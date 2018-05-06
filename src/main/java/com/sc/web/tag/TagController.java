package com.sc.web.tag;

import com.sc.auth.Authenticator;
import com.sc.auth.WebApiContext;
import com.sc.domain.ScAppUser;
import com.sc.domain.ScTag;
import com.sc.repository.ScAppUserMapper;
import com.sc.repository.ScTagMapper;
import com.sc.repository.example.ScTagExample;
import com.sc.web.exception.BadRequestException;
import com.sc.web.exception.UnauthorizedException;
import com.sc.web.tag.request.InsertTagRequest;
import com.sc.web.tag.response.TagQueryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by hgwang on 2017/12/28.
 */
@Api(tags = "标签", description = "标签相关接口")
@Slf4j
@RestController
@RequestMapping(value = "/tags")
public class TagController {

    @Autowired
    private Authenticator authenticator;

    @Autowired
    private ScAppUserMapper scAppUserMapper;

    @Autowired
    private ScTagMapper scTagMapper;


    @ApiOperation(value = "获取我的标签",  notes = "标签列表")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<TagQueryResponse> getList(){
        if (!authenticator.authenticate()) {
            throw new UnauthorizedException("当前请求需要用户验证");
        }

        Long memberId = WebApiContext.current().getMemberId();
        ScAppUser scAppUser = scAppUserMapper.selectByPrimaryKey(memberId);
        if (Objects.isNull(scAppUser)) {
            throw new BadRequestException("会话状态无效，请退出重新登录");
        }

        ScTagExample scTagExample = new ScTagExample();
        scTagExample.createCriteria()
                .andUserIdEqualTo(memberId)
                .andDeletedEqualTo(false);
        scTagExample.setOrderByClause("created_at DESC");
        List<ScTag> scTags = scTagMapper.selectByExample(scTagExample);

        List<TagQueryResponse> results = new ArrayList<TagQueryResponse>();

        for(ScTag sctag : scTags){
            TagQueryResponse tag = new TagQueryResponse();
            tag.setTagId(sctag.getId());
            tag.setTagName(sctag.getName());
            results.add(tag);
        }

        return results;
    }

    @ApiOperation(value = "新增标签",  notes = "标签")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insert(InsertTagRequest request){
        if (!authenticator.authenticate()) {
            throw new UnauthorizedException("当前请求需要用户验证");
        }

        Long memberId = WebApiContext.current().getMemberId();
        ScAppUser scAppUser = scAppUserMapper.selectByPrimaryKey(memberId);
        if (Objects.isNull(scAppUser)) {
            throw new BadRequestException("会话状态无效，请退出重新登录");
        }
        ScTag scTag = new ScTag();
        scTag.setName(request.getTagName());
        scTag.setUserId(memberId);

        scTagMapper.insertSelective(scTag);
    }

    @ApiOperation(value = "删除标签",  notes = "标签")
    @RequestMapping(value = "/delete/{tagId}", method = RequestMethod.POST)
    public void insert(@PathVariable() Long tagId){
        if (!authenticator.authenticate()) {
            throw new UnauthorizedException("当前请求需要用户验证");
        }

        Long memberId = WebApiContext.current().getMemberId();
        ScAppUser scAppUser = scAppUserMapper.selectByPrimaryKey(memberId);
        if (Objects.isNull(scAppUser)) {
            throw new BadRequestException("会话状态无效，请退出重新登录");
        }

        ScTag tag = new ScTag();
        tag.setId(tagId);
        tag.setDeleted(true);
        scTagMapper.updateByPrimaryKeySelective(tag);
//        scTagMapper.deleteByPrimaryKey(tagId);
    }
}
