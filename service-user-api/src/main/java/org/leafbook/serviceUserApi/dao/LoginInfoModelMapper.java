package org.leafbook.serviceUserApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.userInfo.LoginInfoModel;

import java.util.List;

@Mapper
public interface LoginInfoModelMapper extends BaseMapper<LoginInfoModel> {
    List<LoginInfoModel> selectLoginIngoByLoginMarks(
            @Param("userId") Long userId,
            @Param("loginMarks") List<Long> loginMarks
    );

    int deleteWithLogic(
            @Param("userId") Long userId,
            @Param("loginMark") Long loginMark,
            @Param("time") Long time
    );
}
