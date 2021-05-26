package org.leafbook.serviceRecordApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.recordInfo.SearchHistoryModel;

import java.util.List;

@Mapper
public interface SearchHistoryModelMapper extends BaseMapper<SearchHistoryModel> {
    List<SearchHistoryModel> selectMultiLatestByUserId(@Param("userId")Long userId,@Param("number")Long number);
}
