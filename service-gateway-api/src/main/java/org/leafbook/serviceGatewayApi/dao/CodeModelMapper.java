package org.leafbook.serviceGatewayApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.common.CSRFCodeModel;

@Mapper
public interface CodeModelMapper extends BaseMapper<CSRFCodeModel> {
    Integer selectDetectIsExist(
            @Param("csrf")String csrf,
            @Param("startTime")Long startTime,
            @Param("endTime")Long endTime
    );
}
