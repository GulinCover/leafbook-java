package org.leafboot.serviceCommonApi.dao;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserBillAmountModelMapper {
    /**
     * 获取用户赞相关的总收支
     * @param userId
     * @return
     */
    public Long selectStarIncomeAndExpenditureAmount(Long userId) {
        return new Random().nextInt(2) == 1 ? (long) new Random().nextInt(5000) : -(long) new Random().nextInt(5000);
    }
    /**
     * 获取用户赞收入
     * @param userId
     * @return
     */
    public Long selectStarIncomeAmount(Long userId) {
        return (long) new Random().nextInt(5000);
    }
    /**
     * 获取用户赞支出
     * @param userId
     * @return
     */
    public Long selectStarExpenditureAmount(Long userId) {
        return (long) new Random().nextInt(5000);
    }
    /**
     * 获取用户著述相关的总收支
     * @param userId
     * @return
     */
    public Long selectTopicIncomeAndExpenditureAmount(Long userId) {
        return new Random().nextInt(2) == 1 ? (long) new Random().nextInt(5000) : -(long) new Random().nextInt(5000);
    }
    /**
     * 获取用户著述收入
     * @param userId
     * @return
     */
    public Long selectTopicIncomeAmount(Long userId) {
        return (long) new Random().nextInt(5000);
    }
    /**
     * 获取用户著述支出
     * @param userId
     * @return
     */
    public Long selectTopicExpenditureAmount(Long userId) {
        return (long) new Random().nextInt(5000);
    }
    /**
     * 获取用户购入卖出相关的总收支
     * @param userId
     * @return
     */
    public Long selectBuyAndSellIncomeAndExpenditureAmount(Long userId) {
        return new Random().nextInt(2) == 1 ? (long) new Random().nextInt(5000) : -(long) new Random().nextInt(5000);
    }
    /**
     * 获取用户买卖收入
     * @param userId
     * @return
     */
    public Long selectBuyAndSellIncomeAmount(Long userId) {
        return (long) new Random().nextInt(5000);
    }
    /**
     * 获取用户买卖支出
     * @param userId
     * @return
     */
    public Long selectBuyAndSellExpenditureAmount(Long userId) {
        return (long) new Random().nextInt(5000);
    }

}
