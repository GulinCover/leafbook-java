package org.leafbook.serviceUserApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.common.CodeModel;

@Mapper
public interface CodeModelMapper extends BaseMapper<CodeModel> {
    String selectByPhone(
            @Param("userId")Long userId,
            @Param("phone")String phone);
    String selectByEmail(
            @Param("userId")Long userId,
            @Param("email")String email);

    String selectCodeInsideByPhone(
            @Param("userId")Long userId,
            @Param("phone")String phone,
            @Param("start")Long start,
            @Param("end")Long end);
    String selectCodeInsideByEmail(
            @Param("userId")Long userId,
            @Param("email")String email,
            @Param("start")Long start,
            @Param("end")Long end);
}
