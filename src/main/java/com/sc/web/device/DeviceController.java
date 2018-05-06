package com.sc.web.device;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sc.auth.Authenticator;
import com.sc.auth.WebApiContext;
import com.sc.domain.ScAppUser;
import com.sc.domain.ScDevice;
import com.sc.domain.ScDeviceSignup;
import com.sc.domain.ScDeviceTag;
import com.sc.repository.ScAppUserMapper;
import com.sc.repository.ScDeviceMapper;
import com.sc.repository.ScDeviceSignupMapper;
import com.sc.repository.ScDeviceTagMapper;
import com.sc.repository.example.ScDeviceSignupExample;
import com.sc.repository.example.ScDeviceTagExample;
import com.sc.web.device.request.DeviceQueryRequest;
import com.sc.web.device.request.SaveDeviceRequest;
import com.sc.web.device.response.DeviceQueryResponse;
import com.sc.web.exception.BadRequestException;
import com.sc.web.exception.InternalServerErrorException;
import com.sc.web.exception.NotFoundException;
import com.sc.web.exception.UnauthorizedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.RequestBody;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hgwang on 2017/12/27.
 */
@Api(tags = "设备", description = "设备相关接口")
@Slf4j
@RestController
@RequestMapping(value = "/devices")
public class DeviceController {
    public static final String AKAOL_CUSTOMER = "88536915";

    public static final String AKAOL_SECRET = "Llpq1ts51W8i5CJTRCGis";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    @Autowired
    private Authenticator authenticator;

    @Autowired
    private ScDeviceMapper scDeviceMapper;

    @Autowired
    private ScAppUserMapper scAppUserMapper;

    @Autowired
    private ScDeviceSignupMapper scDeviceSignupMapper;

    @Autowired
    private ScDeviceTagMapper scDeviceTagMapper;


    /**
     * 对象转换
     *
     * @param entitys
     * @param results
     */
    private void convertEntityToResponse(List<ScDevice> entitys, List<DeviceQueryResponse> results) {
        //第一组
        for (ScDevice entity : entitys) {
            DeviceQueryResponse response = new DeviceQueryResponse();
            BeanUtils.copyProperties(entity, response);
//            LabelValueItem typeEnum = response.getTypeEnum();
//            typeEnum.setName("type");
//            typeEnum.setLabel(com.newhead.sc.modules.scdevice.ScDeviceTypeEnum.getLabel(entity.getType()));
//            typeEnum.setValue(entity.getType());
//            typeEnum.setChecked(true);
            results.add(response);
        }
    }

    @ApiOperation(value = "获取我的设备",  notes = "设备列表")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public List<DeviceQueryResponse> query(DeviceQueryRequest request) {
        if (!authenticator.authenticate()) {
            throw new UnauthorizedException("当前请求需要用户验证");
        }

        String number = null;
        Long memberId = WebApiContext.current().getMemberId();
        ScAppUser scAppUser = scAppUserMapper.selectByPrimaryKey(memberId);
        if (Objects.isNull(scAppUser)) {
            throw new BadRequestException("会话状态无效，请退出重新登录");
        }

        number = scAppUser.getPhoneNumber();

        ScDeviceSignupExample signupExample = new ScDeviceSignupExample();
        signupExample.createCriteria().andPhoneNumberEqualTo(number).andDeletedEqualTo(Boolean.FALSE);
        List<ScDeviceSignup> signups = scDeviceSignupMapper.selectByExample(signupExample);

        if(request.getTagId() != null) {
            //按照标签筛选
            ScDeviceTagExample scDeviceTagExample = new ScDeviceTagExample();
            scDeviceTagExample.createCriteria()
                    .andTagIdEqualTo(request.getTagId())
                    .andDeletedEqualTo(false);

            List<ScDeviceTag> scDeviceTags = scDeviceTagMapper.selectByExample(scDeviceTagExample);
            List<ScDeviceSignup> newsignups = new ArrayList<ScDeviceSignup>();
            for(ScDeviceSignup signup : signups) {
                Boolean flag = false;
                for (ScDeviceTag tag : scDeviceTags) {
                    if (signup.getDeviceId() == tag.getDeviceId()) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    newsignups.add(signup);
                }
            }
            signups = newsignups;
        }

        List<ScDevice> devices = Lists.newArrayList();
        for (ScDeviceSignup signup : signups) {
            ScDevice device = scDeviceMapper.selectByPrimaryKey(signup.getDeviceId());
            devices.add(device);
        }

        List<DeviceQueryResponse> results = new ArrayList<DeviceQueryResponse>();
        //构造查询对象
        convertEntityToResponse(devices, results);

        return results;
    }

