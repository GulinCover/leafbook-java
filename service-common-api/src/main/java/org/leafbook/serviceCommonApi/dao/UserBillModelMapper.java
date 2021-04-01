package org.leafbook.serviceCommonApi.dao;

import org.leafbook.api.modelApi.userInfo.UserBillModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class UserBillModelMapper {
    /**
     * 获取用户月增长
     * @param userId
     * @return
     */
    public Long selectBalanceMonthIncome(Long userId) {
        //获取当前时间，查询上个月增长
        return (long) new Random().nextInt(5000);
    }
    /**
     * 获取用户月支出
     * @param userId
     * @returnE
     */
    public Long selectBalanceMonthExpenditure(Long userId) {
        //获取当前时间，查询上个月支出

        return (long) new Random().nextInt(5000);
    }
    /**
     * 获取用户赞相关的收支
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> selectMultiStarRelatedByUserId(Long userId, Long page) {
        List<UserBillModel> userBillModelList = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            UserBillModel userBillModel = new UserBillModel();
            userBillModel.setBehavior(0);
            userBillModel.setPosition(0);
            userBillModel.setPrice(2L);
            userBillModel.setTopicId(123L);
            userBillModel.setUserBillId((long) i);

            userBillModelList.add(userBillModel);
        }

        return userBillModelList;
    }
    /**
     * 获取用户著述相关的收支
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> selectMultiTopicRelatedByUserId(Long userId, Long page) {
        List<UserBillModel> userBillModelList = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            UserBillModel userBillModel = new UserBillModel();
            userBillModel.setBehavior(1);
            userBillModel.setPosition(0);
            userBillModel.setPrice(2L);
            userBillModel.setTopicId(123L);
            userBillModel.setUserBillId((long) i);

            userBillModelList.add(userBillModel);
        }

        return userBillModelList;
    }
    /**
     * 获取用户赞相关的收支
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> selectMultiBuyAndSellRelatedByUserId(Long userId, Long page) {
        List<UserBillModel> userBillModelList = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            UserBillModel userBillModel = new UserBillModel();
            userBillModel.setBehavior(2);
            userBillModel.setPosition(0);
            userBillModel.setPrice(2321L);
            userBillModel.setBillId(123L);
            userBillModel.setUserBillId((long) i);

            userBillModelList.add(userBillModel);
        }

        return userBillModelList;
    }

}
