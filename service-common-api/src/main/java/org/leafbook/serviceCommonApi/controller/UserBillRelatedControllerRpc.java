package org.leafbook.serviceCommonApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.userInfo.UserBillModel;
import org.leafbook.serviceCommonApi.service.UserBillRelatedServiceRpc;
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
     * 获取用户月增长
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/user/balance/income")
    @PostMapping("/rpc/post/select/user/balance/income")
    public Long postSelectUserBalanceIncomeForMonthRpc(@RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserBalanceIncomeForMonth(userId);
    }
    /**
     * 获取用户月支出
     * @param userId
     * @returnE
     */
    @ApiOperation("/rpc/post/select/user/balance/expenditure")
    @PostMapping("/rpc/post/select/user/balance/expenditure")
    public Long postSelectUserBalanceExpenditureForMonthRpc(@RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserBalanceExpenditureForMonth(userId);
    }

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
    /**
     * 获取用户赞相关的总收支
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/user/star/incomeAndExpenditure/amount")
    @PostMapping("/rpc/post/select/user/star/incomeAndExpenditure/amount")
    public Long postSelectUserStarRelatedIncomeAndExpenditureAmountRpc(@RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserStarRelatedIncomeAndExpenditureAmount(userId);
    }

    /**
     * 获取用户赞总收入
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/user/star/income/amount")
    @PostMapping("/rpc/post/select/user/star/income/amount")
    public Long postSelectUserStarRelatedIncomeAmountRpc(@RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserStarRelatedIncomeAmount(userId);
    }
    /**
     * 获取用户赞总支出
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/user/star/expenditure/amount")
    @PostMapping("/rpc/post/select/user/star/expenditure/amount")
    public Long postSelectUserStarRelatedExpenditureAmountRpc(@RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserStarRelatedExpenditureAmountRpc(userId);
    }

    /**
     * 获取用户著述相关的收支
     * @param userId
     * @param page
     * @return
     */
    @ApiOperation("/rpc/post/select/topic/incomeAndExpenditure")
    @PostMapping("/rpc/post/select/topic/incomeAndExpenditure")
    public List<UserBillModel> postSelectTopicRelatedIncomeAndExpenditureRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page) {
        return userBillRelatedServiceRpc.postSelectTopicRelatedIncomeAndExpenditure(userId,page);
    }

    /**
     * 获取用户著述相关的总收支
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/user/topic/incomeAndExpenditure/amount")
    @PostMapping("/rpc/post/select/user/topic/incomeAndExpenditure/amount")
    public Long postSelectUserTopicRelatedIncomeAndExpenditureAmountRpc(@RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserTopicRelatedIncomeAndExpenditureAmount(userId);
    }

    /**
     * 获取用户著述总收入
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/user/topic/income/amount")
    @PostMapping("/rpc/post/select/user/topic/income/amount")
    public Long postSelectUserTopicRelatedIncomeAmountRpc(@RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserTopicRelatedIncomeAmount(userId);
    }
    /**
     * 获取用户著述总支出
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/user/topic/expenditure/amount")
    @PostMapping("/rpc/post/select/user/topic/expenditure/amount")
    public Long postSelectUserTopicRelatedExpenditureAmountRpc(@RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserTopicRelatedExpenditureAmount(userId);
    }

    /**
     * 获取用户购入卖出相关的收支
     * @param userId
     * @param page
     * @returnb
     */
    @ApiOperation("/rpc/post/select/buyAndSell/incomeAndExpenditure")
    @PostMapping("/rpc/post/select/buyAndSell/incomeAndExpenditure")
    public List<UserBillModel> postSelectBuyAndSellRelatedIncomeAndExpenditureRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page) {
        return userBillRelatedServiceRpc.postSelectBuyAndSellRelatedIncomeAndExpenditure(userId,page);
    }

    /**
     * 获取用户购入卖出相关的总收支
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/user/buyAndSell/incomeAndExpenditure/amount")
    @PostMapping("/rpc/post/select/user/buyAndSell/incomeAndExpenditure/amount")
    public Long postSelectUserBuyAndSellRelatedIncomeAndExpenditureAmountRpc(@RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserBuyAndSellRelatedIncomeAndExpenditureAmount(userId);
    }

    /**
     * 获取用户买卖总收入
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/user/buyAndSell/income/amount")
    @PostMapping("/rpc/post/select/user/buyAndSell/income/amount")
    public Long postSelectUserBuyAndSellRelatedIncomeAmountRpc(@RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserBuyAndSellRelatedIncomeAmount(userId);
    }
    /**
     * 获取买卖总支出
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/user/buyAndSell/expenditure/amount")
    @PostMapping("/rpc/post/select/user/buyAndSell/expenditure/amount")
    public Long postSelectUserBuyAndSellRelatedExpenditureAmountRpc(@RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserBuyAndSellRelatedExpenditureAmount(userId);
    }

    /**
     * 买卖相关数据条数
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/user/buyAndSell/incomeAndExpenditure/page")
    @PostMapping("/rpc/post/select/user/buyAndSell/incomeAndExpenditure/page")
    public Long postSelectUserBuyAndSellRelatedIncomeAndExpenditurePageAmountRpc(
            @RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserBuyAndSellRelatedIncomeAndExpenditurePageAmount(userId);
    }
    /**
     * topic相关数据条数
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/user/topic/incomeAndExpenditure/page")
    @PostMapping("/rpc/post/select/user/topic/incomeAndExpenditure/page")
    public Long postSelectUserTopicRelatedIncomeAndExpenditurePageAmountRpc(
            @RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserTopicRelatedIncomeAndExpenditurePageAmount(userId);
    }
    /**
     * 赞相关数据条数
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/user/star/incomeAndExpenditure/page")
    @PostMapping("/rpc/post/select/user/star/incomeAndExpenditure/page")
    public Long postSelectUserStarRelatedIncomeAndExpenditurePageAmountRpc(
            @RequestParam("userId") Long userId) {
        return userBillRelatedServiceRpc.postSelectUserStarRelatedIncomeAndExpenditurePageAmount(userId);
    }
}
