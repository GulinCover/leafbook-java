package org.leafbook.serviceCommonApi.daoImpl;

import org.leafbook.api.modelApi.userInfo.UserBillModel;
import org.leafbook.serviceCommonApi.dao.UserBillModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBillModelMapperImpl {
    @Autowired
    private UserBillModelMapper userBillModelMapper;

    /**
     * 时间查询
     *
     * @param userId
     * @return
     */
    public List<UserBillModel> selectBalanceMonthIncome(Long userId, Long startTime, Long endTime) {
        return userBillModelMapper.selectMultiUserBillByUserId(userId, startTime, endTime);
    }

    /**
     * 获取用户赞相关的收支
     *
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> selectMultiStarRelatedByUserId(Long userId, Long page) {
        Long end = 20;
        Long start = page * 20;
        return userBillModelMapper.selectMultiUserBillForStarByUserId(userId, start, end);
    }

    /**
     * 获取用户著述相关的收支
     *
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> selectMultiTopicRelatedByUserId(Long userId, Long page) {
        Long end = 20;
        Long start = page * 20;
        return userBillModelMapper.selectMultiUserBillForTopicByUserId(userId, start, end);
    }

    /**
     * 获取用户购入卖出相关的收支
     *
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> selectMultiBuyAndSellRelatedByUserId(Long userId, Long page) {
        Long end = 20;
        Long start = page * 20;
        return userBillModelMapper.selectMultiUserBillForBuyByUserId(userId, start, end);
    }

    /**
     * 买卖相关数据条数
     * @param userId
     * @return
     */
    public Long selectMultiBuyAndSellRelatedAmountByUserId(Long userId) {
        return userBillModelMapper.selectMultiBuyAndSellRelatedAmountByUserId(userId);
    }
    /**
     * topic相关数据条数
     * @param userId
     * @return
     */
    public Long selectMultiTopicRelatedAmountByUserId(Long userId) {
        return userBillModelMapper.selectMultiTopicRelatedAmountByUserId(userId);
    }
    /**
     * 赞相关数据条数
     * @param userId
     * @return
     */
    public Long selectMultiStarRelatedAmountByUserId(Long userId) {
        return userBillModelMapper.selectMultiStarRelatedAmountByUserId(userId);
    }
}
