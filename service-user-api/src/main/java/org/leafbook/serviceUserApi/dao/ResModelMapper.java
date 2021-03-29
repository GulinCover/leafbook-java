package org.leafbook.serviceUserApi.dao;

import org.leafbook.api.modelApi.billInfo.ResModel;
import org.springframework.stereotype.Service;

@Service
public class ResModelMapper {
    /**
     * 获取物品信息
     * @param userId
     * @return
     */
    public ResModel selectSingleResInfo(Long userId) {
        ResModel resModel = new ResModel();
        resModel.setPrice(312L);
        resModel.setResId(231L);
        resModel.setTopicId(userId);
        resModel.setType(0);
        resModel.setTopicId(412L);

        return resModel;
    }
}
