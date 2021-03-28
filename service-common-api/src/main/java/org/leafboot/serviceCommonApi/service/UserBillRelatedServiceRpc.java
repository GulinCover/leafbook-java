package org.leafboot.serviceCommonApi.service;

import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.userInfo.UserBillModel;
import org.leafbook.api.respAbs.userManagerPage.StarRelatedIncomeAndExpenditureAbs;
import org.leafboot.serviceCommonApi.dao.UserBillModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class UserBillRelatedServiceRpc {
    @Autowired
    private UserBillModelMapper userBillModelMapper;
    /**
     * 获取用户赞相关的收支
     * @param userId
     * @param page
     * @return
     */
    public List<UserBillModel> postSelectStarRelatedIncomeAndExpenditure(Long userId, Long page) {
        return userBillModelMapper.selectMultiByUserId(userId,page);
    }
}
