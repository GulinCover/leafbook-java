package org.leafbook.serviceEntryApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.entryInfo.EntryModel;

import java.util.List;

@Mapper
public interface EntryModelMapper extends BaseMapper<EntryModel> {
    Integer selectDetectIsExistByEntry(@Param("entryName")String entryName);

    EntryModel selectSingleEntryByEntryId(@Param("entryId")Long entryId);

    List<EntryModel> selectMultiEntryByEntryIds(@Param("entryIds")List<Long> entryIds);

    int deleteSingleForLogic(
            @Param("entryId")Long entryId,
            @Param("time")Long time
    );
    int deleteMultiForLogic(
            @Param("entryIds")List<Long> entryIds,
            @Param("time")Long time
    );
}
