package org.leafbook.serviceEntryApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.entryInfo.Entry2StarAndTreadAmountModel;

import java.util.List;

@Mapper
public interface Entry2StarAndTreadAmountModelMapper extends BaseMapper<Entry2StarAndTreadAmountModel> {
    Entry2StarAndTreadAmountModel selectSingleEntryStarAndTreadAmountByEntryId(@Param("entryId")Long entryId);

    List<Entry2StarAndTreadAmountModel> selectMultiEntryStarAndTreadAmountByEntryId(@Param("entryIds")List<Long> entryIds);

    int updateStarIncreaseByEntryId(
            @Param("entryId")Long entryId,
            @Param("time")Long time
    );

    int updateTreadIncreaseByEntryId(
            @Param("entryId")Long entryId,
            @Param("time")Long time
    );
}