    @ApiOperation(value = "获取我展示在首页设备",  notes = "设备列表")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public List<DeviceQueryResponse> getListForHome() {
        if (!authenticator.authenticate()) {
            throw new UnauthorizedException("当前请求需要用户验证");
        }

        String number = null;
        Long memberId = WebApiContext.current().getMemberId();
        ScAppUser scAppUser = scAppUserMapper.selectByPrimaryKey(memberId);
        if (Objects.isNull(scAppUser)) {
            throw new BadRequestException("会话状态无效，请退出重新登录");
        }

        number = scAppUser.getPhoneNumber();

        ScDeviceSignupExample signupExample = new ScDeviceSignupExample();
        signupExample.createCriteria().andPhoneNumberEqualTo(number).andDeletedEqualTo(Boolean.FALSE);
        List<ScDeviceSignup> signups = scDeviceSignupMapper.selectByExample(signupExample);
        List<ScDevice> devices = Lists.newArrayList();

        for (ScDeviceSignup signup : signups) {
            ScDevice device = scDeviceMapper.selectByPrimaryKey(signup.getDeviceId());
            if(device.getForFront()) {
                devices.add(device);
            }
        }

        List<DeviceQueryResponse> results = new ArrayList<DeviceQueryResponse>();
        //构造查询对象
        convertEntityToResponse(devices, results);

        return results;
    }


    @ApiOperation(value = "获取设备详情",  notes = "设备详情")
    @RequestMapping(value = "/{deviceId}", method = RequestMethod.GET)
    public DeviceQueryResponse getDetail(@PathVariable Long deviceId) {
        if (!authenticator.authenticate()) {
            throw new UnauthorizedException("当前请求需要用户验证");
        }

        String number = null;
        Long memberId = WebApiContext.current().getMemberId();
        ScAppUser scAppUser = scAppUserMapper.selectByPrimaryKey(memberId);
        if (Objects.isNull(scAppUser)) {
            throw new BadRequestException("会话状态无效，请退出重新登录");
        }

        number = scAppUser.getPhoneNumber();

        ScDeviceSignupExample signupExample = new ScDeviceSignupExample();
        signupExample.createCriteria()
                .andPhoneNumberEqualTo(number)
                .andDeviceIdEqualTo(deviceId)
                .andDeletedEqualTo(Boolean.FALSE);
        List<ScDeviceSignup> signups = scDeviceSignupMapper.selectByExample(signupExample);

        if(CollectionUtils.isEmpty(signups)){
            throw new NotFoundException("未找到对应用户的设备");
        }

        ScDevice device = scDeviceMapper.selectByPrimaryKey(signups.get(0).getDeviceId());

        ScDeviceTagExample scDeviceTagExample = new ScDeviceTagExample();
        scDeviceTagExample.createCriteria()
                .andDeviceIdEqualTo(device.getId())
                .andDeletedEqualTo(false);
        List<ScDeviceTag> scDeviceTags = scDeviceTagMapper.selectByExample(scDeviceTagExample);

        DeviceQueryResponse result = new DeviceQueryResponse();
        BeanUtils.copyProperties(device, result);

        if(CollectionUtils.isNotEmpty(scDeviceTags)){
            result.setTagId(scDeviceTags.get(0).getTagId());
        }

        return result;
    }

