package org.leafbook.serviceCommonApi.service;

import org.leafbook.api.modelApi.userInfo.UserBillModel;
import org.leafbook.serviceCommonApi.daoImpl.UserBillAmountModelMapperImpl;
import org.leafbook.serviceCommonApi.daoImpl.UserBillModelMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class UserBillRelatedServiceRpc {
    @Autowired
    private UserBillModelMapperImpl userBillModelMapperImpl;
    @Autowired
    private UserBillAmountModelMapperImpl userBillAmountModelMapperImpl;

    /**
     * 获取用户月增长
     *
     * @param userId
     * @return
     */
    public Long postSelectUserBalanceIncomeForMonth(Long userId) {
        //获取当前时间，查询上个月增长
        Long endTime = new Date().getTime();
        Long startTime = endTime - (30L * 24 * 3600 * 1000);
        List<UserBillModel> modelList = userBillModelMapperImpl.selectBalanceMonthIncome(userId, startTime, endTime);
        if (Objects.nonNull(modelList) && !modelList.isEmpty()) {
            Long ret = 0L;
            for (UserBillModel userBillModel : modelList) {
                if (userBillModel.getPosition() == 0) {
                    ret += userBillModel.getPrice();
                }
            }
            return ret;
        } else {
            return 0L;
        }
    }

    /**
     * 获取用户月支出
     *
     * @param userId
     * @returnE
     */
    public Long postSelectUserBalanceExpenditureForMonth(Long userId) {
        //获取当前时间，查询上个月支出
        Long endTime = new Date().getTime();
        Long startTime = endTime - (30L * 24 * 3600 * 1000);
        List<UserBillModel> modelList = userBillModelMapperImpl.selectBalanceMonthIncome(userId, startTime, endTime);
        if (Objects.nonNull(modelList) && !modelList.isEmpty()) {
            Long ret = 0L;
            for (UserBillModel userBillModel : modelList) {
                if (userBillModel.getPosition() == 1) {
                    ret += userBillModel.getPrice();
                }
            }
            return ret;
        } else {
            return 0L;
        }
    }

    /**
     * 获取用户赞相关的收支
     *
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> postSelectStarRelatedIncomeAndExpenditure(Long userId, Long page) {
        return userBillModelMapperImpl.selectMultiStarRelatedByUserId(userId, page);
    }

    /**
     * 获取用户赞相关的总收支
     *
     * @param userId
     * @return
     */
    public Long postSelectUserStarRelatedIncomeAndExpenditureAmount(Long userId) {
        return userBillAmountModelMapperImpl.selectStarIncomeAndExpenditureAmount(userId);
    }

    /**
     * 获取用户赞总收入
     *
     * @param userId
     * @return
     */
    public Long postSelectUserStarRelatedIncomeAmount(Long userId) {
        return userBillAmountModelMapperImpl.selectStarIncomeAmount(userId);
    }

    /**
     * 获取用户赞总支出
     *
     * @param userId
     * @return
     */
    public Long postSelectUserStarRelatedExpenditureAmountRpc(Long userId) {
        return userBillAmountModelMapperImpl.selectStarExpenditureAmount(userId);
    }

    /**
     * 获取用户著述相关的收支
     *
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> postSelectTopicRelatedIncomeAndExpenditure(Long userId, Long page) {
        return userBillModelMapperImpl.selectMultiTopicRelatedByUserId(userId, page);
    }

    /**
     * 获取用户著述相关的总收支
     *
     * @param userId
     * @return
     */
    public Long postSelectUserTopicRelatedIncomeAndExpenditureAmount(Long userId) {
        return userBillAmountModelMapperImpl.selectTopicIncomeAndExpenditureAmount(userId);
    }

    /**
     * 获取用户著述总收入
     *
     * @param userId
     * @return
     */
    public Long postSelectUserTopicRelatedIncomeAmount(Long userId) {
        return userBillAmountModelMapperImpl.selectTopicIncomeAmount(userId);
    }

    /**
     * 获取用户著述总支出
     *
     * @param userId
     * @return
     */
    public Long postSelectUserTopicRelatedExpenditureAmount(Long userId) {
        return userBillAmountModelMapperImpl.selectTopicExpenditureAmount(userId);
    }


    /**
     * 获取用户购入卖出相关的收支
     *
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> postSelectBuyAndSellRelatedIncomeAndExpenditure(Long userId, Long page) {
        return userBillModelMapperImpl.selectMultiBuyAndSellRelatedByUserId(userId, page);
    }

    /**
     * 获取用户购入卖出相关的总支收支
     *
     * @param userId
     * @return
     */
    public Long postSelectUserBuyAndSellRelatedIncomeAndExpenditureAmount(Long userId) {
        return userBillAmountModelMapperImpl.selectBuyAndSellIncomeAndExpenditureAmount(userId);
    }

    /**
     * 获取用户买卖总收入
     *
     * @param userId
     * @return
     */
    public Long postSelectUserBuyAndSellRelatedIncomeAmount(Long userId) {
        return userBillAmountModelMapperImpl.selectBuyAndSellIncomeAmount(userId);
    }

    /**
     * 获取用户买卖总支出
     *
     * @param userId
     * @return
     */
    public Long postSelectUserBuyAndSellRelatedExpenditureAmount(Long userId) {
        return userBillAmountModelMapperImpl.selectBuyAndSellExpenditureAmount(userId);
    }
    /**
     * 买卖相关数据条数
     * @param userId
     * @return
     */
    public Long postSelectUserBuyAndSellRelatedIncomeAndExpenditurePageAmount(Long userId) {
        return userBillModelMapperImpl.selectMultiBuyAndSellRelatedAmountByUserId(userId);
    }
    /**
     * topic相关数据条数
     * @param userId
     * @return
     */
    public Long postSelectUserTopicRelatedIncomeAndExpenditurePageAmount(Long userId) {
        return userBillModelMapperImpl.selectMultiTopicRelatedAmountByUserId(userId);
    }
    /**
     * 赞相关数据条数
     * @param userId
     * @return
     */
    public Long postSelectUserStarRelatedIncomeAndExpenditurePageAmount(Long userId) {
        return userBillModelMapperImpl.selectMultiStarRelatedAmountByUserId(userId);
    }

}
