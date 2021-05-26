package org.leafbook.serviceUserApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.userInfo.ResModel;

import java.util.List;

@Mapper
public interface ResModelMapper extends BaseMapper<ResModel> {
    ResModel selectByUserIdAndResId(
            @Param("userId")Long userId,
            @Param("resId")Long resId);

    List<ResModel> selectMultiByUserId(
            @Param("userId")Long userId,
            @Param("start")Long start,
            @Param("end")Long end);

    Long selectMultiAmountByUserId(@Param("userId")Long userId);

    int deleteByLogic(
            @Param("userId")Long userId,
            @Param("resId")Long resId);
}
