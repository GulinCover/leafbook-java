package org.leafbook.serviceapi.ControllerApi.topicPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.topicPublicPage.talk.CreateTalkInfoResp;
import org.leafbook.api.respAbs.topicPublicPage.talk.TalkComment1InfoResp;
import org.leafbook.api.respAbs.topicPublicPage.talk.TalkComment2InfoResp;
import org.leafbook.api.respAbs.topicPublicPage.talk.TalkInfoResp;
import org.leafbook.serviceapi.serviceApi.topicPage.TalkPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("topicPage.TalkPageControllerApi")
@RestController
public class TalkPageControllerApi {
    @Autowired
    private TalkPageServiceApi talkPageServiceApi;

    /**
     * 获取talkInfo
     * @param userId
     * @param form:topicId,page
     * @return
     */
    @ApiOperation("/api/post/select/talkInfos")
    @PostMapping("/api/post/select/talkInfos")
    public TalkInfoResp postSelectTalkInfosApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String ,Long> form
            ) {

        TalkInfoResp resp = talkPageServiceApi.getSelectTalkInfos(userId,form);
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取talk的一级评论
     * @param userId
     * @param form:talkId,page
     * @return
     */
    @ApiOperation("/api/post/select/talkComment1Infos")
    @PostMapping("/api/post/select/talkComment1Infos")
    public TalkComment1InfoResp postSelectTalkComment1InfosApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String ,Long> form
    ) {
        TalkComment1InfoResp resp = talkPageServiceApi.postSelectTalkComment1Infos(userId,form);

        resp.setCode(200);
        return resp;
    }

    /**
     * 获取二级评论
     * @param userId
     * @param form:comment1Id,page
     * @return
     */
    @ApiOperation("/api/post/select/talkComment2Info")
    @PostMapping("/api/post/select/talkComment2Info")
    public TalkComment2InfoResp postSelectTalkComment2InfoApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String ,Long> form
    ) {
        TalkComment2InfoResp resp = talkPageServiceApi.postSelectTalkComment2Info(userId,form);
        resp.setCode(200);
        return resp;
    }

    /**
     * 创建talkInfo
     * @param userId
     * @param form:talkTitle,talkDesc,topicId
     * @return
     */
    @ApiOperation("/api/post/insert/create/talkInfo")
    @PostMapping("/api/post/insert/create/talkInfo")
    public CreateTalkInfoResp postInsertCreateTalkInfoApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String ,String> form
    ) {
        CreateTalkInfoResp resp = talkPageServiceApi.postInsertCreateTalkInfo(userId,form);
        return resp;
    }
}
