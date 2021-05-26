package org.leafbook.serviceTopicApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleEntryInfoShowModel;

import java.util.List;

@Mapper
public interface Article2EntryShowModelMapper extends BaseMapper<ArticleEntryInfoShowModel> {
    List<Long> selectMultiEntryIdsByArticleId(@Param("articleId")Long articleId);
}
