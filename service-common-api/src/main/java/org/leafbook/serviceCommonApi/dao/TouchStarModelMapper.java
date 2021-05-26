package org.leafbook.serviceCommonApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.common.StarModel;

@Mapper
public interface TouchStarModelMapper extends BaseMapper<StarModel> {
    Integer selectDetectIsExist(
            @Param("userId")Long userId,
            @Param("objectId")Long objectId,
            @Param("type")String type
    );
}
