package org.leafbook.serviceapi.ControllerApi.userManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.api.respAbs.userManagerPage.BuyAndSellRelatedIncomeAndExpenditureResp;
import org.leafbook.api.respAbs.userManagerPage.StarRelatedIncomeAndExpenditureResp;
import org.leafbook.api.respAbs.userManagerPage.TopicRelatedIncomeAndExpenditureResp;
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
     * 获取用户月增长
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/user/balance/income")
    @PostMapping("/api/post/select/user/balance/income")
    public MessageResp postSelectUserBalanceIncomeApi(@RequestHeader("userId")Long userId) {
        MessageResp resp = new MessageResp();
        Long ret = billAndPayPageServiceApi.postSelectUserBalanceIncome(userId);
        resp.setMsg(ret.toString());
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取用户月支出
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/user/balance/expenditure")
    @PostMapping("/api/post/select/user/balance/expenditure")
    public MessageResp postSelectUserBalanceExpenditureApi(@RequestHeader("userId")Long userId) {
        MessageResp resp = new MessageResp();
        Long ret = billAndPayPageServiceApi.postSelectUserBalanceExpenditure(userId);
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
        StarRelatedIncomeAndExpenditureResp resp = billAndPayPageServiceApi.postSelectStarRelatedIncomeAndExpenditure(userId,form);
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取用户赞相关的总支出
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/user/star/incomeAndExpenditure/amount")
    @PostMapping("/api/post/select/user/star/incomeAndExpenditure/amount")
    public MessageResp postSelectUserStarRelatedIncomeAndExpenditureAmountApi(
            @RequestHeader("userId")Long userId) {
        MessageResp resp = new MessageResp();

        resp.setMsg(billAndPayPageServiceApi.postSelectUserStarRelatedIncomeAndExpenditureAmount(userId).toString());
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取用户赞总收入
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/star/income/amount")
    @PostMapping("/api/post/select/star/income/amount")
    public MessageResp postSelectStarIncomeAmountApi(@RequestHeader("userId")Long userId) {
        MessageResp resp = new MessageResp();

        resp.setMsg(billAndPayPageServiceApi.postSelectStarIncomeAmount(userId).toString());
        resp.setCode(200);
        return resp;
    }
    /**
     * 获取用户赞总支出
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/star/expenditure/amount")
    @PostMapping("/api/post/select/star/expenditure/amount")
    public MessageResp postSelectStarExpenditureAmountApi(@RequestHeader("userId")Long userId) {
        MessageResp resp = new MessageResp();

        resp.setMsg(billAndPayPageServiceApi.postSelectStarExpenditureAmount(userId).toString());
        resp.setCode(200);
        return resp;
    }


    /**
     * 获取用户著述相关的收支
     * @param userId
     * @param form: page
     * @return
     */
    @ApiOperation("/api/post/select/topic/incomeAndExpenditure")
    @PostMapping("/api/post/select/topic/incomeAndExpenditure")
    public TopicRelatedIncomeAndExpenditureResp postSelectTopicRelatedIncomeAndExpenditureApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String,Long> form) {
        TopicRelatedIncomeAndExpenditureResp resp = billAndPayPageServiceApi.postSelectTopicRelatedIncomeAndExpenditure(userId,form);
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取用户著述相关的总收支
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/user/topic/incomeAndExpenditure/amount")
    @PostMapping("/api/post/select/user/topic/incomeAndExpenditure/amount")
    public MessageResp postSelectUserTopicRelatedIncomeAndExpenditureAmountApi(
            @RequestHeader("userId")Long userId) {
        MessageResp resp = new MessageResp();

        resp.setMsg(billAndPayPageServiceApi.postSelectUserTopicRelatedIncomeAndExpenditureAmount(userId).toString());
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取用户著述总收入
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/topic/income/amount")
    @PostMapping("/api/post/select/topic/income/amount")
    public MessageResp postSelectTopicIncomeAmountApi(@RequestHeader("userId")Long userId) {
        MessageResp resp = new MessageResp();

        resp.setMsg(billAndPayPageServiceApi.postSelectTopicIncomeAmount(userId).toString());
        resp.setCode(200);
        return resp;
    }
    /**
     * 获取用户著述总支出
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/topic/expenditure/amount")
    @PostMapping("/api/post/select/topic/expenditure/amount")
    public MessageResp postSelectTopicExpenditureAmountApi(@RequestHeader("userId")Long userId) {
        MessageResp resp = new MessageResp();

        resp.setMsg(billAndPayPageServiceApi.postSelectTopicExpenditureAmount(userId).toString());
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取用户购入卖出相关的收支
     * @param userId
     * @param form: page
     * @return
     */
    @ApiOperation("/api/post/select/buyAndSell/incomeAndExpenditure")
    @PostMapping("/api/post/select/buyAndSell/incomeAndExpenditure")
    public BuyAndSellRelatedIncomeAndExpenditureResp postSelectBuyAndSellRelatedIncomeAndExpenditureApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String,Long> form) {
        BuyAndSellRelatedIncomeAndExpenditureResp resp = billAndPayPageServiceApi.postSelectBuyAndSellRelatedIncomeAndExpenditure(userId,form);
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取用户购入卖出相关的总收支
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/user/buyAndSell/incomeAndExpenditure/amount")
    @PostMapping("/api/post/select/user/buyAndSell/incomeAndExpenditure/amount")
    public MessageResp postSelectUserBuyAndSellRelatedIncomeAndExpenditureAmountApi(
            @RequestHeader("userId")Long userId) {
        MessageResp resp = new MessageResp();

        resp.setMsg(billAndPayPageServiceApi.postSelectUserBuyAndSellRelatedIncomeAndExpenditureAmount(userId).toString());
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取用户买卖总收入
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/buyAndSell/income/amount")
    @PostMapping("/api/post/select/buyAndSell/income/amount")
    public MessageResp postSelectBuyAndSellIncomeAmountApi(@RequestHeader("userId")Long userId) {
        MessageResp resp = new MessageResp();

        resp.setMsg(billAndPayPageServiceApi.postSelectBuyAndSellIncomeAmount(userId).toString());
        resp.setCode(200);
        return resp;
    }
    /**
     * 获取用户买卖总支出
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/buyAndSell/expenditure/amount")
    @PostMapping("/api/post/select/buyAndSell/expenditure/amount")
    public MessageResp postSelectBuyAndSellExpenditureAmountApi(@RequestHeader("userId")Long userId) {
        MessageResp resp = new MessageResp();

        resp.setMsg(billAndPayPageServiceApi.postSelectBuyAndSellExpenditureAmount(userId).toString());
        resp.setCode(200);
        return resp;
    }


}




