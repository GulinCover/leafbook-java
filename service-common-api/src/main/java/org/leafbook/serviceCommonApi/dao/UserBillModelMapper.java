package org.leafbook.serviceCommonApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.userInfo.UserBillModel;

import java.util.List;

@Mapper
public interface UserBillModelMapper extends BaseMapper<UserBillModel> {
    List<UserBillModel> selectMultiUserBillByUserId(
            @Param("userId")Long userId,
            @Param("startTime")Long startTime,
            @Param("endTime")Long endTime
    );
    List<UserBillModel> selectMultiUserBillForStarByUserId(
            @Param("userId")Long userId,
            @Param("start")Long start,
            @Param("end")Long end
    );

    List<UserBillModel> selectMultiUserBillForTopicByUserId(
            @Param("userId")Long userId,
            @Param("start")Long start,
            @Param("end")Long end
    );

    List<UserBillModel> selectMultiUserBillForBuyByUserId(
            @Param("userId")Long userId,
            @Param("start")Long start,
            @Param("end")Long end
    );

    Long selectMultiBuyAndSellRelatedAmountByUserId(@Param("userId")Long userId);
    Long selectMultiTopicRelatedAmountByUserId(@Param("userId")Long userId);
    Long selectMultiStarRelatedAmountByUserId(@Param("userId")Long userId);
}
