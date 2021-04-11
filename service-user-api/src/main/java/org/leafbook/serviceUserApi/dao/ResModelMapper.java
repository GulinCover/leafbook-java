package org.leafbook.serviceUserApi.dao;

import org.leafbook.api.modelApi.userInfo.ResModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class ResModelMapper {
    /**
     * 获取物品信息
     * @param userId
     * @return
     */
    public ResModel selectSingleResInfo(Long userId,Long resId) {
        ResModel resModel = new ResModel();
        resModel.setBillId(312L);
        resModel.setPrice(312L);
        resModel.setResId(231L);
        resModel.setTopicId(userId);
        resModel.setType(0);
        resModel.setStatus(new Random().nextInt(4));
        resModel.setTopicId(412L);

        return resModel;
    }
    /**
     * 查询用户所有可用物品
     * @param userId
     * @return
     */
    public List<ResModel> selectMultiResInfoByUserId(Long userId,Long page) {
        List<ResModel> resModelList = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            resModelList.add(selectSingleResInfo(1L,1L));
        }

        return resModelList;
    }


    /**
     * 逻辑单删除
     * @param userId
     * @param resId
     * @return
     */
    public int deleteSingleResInfoByUserId(Long userId,Long resId) {
        return 1;
    }
}
