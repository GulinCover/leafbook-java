package org.leafbook.serviceapi.ControllerApi.topicPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.topicPublicPage.branch.DirectoryInfoResp;
import org.leafbook.serviceapi.serviceApi.topicPage.TopicBranchPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Api("topicPage.TopicBranchPageControllerApi")
@RestController
public class TopicBranchPageControllerApi {
    @Autowired
    private TopicBranchPageServiceApi topicBranchPageServiceApi;

    /**
     * 获取目录
     * @param userId
     * @param topicId
     * @param page
     * @return
     */
    @ApiOperation("/api/get/select/topic/directory/by/{topicId}/page/{page}")
    @GetMapping("/api/get/select/topic/directory/by/{topicId}/page/{page}")
    public DirectoryInfoResp getSelectTopicDirectoryApi(
            @RequestHeader("userId")Long userId,
            @PathVariable("topicId")Long topicId,
            @PathVariable("page")Long page
    ) {
        DirectoryInfoResp resp = topicBranchPageServiceApi.getSelectTopicDirectory(userId, topicId,page);
        resp.setCode(200);
        return resp;
    }
}
