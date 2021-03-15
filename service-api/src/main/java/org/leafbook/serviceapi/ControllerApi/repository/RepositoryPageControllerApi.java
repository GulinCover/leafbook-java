package org.leafbook.serviceapi.ControllerApi.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    //获取用户所有可用物品
    @ApiOperation("/api/post/select/me/consumableInfos")
    @PostMapping("/api/post/select/me/consumableInfos")
    public ConsumableInfosResp postSelectConsumableInfosApi(@RequestHeader("user_id")Long userId) {
        ConsumableInfosResp resp = new ConsumableInfosResp();

        resp.setConsumableInfoAbsList(repositoryPageServiceApi.postSelectConsumableInfos());

        resp.setCode(200);
        return resp;
    }

    //搜索用户所有可用物品
    /*
    search_content:搜索内容
    page:
     */
    @ApiOperation("/api/post/select/search/me/consumableInfos")
    @PostMapping(value = "/api/post/select/search/me/consumableInfos",produces = MediaType.APPLICATION_JSON_VALUE)
    public ConsumableInfosResp postSelectSearchConsumableInfosApi(
            @RequestHeader("user_id")Long userId,
            @RequestBody Map<String, String> form
    ) {
        ConsumableInfosResp resp = new ConsumableInfosResp();

        resp.setConsumableInfoAbsList(repositoryPageServiceApi.postSelectConsumableInfos());

        resp.setCode(200);
        return resp;
    }

    //使用物品
    /*
    consumable_id:
    consumable_uuid:
     */
    @ApiOperation("/api/post/update/use/consumableInfo")
    @PostMapping(value = "/api/post/update/use/consumableInfo",produces = MediaType.APPLICATION_JSON_VALUE)
    public UseConsumableResp postUpdateUseConsumableApi(
            @RequestHeader("user_id")Long userId,
            @RequestBody Map<String, String> form
    ) {
        UseConsumableResp resp = new UseConsumableResp();

        resp.setMessage("使用成功");
        resp.setCode(200);
        return resp;
    }
}
