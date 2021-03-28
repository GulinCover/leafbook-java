package org.leafbook.serviceapi.ControllerApi.userManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.api.respAbs.userManagerPage.StarRelatedIncomeAndExpenditureResp;
import org.leafbook.serviceapi.serviceApi.userManager.BillAndPayPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@CrossOrigin("*")
@Api("BillAndPayPageControllerApi")
@RestController
public class BillAndPayPageControllerApi {
    @Autowired
    private BillAndPayPageServiceApi billAndPayPageServiceApi;

    /**
     * 获取用户余额
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/user/balance")
    @PostMapping("/api/post/select/user/balance")
    public MessageResp postSelectUserBalanceApi(@RequestHeader("userId")Long userId) {
        MessageResp resp = new MessageResp();
        Long ret = billAndPayPageServiceApi.postSelectUserBalance(userId);
        resp.setMsg(ret.toString());
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取用户赞相关的收支
     * @param userId
     * @param form: page
     * @return
     */
    @ApiOperation("/api/post/select/star/incomeAndExpenditure")
    @PostMapping("/api/post/select/star/incomeAndExpenditure")
    public StarRelatedIncomeAndExpenditureResp postSelectStarRelatedIncomeAndExpenditureApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String,Long> form) {
        StarRelatedIncomeAndExpenditureResp resp = new StarRelatedIncomeAndExpenditureResp();

        Long page = form.get("page");
        if (Objects.isNull(page) || page <= 0) {
            resp.setCode(403);
            return resp;
        }
        resp.setStarRelatedIncomeAndExpenditureAbsList(billAndPayPageServiceApi.postSelectStarRelatedIncomeAndExpenditure(userId,page));
        resp.setCode(200);
        return resp;
    }
}




