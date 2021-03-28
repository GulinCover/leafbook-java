package org.leafboot.serviceCommonApi.dao;

import org.leafbook.api.modelApi.userInfo.UserBillModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserBillModelMapper {
    /**
     * 获取用户赞相关的收支
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> selectMultiByUserId(Long userId, Long page) {
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
}
