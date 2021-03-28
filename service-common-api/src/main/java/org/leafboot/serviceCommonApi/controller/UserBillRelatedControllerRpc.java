package org.leafboot.serviceCommonApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.userInfo.UserBillModel;
import org.leafbook.api.respAbs.userManagerPage.StarRelatedIncomeAndExpenditureResp;
import org.leafboot.serviceCommonApi.service.UserBillRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@Api("BillRelatedControllerRpc")
@RestController
public class UserBillRelatedControllerRpc {
    @Autowired
    private UserBillRelatedServiceRpc userBillRelatedServiceRpc;

    /**
     * 获取用户赞相关的收支
     * @param userId
     * @param page
     * @return
     */
    @ApiOperation("/rpc/post/select/star/incomeAndExpenditure")
    @PostMapping("/rpc/post/select/star/incomeAndExpenditure")
    public List<UserBillModel> postSelectStarRelatedIncomeAndExpenditureRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page) {
        return userBillRelatedServiceRpc.postSelectStarRelatedIncomeAndExpenditure(userId,page);
    }
}
