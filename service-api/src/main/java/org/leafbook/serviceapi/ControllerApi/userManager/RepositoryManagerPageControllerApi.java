package org.leafbook.serviceapi.ControllerApi.userManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.userManagerPage.RepositoryManagerTopicResp;
import org.leafbook.serviceapi.serviceApi.userManager.RepositoryManagerPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("RepositoryManagerPageControllerApi")
@RestController
public class RepositoryManagerPageControllerApi {
    @Autowired
    private RepositoryManagerPageServiceApi repositoryManagerPageServiceApi;


    /**
     * 获取用户已发布的著述
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/multi/me/topicInfo")
    @PostMapping("/api/post/select/multi/me/topicInfo")
    public RepositoryManagerTopicResp postSelectMultiMeTopicInfoApi(@RequestHeader("userId")Long userId) {
        RepositoryManagerTopicResp resp = new RepositoryManagerTopicResp();
        resp.setRepositoryManagerTopicAbsList(repositoryManagerPageServiceApi.postSelectMultiMeTopicInfo(userId));
        resp.setCode(200);
        return resp;
    }
}








