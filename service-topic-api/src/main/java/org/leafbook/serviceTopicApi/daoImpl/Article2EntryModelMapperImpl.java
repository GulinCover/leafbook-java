package org.leafbook.serviceTopicApi.daoImpl;

import org.leafbook.api.modelApi.topicInfo.articleInfo.Article2EntryModel;
import org.leafbook.serviceTopicApi.dao.Article2EntryModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Article2EntryModelMapperImpl {
    @Autowired
    private Article2EntryModelMapper article2EntryModelMapper;

    public int insert(Article2EntryModel article2EntryModel) {
        return article2EntryModelMapper.insert(article2EntryModel);
    }

    public int insertMulti(Long articleId, List<Long> entryIds) {
        return article2EntryModelMapper.insertMultiEntryIds(articleId,entryIds,new Date().getTime());
    }
}
