package org.leafboot.serviceCommonApi.service;

import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.userInfo.UserBillModel;
import org.leafbook.api.respAbs.userManagerPage.StarRelatedIncomeAndExpenditureAbs;
import org.leafboot.serviceCommonApi.dao.UserBillAmountModelMapper;
import org.leafboot.serviceCommonApi.dao.UserBillModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class UserBillRelatedServiceRpc {
    @Autowired
    private UserBillModelMapper userBillModelMapper;
    @Autowired
    private UserBillAmountModelMapper userBillAmountModelMapper;
    /**
     * 获取用户月增长
     * @param userId
     * @return
     */
    public Long postSelectUserBalanceIncomeForMonth(Long userId) {
        return userBillModelMapper.selectBalanceMonthIncome(userId);
    }
    /**
     * 获取用户月支出
     * @param userId
     * @returnE
     */
    public Long postSelectUserBalanceExpenditureForMonth(Long userId) {
        return userBillModelMapper.selectBalanceMonthExpenditure(userId);
    }
    /**
     * 获取用户赞相关的收支
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> postSelectStarRelatedIncomeAndExpenditure(Long userId, Long page) {
        return userBillModelMapper.selectMultiStarRelatedByUserId(userId,page);
    }
    /**
     * 获取用户赞相关的总收支
     * @param userId
     * @return
     */
    public Long postSelectUserStarRelatedIncomeAndExpenditureAmount(Long userId) {
        return userBillAmountModelMapper.selectStarIncomeAndExpenditureAmount(userId);
    }
    /**
     * 获取用户赞总收入
     * @param userId
     * @return
     */
    public Long postSelectUserStarRelatedIncomeAmount(Long userId) {
        return userBillAmountModelMapper.selectStarIncomeAmount(userId);
    }
    /**
     * 获取用户赞总支出
     * @param userId
     * @return
     */
    public Long postSelectUserStarRelatedExpenditureAmountRpc(Long userId) {
        return userBillAmountModelMapper.selectStarExpenditureAmount(userId);
    }

    /**
     * 获取用户著述相关的收支
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> postSelectTopicRelatedIncomeAndExpenditure(Long userId, Long page) {
        return userBillModelMapper.selectMultiTopicRelatedByUserId(userId,page);
    }

    /**
     * 获取用户著述相关的总支出
     * @param userId
     * @return
     */
    public Long postSelectUserTopicRelatedIncomeAndExpenditureAmount(Long userId) {
        return userBillAmountModelMapper.selectTopicIncomeAndExpenditureAmount(userId);
    }

    /**
     * 获取用户著述总收入
     * @param userId
     * @return
     */
    public Long postSelectUserTopicRelatedIncomeAmount(Long userId) {
        return userBillAmountModelMapper.selectTopicIncomeAmount(userId);
    }
    /**
     * 获取用户著述总支出
     * @param userId
     * @return
     */
    public Long postSelectUserTopicRelatedExpenditureAmount(Long userId) {
        return userBillAmountModelMapper.selectTopicExpenditureAmount(userId);
    }



    /**
     * 获取用户赞相关的收支
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> postSelectBuyAndSellRelatedIncomeAndExpenditure(Long userId, Long page) {
        return userBillModelMapper.selectMultiBuyAndSellRelatedByUserId(userId,page);
    }

    /**
     * 获取用户购入卖出相关的总支出
     * @param userId
     * @return
     */
    public Long postSelectUserBuyAndSellRelatedIncomeAndExpenditureAmount(Long userId) {
        return userBillAmountModelMapper.selectBuyAndSellIncomeAndExpenditureAmount(userId);
    }

    /**
     * 获取用户买卖总收入
     * @param userId
     * @return
     */
    public Long postSelectUserBuyAndSellRelatedIncomeAmount(Long userId) {
        return userBillAmountModelMapper.selectBuyAndSellIncomeAmount(userId);
    }
    /**
     * 获取用户买卖总支出
     * @param userId
     * @return
     */
    public Long postSelectUserBuyAndSellRelatedExpenditureAmount(Long userId) {
        return userBillAmountModelMapper.selectBuyAndSellExpenditureAmount(userId);
    }

}
