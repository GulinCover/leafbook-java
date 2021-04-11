package org.leafbook.serviceapi.ControllerApi.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.repository.overviewPage.LastCommentInfoResp;
import org.leafbook.api.respAbs.repository.overviewPage.LastTopicInfoResp;
import org.leafbook.serviceapi.serviceApi.repository.OverviewPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("repository.OverviewPageControllerApi")
@RestController
public class OverviewPageControllerApi {
    @Autowired
    private OverviewPageServiceApi overviewPageServiceApi;

    /**
     * 获取用户发布的一些著述
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/multi/lastTopicInfo")
    @PostMapping("/api/post/select/multi/lastTopicInfo")
    public LastTopicInfoResp postSelectMultiLastTopicInfoApi(
            @RequestHeader("userId")Long userId
    ) {
        LastTopicInfoResp resp = new LastTopicInfoResp();

        resp.setLastTopicAbsList(overviewPageServiceApi.postSelectMultiLastTopicInfo(userId));
        resp.setCode(200);
        return resp;
    }
    /**
     * 获取用户发布著述的最近的一些评论
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/multi/lastCommentInfo")
    @PostMapping("/api/post/select/multi/lastCommentInfo")
    public LastCommentInfoResp postSelectMultiLastCommentInfoApi(
            @RequestHeader("userId")Long userId
    ) {
        LastCommentInfoResp resp = new LastCommentInfoResp();

        resp.setLastTopicCommentAbsList(overviewPageServiceApi.postSelectMultiLastCommentInfo(userId));
        resp.setCode(200);
        return resp;
    }
}
