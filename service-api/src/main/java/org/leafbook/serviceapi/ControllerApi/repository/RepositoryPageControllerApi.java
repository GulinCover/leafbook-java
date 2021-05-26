package org.leafbook.serviceapi.ControllerApi.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.api.respAbs.repository.publicCommentPage.BillInfosResp;
import org.leafbook.api.respAbs.repository.publicTopicPage.PublicTopicInfosResp;
import org.leafbook.api.respAbs.repository.repositoryPage.ConsumableInfosResp;
import org.leafbook.api.respAbs.repository.repositoryPage.UseConsumableResp;
import org.leafbook.serviceapi.serviceApi.repository.RepositoryPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin("*")
@Api("repository.RepositoryPageControllerApi")
@RestController
public class RepositoryPageControllerApi {
    @Autowired
    private RepositoryPageServiceApi repositoryPageServiceApi;

    /**
     * 获取用户所有可用物品
     * @param userId
     * @param form:page
     * @return
     */
    @ApiOperation("/api/post/select/me/consumableInfos")
    @PostMapping("/api/post/select/me/consumableInfos")
    public ConsumableInfosResp postSelectConsumableInfosApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String,Long> form) {
        ConsumableInfosResp resp = repositoryPageServiceApi.postSelectConsumableInfos(userId,form);
        resp.setCode(200);
        return resp;
    }

    //搜索用户所有可用物品
    /*
    search_content:搜索内容
    page:
     */
    @ApiOperation("/api/post/select/search/me/consumableInfos")
    @PostMapping("/api/post/select/search/me/consumableInfos")
    public ConsumableInfosResp postSelectSearchConsumableInfosApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String, String> form
    ) {
        ConsumableInfosResp resp = new ConsumableInfosResp();

//        resp.setConsumableInfoAbsList(repositoryPageServiceApi.postSelectConsumableInfos());

        resp.setCode(200);
        return resp;
    }


    /**
     * 使用物品
     * @param userId
     * @param form:resId,newName
     * @return
     */
    @ApiOperation("/api/post/update/use/consumableInfo")
    @PostMapping("/api/post/update/use/consumableInfo")
    public MessageResp postUpdateUseConsumableApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String, String> form
    ) {
        MessageResp resp = new MessageResp();
        int ret = repositoryPageServiceApi.postUpdateUseConsumable(userId,form);
        if (ret == 1) {
            resp.setMsg("使用成功");
            resp.setCode(200);
            return resp;
        }
        resp.setMsg("使用失败");
        resp.setCode(ret);
        return resp;
    }
}
