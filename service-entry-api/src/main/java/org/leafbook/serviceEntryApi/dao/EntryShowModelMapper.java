package org.leafbook.serviceEntryApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;

import java.util.List;

@Mapper
public interface EntryShowModelMapper extends BaseMapper<EntryShowModel> {
    Long selectAllEntryAmount();

    EntryShowModel selectSingleEntryByEntryId(@Param("entryId")Long entryId);

    List<EntryShowModel> selectMultiEntryByEntryIds(@Param("entryIds")List<Long> entryIds);

    List<EntryShowModel> selectMultiEntryByPage(
            @Param("start")Long start,
            @Param("end")Long end
    );

    List<EntryShowModel> selectAllEntry();

    List<EntryShowModel> selectRandomEntry(@Param("number")Long number);
    List<EntryShowModel> selectRandomHotEntry(@Param("number")Long number);
    List<EntryShowModel> selectRandomEntryByType(
            @Param("type")String type,
            @Param("number")Long number
    );
    List<EntryShowModel> selectAllEntryByType(
            @Param("type")String type
    );

    Integer selectSingleDetectIsExistEntry(@Param("entryId")Long entryId);
    Integer selectMultiDetectIsExistEntry(@Param("entryIds")List<Long> entryIds);

    List<EntryShowModel> selectSearchAllEntry(
            @Param("name")String name,
            @Param("type")String type
    );

}











