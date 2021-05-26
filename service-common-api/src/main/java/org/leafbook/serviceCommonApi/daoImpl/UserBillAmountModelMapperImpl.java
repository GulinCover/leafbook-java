package org.leafbook.serviceCommonApi.daoImpl;

import org.leafbook.serviceCommonApi.dao.UserBillAmountModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBillAmountModelMapperImpl {
    @Autowired
    private UserBillAmountModelMapper userBillAmountModelMapper;

    /**
     * 获取用户赞相关的总收支
     *
     * @param userId
     * @return
     */
    public Long selectStarIncomeAndExpenditureAmount(Long userId) {
        return userBillAmountModelMapper.selectSingleUserBillByUserId(userId).getStarIncomeAndExpenditureAmount();
    }

    /**
     * 获取用户赞收入
     *
     * @param userId
     * @return
     */
    public Long selectStarIncomeAmount(Long userId) {
        return userBillAmountModelMapper.selectSingleUserBillByUserId(userId).getStarIncomeAmount();
    }

    /**
     * 获取用户赞支出
     *
     * @param userId
     * @return
     */
    public Long selectStarExpenditureAmount(Long userId) {
        return userBillAmountModelMapper.selectSingleUserBillByUserId(userId).getStarExpenditureAmount();
    }

    /**
     * 获取用户著述相关的总收支
     *
     * @param userId
     * @return
     */
    public Long selectTopicIncomeAndExpenditureAmount(Long userId) {
        return userBillAmountModelMapper.selectSingleUserBillByUserId(userId).getTopicIncomeAndExpenditureAmount();
    }

    /**
     * 获取用户著述收入
     *
     * @param userId
     * @return
     */
    public Long selectTopicIncomeAmount(Long userId) {
        return userBillAmountModelMapper.selectSingleUserBillByUserId(userId).getTopicIncomeAmount();
    }

    /**
     * 获取用户著述支出
     *
     * @param userId
     * @return
     */
    public Long selectTopicExpenditureAmount(Long userId) {
        return userBillAmountModelMapper.selectSingleUserBillByUserId(userId).getTopicExpenditureAmount();
    }

    /**
     * 获取用户购入卖出相关的总收支
     *
     * @param userId
     * @return
     */
    public Long selectBuyAndSellIncomeAndExpenditureAmount(Long userId) {
        return userBillAmountModelMapper.selectSingleUserBillByUserId(userId).getBuyAndSellIncomeAndExpenditureAmount();
    }

    /**
     * 获取用户买卖收入
     *
     * @param userId
     * @return
     */
    public Long selectBuyAndSellIncomeAmount(Long userId) {
        return userBillAmountModelMapper.selectSingleUserBillByUserId(userId).getBuyAndSellIncomeAmount();
    }

    /**
     * 获取用户买卖支出
     *
     * @param userId
     * @return
     */
    public Long selectBuyAndSellExpenditureAmount(Long userId) {
        return userBillAmountModelMapper.selectSingleUserBillByUserId(userId).getBuyAndSellExpenditureAmount();
    }

}
