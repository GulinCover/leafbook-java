package org.leafbook.serviceapi.ControllerApi.topicPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.topicPublicPage.manager.TopicManagerInfoResp;
import org.leafbook.serviceapi.serviceApi.topicPage.TopicManagerPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("topicPage.TopicManagerPageControllerApi")
@RestController
public class TopicManagerPageControllerApi {
    @Autowired
    private TopicManagerPageServiceApi topicManagerPageServiceApi;

    /**
     * 获取著述的所有管理者
     *
     * @param userId
     * @param form:topicId
     * @return
     */
    @ApiOperation("/api/post/select/all/topicManagerUser")
    @PostMapping("/api/post/select/all/topicManagerUser")
    public TopicManagerInfoResp postSelectAllTopicManagerUserApi(
            @RequestHeader("userId") Long userId,
            @RequestBody Map<String, Long> form
    ) {
        TopicManagerInfoResp resp = new TopicManagerInfoResp();

        resp.setTopicManagerAbsList(topicManagerPageServiceApi.postSelectAllTopicManagerUser(userId, form));
        resp.setCode(200);
        return resp;
    }

}
