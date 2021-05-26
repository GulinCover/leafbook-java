package org.leafbook.serviceTopicApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.topicInfo.articleInfo.Article2EntryModel;

import java.util.List;

@Mapper
public interface Article2EntryModelMapper extends BaseMapper<Article2EntryModel> {
    int insertMultiEntryIds(
            @Param("articleId")Long article,
            @Param("entryIds")List<Long> entryIds,
            @Param("time")Long time
    );
}
