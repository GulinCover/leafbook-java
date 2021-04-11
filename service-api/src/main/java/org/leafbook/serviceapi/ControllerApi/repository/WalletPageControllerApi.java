package org.leafbook.serviceapi.ControllerApi.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.repository.publicCommentPage.BillInfosResp;
import org.leafbook.api.respAbs.repository.publicCommentPage.CommentInfosResp;
import org.leafbook.api.respAbs.repository.publicTopicPage.EntryListResp;
import org.leafbook.serviceapi.serviceApi.repository.WalletPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@CrossOrigin("*")
@Api("repository.WalletPageControllerApi")
@RestController
public class WalletPageControllerApi {
    @Autowired
    private WalletPageServiceApi walletPageServiceApi;

    /**
     * 获取用户账单
     * @param userId
     * @param form:page
     * @return
     */
    @ApiOperation("/api/post/select/me/billInfos")
    @PostMapping("/api/post/select/me/billInfos")
    public BillInfosResp postSelectBillInfosApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String,Long> form) {
        BillInfosResp resp = walletPageServiceApi.postSelectBillInfos(userId,form);
        resp.setCode(200);
        return resp;
    }

    //获取回复的著述的词条
    @ApiOperation("/api/post/select/me/billInfos/all/entry")
    @PostMapping("/api/post/select/me/billInfos/all/entry")
    public EntryListResp postSelectPublicTopicInfoAllEntryListApi(@RequestHeader("userId")Long userId) {
        EntryListResp resp = new EntryListResp();

//        resp.setEntryAbsList(walletPageServiceApi.postSelectPublicTopicInfoAllEntryList());

        resp.setCode(200);
        return resp;
    }
}
