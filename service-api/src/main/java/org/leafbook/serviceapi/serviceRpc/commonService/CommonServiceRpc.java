package org.leafbook.serviceapi.serviceRpc.commonService;

import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.userInfo.UserBillModel;
import org.leafbook.serviceapi.openfeinFallback.commonService.CommonServiceRpcFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        value = "service-common-api",
        fallbackFactory = CommonServiceRpcFallback.class
)
public interface CommonServiceRpc {

    /**
     * 获取站点协议
     *
     * @return
     */
    @GetMapping("/rpc/get/select/policy")
    String getSelectPolicyRpc();

    /**
     * 点赞
     *
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    @PostMapping("/rpc/post/insert/touch/star")
    int postInsertTouchStarRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("objectId") Long objectId,
            @RequestParam("type") String type);

    /**
     * 点踩
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    @PostMapping("/rpc/post/insert/touch/star")
    int postInsertTouchTreadRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("objectId")Long objectId,
            @RequestParam("type")String type);
    /**
     * 查看是否点过赞
     *
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    @PostMapping("/rpc/post/select/touched/star")
    int postSelectTouchedStarRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("objectId") Long objectId,
            @RequestParam("type") String type);

    /**
     * 查看是否点过踩
     *
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    @PostMapping("/rpc/post/select/touched/tread")
    int postSelectTouchedTreadRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("objectId") Long objectId,
            @RequestParam("type") String type);


    /**
     * 发送验证码
     *
     * @param email
     * @return
     */
    @PostMapping("/rpc/post/send/code/by/email")
    int postSendCodeRpc(@RequestParam("email") String email);

    /**
     * 获取验证码
     *
     * @param email
     * @return
     */
    @PostMapping("/rpc/post/acquire/code/by/email")
    String postAcquireCodeRpc(@RequestParam("email") String email);

    /**
     * 获取用户赞相关的收支
     * @param userId
     * @param page
     * @return
     */
    @PostMapping("/rpc/post/select/star/incomeAndExpenditure")
    List<UserBillModel> postSelectStarRelatedIncomeAndExpenditureRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page);

    /**
     * 获取用户赞相关的总支出
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/user/star/incomeAndExpenditure/amount")
    Long postSelectUserStarRelatedIncomeAndExpenditureAmountRpc(@RequestParam("userId") Long userId);
    /**
     * 获取用户赞总收入
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/user/star/income/amount")
    Long postSelectUserStarRelatedIncomeAmountRpc(@RequestParam("userId") Long userId);
    /**
     * 获取用户赞总支出
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/user/star/expenditure/amount")
    Long postSelectUserStarRelatedExpenditureAmountRpc(@RequestParam("userId") Long userId);
    /**
     * 获取用户著述相关的收支
     * @param userId
     * @param page
     * @return
     */
    @PostMapping("/rpc/post/select/topic/incomeAndExpenditure")
    List<UserBillModel> postSelectTopicRelatedIncomeAndExpenditureRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page);

    /**
     * 获取用户著述相关的总收支
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/user/topic/incomeAndExpenditure/amount")
    Long postSelectUserTopicRelatedIncomeAndExpenditureAmountRpc(@RequestParam("userId") Long userId);

    /**
     * 获取用户著述总收入
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/user/topic/income/amount")
    Long postSelectUserTopicRelatedIncomeAmountRpc(@RequestParam("userId") Long userId);
    /**
     * 获取用户著述总支出
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/user/topic/expenditure/amount")
    Long postSelectUserTopicRelatedExpenditureAmountRpc(@RequestParam("userId") Long userId);
    /**
     * 获取用户购入卖出相关的收支
     * @param userId
     * @param page
     * @returnb
     */
    @PostMapping("/rpc/post/select/buyAndSell/incomeAndExpenditure")
    List<UserBillModel> postSelectBuyAndSellRelatedIncomeAndExpenditureRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page);

    /**
     * 获取用户购入卖出相关的总收支
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/user/buyAndSell/incomeAndExpenditure/amount")
    Long postSelectUserBuyAndSellRelatedIncomeAndExpenditureAmountRpc(@RequestParam("userId") Long userId);

    /**
     * 获取用户买卖总收入
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/user/buyAndSell/income/amount")
    Long postSelectUserBuyAndSellRelatedIncomeAmountRpc(@RequestParam("userId") Long userId);
    /**
     * 获取买卖总支出
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/user/buyAndSell/expenditure/amount")
    Long postSelectUserBuyAndSellRelatedExpenditureAmountRpc(@RequestParam("userId") Long userId);

    /**
     * 获取用户月增长
     * @param userId
     * @return
     */
    @PostMapping("/api/post/select/user/balance/income")
    Long postSelectUserBalanceIncomeForMonthRpc(@RequestParam("userId") Long userId);
    /**
     * 获取用户月支出
     * @param userId
     * @returnE
     */
    @PostMapping("/api/post/select/user/balance/expenditure")
    Long postSelectUserBalanceExpenditureForMonthRpc(@RequestParam("userId") Long userId);
}