    @ApiOperation(value = "更新设备",  notes = "保存设备")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(SaveDeviceRequest request){
        if (!authenticator.authenticate()) {
            throw new UnauthorizedException("当前请求需要用户验证");
        }

        String number = null;
        Long memberId = WebApiContext.current().getMemberId();
        ScAppUser scAppUser = scAppUserMapper.selectByPrimaryKey(memberId);
        if (Objects.isNull(scAppUser)) {
            throw new BadRequestException("会话状态无效，请退出重新登录");
        }

        number = scAppUser.getPhoneNumber();

        ScDeviceSignupExample signupExample = new ScDeviceSignupExample();
        signupExample.createCriteria()
                .andPhoneNumberEqualTo(number)
                .andDeviceIdEqualTo(request.getId())
                .andDeletedEqualTo(Boolean.FALSE);
        List<ScDeviceSignup> signups = scDeviceSignupMapper.selectByExample(signupExample);

        if(CollectionUtils.isEmpty(signups)){
            throw new NotFoundException("未找到对应用户的设备");
        }

        ScDevice device = scDeviceMapper.selectByPrimaryKey(signups.get(0).getDeviceId());

        if(device == null){
            throw new NotFoundException("未找到设备信息");
        }

        if(StringUtils.isNotEmpty(request.getDeviceName())){
            device.setDeviceName(request.getDeviceName());
        }
        if(StringUtils.isNotEmpty(request.getDevicePic())){
            device.setImage(request.getDevicePic());
        }
        if(request.getIsForFront() != null){
            device.setForFront(request.getIsForFront());
        }

        if(request.getTagId() != null){
            //保存标签
            ScDeviceTagExample scDeviceTagExample = new ScDeviceTagExample();
            scDeviceTagExample.createCriteria()
                    .andDeviceIdEqualTo(device.getId())
                    .andDeletedEqualTo(false);
            scDeviceTagMapper.deleteByExample(scDeviceTagExample);

            ScDeviceTag scDeviceTag = new ScDeviceTag();
            scDeviceTag.setTagId(request.getTagId());
            scDeviceTag.setDeviceId(device.getId());
            scDeviceTagMapper.insertSelective(scDeviceTag);
        }

        scDeviceMapper.updateByPrimaryKeySelective(device);
    }


    @ApiOperation(value = "获取佳乐开门二维码", notes = "获取佳乐开门二维码")
    @RequestMapping(value = "getakaolqrcode", method = RequestMethod.GET)
    public String getAkaolQrCode(
            @RequestParam String cno,
            @RequestParam String bno,
            @RequestParam String ano,
            @RequestParam String hno,
            @RequestParam String account) throws IOException {
        String qrCode = getQrCode(cno, bno, ano, hno, account);
        return (qrCode);
    }

    private String getQrCode(String cno, String bno, String ano, String hno, String account) throws IOException {
        String url = "http://webservice.akaol.com/api/getQrcode";

        String nonceStr = getNonceStr();
        String sign = signAkaolParams(nonceStr);

        Map<String, String> params = Maps.newHashMap();
        params.put("cno", cno);
        params.put("bno", bno);
        params.put("ano", ano);
        params.put("hno", hno);
        params.put("account", account);
        params.put("customer", AKAOL_CUSTOMER);
        params.put("nonce_str", nonceStr);
        params.put("sign", sign);

        JSONObject responseBody = postForm(url, params);
//        log.info("akaol:getqrcode响应, response={}", responseBody.toJSONString());

        if (!Objects.equals(responseBody.getString("errorCode"), "S0000")) {
            throw new InternalServerErrorException("");
        }

        return responseBody.getString("qrcode");
    }

    private JSONObject postJson(String url, JSONObject requestBody) throws IOException {
        RequestBody body = RequestBody.create(JSON, requestBody.toJSONString());
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        return JSONObject.parseObject(response.body().string());
    }

    private JSONObject postForm(String url, Map<String, String> requestBody) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();

        for (String key : requestBody.keySet()) {
            builder.add(key, requestBody.get(key));
        }

        FormBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        return JSONObject.parseObject(response.body().string());
    }

    private String getNonceStr() {
        return System.currentTimeMillis() + RandomStringUtils.random(11, true, true);
    }

    private String signAkaolParams(String nonceStr) {
        return DigestUtils.md5Hex(AKAOL_CUSTOMER + AKAOL_SECRET + nonceStr);
    }
}
