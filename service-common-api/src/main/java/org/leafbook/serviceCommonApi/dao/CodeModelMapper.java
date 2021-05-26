package org.leafbook.serviceCommonApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.common.CodeModel;

@Mapper
public interface CodeModelMapper extends BaseMapper<CodeModel> {
    String selectEmailCode(
            @Param("email")String email,
            @Param("type")Integer type,
            @Param("time")Long time
    );

    CodeModel selectSingleCodeByEmail(
            @Param("email")String email,
            @Param("type")Integer type,
            @Param("time")Long time
    );


    int deleteLogicCodeByEmail(
            @Param("email")String email,
            @Param("type")Integer type,
            @Param("time")Long time
    );
}
