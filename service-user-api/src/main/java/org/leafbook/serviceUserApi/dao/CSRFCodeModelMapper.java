package org.leafbook.serviceUserApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.common.CSRFCodeModel;

import java.util.List;

@Mapper
public interface CSRFCodeModelMapper extends BaseMapper<CSRFCodeModel> {
    List<String> selectCurrentCSRFCodeByTime(@Param("startTime")Long startTime, @Param("endTime")Long endTime);
}
