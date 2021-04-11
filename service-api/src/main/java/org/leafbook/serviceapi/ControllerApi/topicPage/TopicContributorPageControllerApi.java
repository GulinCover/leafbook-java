package org.leafbook.serviceapi.ControllerApi.topicPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.topicPublicPage.contributor.TopicContributorInfoResp;
import org.leafbook.serviceapi.serviceApi.topicPage.TopicContributorPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("topicPage.TopicContributorPageControllerApi")
@RestController
public class TopicContributorPageControllerApi {
    @Autowired
    private TopicContributorPageServiceApi topicContributorPageServiceApi;

    /**
     * 获取著述所有的贡献者
     * @param userId
     * @param form:topicId,page
     * @return
     */
    @ApiOperation("/api/post/select/multi/topicContributorUserInfo")
    @PostMapping("/api/post/select/multi/topicContributorUserInfo")
    public TopicContributorInfoResp postSelectMultiTopicContributorUserInfoApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String,Long> form
            ) {
        TopicContributorInfoResp resp = topicContributorPageServiceApi.postSelectMultiTopicContributorUserInfo(userId,form);
        resp.setCode(200);
        return resp;
    }
}
