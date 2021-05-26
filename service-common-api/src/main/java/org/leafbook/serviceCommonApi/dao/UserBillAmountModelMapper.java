package org.leafbook.serviceCommonApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.userInfo.UserBillAmountModel;
import org.leafbook.api.modelApi.userInfo.UserBillModel;

@Mapper
public interface UserBillAmountModelMapper extends BaseMapper<UserBillModel> {
    UserBillAmountModel selectSingleUserBillByUserId(@Param("userId")Long userId);
}
