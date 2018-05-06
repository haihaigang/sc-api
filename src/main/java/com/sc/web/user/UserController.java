package com.sc.web.user;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sc.auth.*;
import com.sc.domain.ScAppUser;
import com.sc.domain.ScDevice;
import com.sc.domain.ScDeviceSignup;
import com.sc.repository.ScAppUserMapper;
import com.sc.repository.ScDeviceMapper;
import com.sc.repository.ScDeviceSignupMapper;
import com.sc.repository.example.ScAppUserExample;
import com.sc.repository.example.ScDeviceSignupExample;
import com.sc.utils.AESTool;
import com.sc.web.device.response.DeviceQueryResponse;
import com.sc.web.exception.InternalServerErrorException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hgwang on 2017/12/27.
 */
@Api(tags = "会员", description = "会员、账号相关接口")
@Slf4j
@RestController
@RequestMapping(value = "/users")
public class UserController {
    public static final String APP_ID = "6B25E3BA0B9416CB30100378452CF4C8";

    public static final String APP_SECRET = "CBF72FB38B96117E54DD1CFE0E6F57F8";

    public static final String KEY = "20160226!#xm@837";

    public static final String IV_PARAMETER = "0231345874954435";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    @Autowired
    private com.sc.auth.Authenticator authenticator;

    @Autowired
    private ScAppUserMapper scAppUserMapper;

    @ApiOperation(value = "自动登录", notes = "使用市民云的usertoken自动登录")
    @RequestMapping(value = "/autologin", method = RequestMethod.POST)
    public void autoLogin(@RequestParam String usertoken){
        String accessToken = null;
        JSONObject smyUser = null;
        Long memberId = null;

        try {
            accessToken = getAccessToken();
            smyUser = getSMYUserINfo(usertoken, accessToken);
            String unid = smyUser.getString("user_unid");

            //根据市民云中获取的unid判断appuser表是否存在，存在更新、不存在新增
            ScAppUserExample scAppUserExample = new ScAppUserExample();
            scAppUserExample.createCriteria()
                    .andSmUnidEqualTo(unid)
                    .andDeletedEqualTo(false);
            List<ScAppUser> scAppUsers = scAppUserMapper.selectByExample(scAppUserExample);

            if(CollectionUtils.isEmpty(scAppUsers)){
                ScAppUser insertedUser = getAppUserFromObj(smyUser);
                scAppUserMapper.insertSelective(insertedUser);
                memberId = insertedUser.getId();
            }else {
                ScAppUser updatedUser = getAppUserFromObj(smyUser);
                updatedUser.setId(scAppUsers.get(0).getId());
                scAppUserMapper.updateByPrimaryKeySelective(updatedUser);
                memberId = updatedUser.getId();
            }

            // 往session中设置会员ID
            authenticator.newSession(memberId);
        } catch (Exception e) {
//            log.error("调用SmartCity接口错误", e);
            throw new InternalServerErrorException("手机号尚未注册");
        }
    }

    private ScAppUser getAppUserFromObj(JSONObject obj){
        ScAppUser scAppUser = new ScAppUser();

        scAppUser.setPhoneNumber(obj.getString("user_login_name"));

        scAppUser.setSmUnid(obj.getString("user_unid"));
        scAppUser.setSmUserName(obj.getString("user_name"));
        scAppUser.setPhoneNumber(obj.getString("user_login_name"));
        scAppUser.setSmUserType(obj.getString("user_type"));

        JSONObject userObj = obj.getJSONObject("user");
        scAppUser.setSmCertificateNumber(userObj.getString("certificateNumber"));
        scAppUser.setSmCertificateType(userObj.getString("certificateType"));
        scAppUser.setSmSex(userObj.getString("se"));
        scAppUser.setSmMobilePhone(userObj.getString("mobilePhone"));
        scAppUser.setSmAddress(userObj.getString("address"));
        scAppUser.setSmEmail(userObj.getString("email"));
        scAppUser.setSmName(userObj.getString("name"));
        scAppUser.setSmLastUpdatetime(userObj.getString("last_updatetime"));

        return scAppUser;
    }

    private String getAccessToken() throws Exception {
        String url = "http://175.43.157.100:8093/SmartCity/user/get_access_token";

        String params = "{\"app_id\":\"" + APP_ID + "\",\"app_secret\":\"" + APP_SECRET + "\"}";
        String encryptedParams = AESTool.Encrypt(params, KEY, IV_PARAMETER);

        JSONObject requestBody = new JSONObject();
        requestBody.put("signature", "");
        requestBody.put("timestamp", System.currentTimeMillis());
        requestBody.put("params", encryptedParams);

        JSONObject responseBody = postJson(url, requestBody);
//        log.info("smartcity:get_access_toke响应, response={}", responseBody.toJSONString());

        if (!Objects.equals(responseBody.getString("result"), "0")) {
            throw new InternalServerErrorException("");
        }

        return responseBody.getString("access_token");
    }

    private JSONObject getSMYUserINfo(String userToken, String accessToken) throws Exception {
//        String url = "http://59.61.216.123:39012/SmartCity/user/getuserinfo";
        String url = "http://175.43.157.100:8093/SmartCity/user/getuserinfo";

        JSONObject requestBody = new JSONObject();
        requestBody.put("access_token", accessToken);
        requestBody.put("user_token", userToken);
        requestBody.put("signature", "");
        requestBody.put("timestamp", System.currentTimeMillis());
        requestBody.put("params", "");

        JSONObject responseBody = postJson(url, requestBody);
//        log.info("smartcity:getuserinfo响应, response={}", responseBody.toJSONString());

        if (!Objects.equals(responseBody.getString("result"), "0")) {
            return null;
        }
        String dataEncrypted = responseBody.getString("data");
//        log.info("smartcity:getuserinfo响应data密文, data={}", dataEncrypted);
        String dataStr = AESTool.getInstance().decrypt(dataEncrypted, KEY, IV_PARAMETER);
//        log.info("smartcity:getuserinfo响应data解密, data={}", dataStr);
        return JSONObject.parseObject(dataStr);
    }

    private JSONObject postJson(String url, JSONObject requestBody) throws IOException {
        RequestBody body = RequestBody.create(JSON, requestBody.toJSONString());
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        return JSONObject.parseObject(response.body().string());
    }
}
