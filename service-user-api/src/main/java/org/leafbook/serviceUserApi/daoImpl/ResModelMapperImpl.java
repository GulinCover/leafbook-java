package org.leafbook.serviceUserApi.daoImpl;

import org.leafbook.api.modelApi.userInfo.ResModel;
import org.leafbook.serviceUserApi.dao.ResModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class ResModelMapperImpl {
    @Autowired
    private ResModelMapper resModelMapper;
    /**
     * 获取物品信息
     * @param userId
     * @return
     */
    public ResModel selectSingleResInfo(Long userId,Long resId) {
        return resModelMapper.selectByUserIdAndResId(userId,resId);
    }
    /**
     * 查询用户所有可用物品
     * @param userId
     * @return
     */
    public List<ResModel> selectMultiResInfoByUserId(Long userId,Long page) {
        Long end = 20;
        Long start = page * 20;
        return resModelMapper.selectMultiByUserId(userId, start, end);
    }

    /**
     * 查询用户所有可用物品数量
     * @param userId
     * @return
     */
    public Long selectMultiResInfoAmountByUserId(Long userId) {
        return resModelMapper.selectMultiAmountByUserId(userId);
    }


    /**
     * 逻辑单删除
     * @param userId
     * @param resId
     * @return
     */
    public int deleteSingleResInfoByUserId(Long userId,Long resId) {
        return resModelMapper.deleteByLogic(userId, resId);
    }
}
