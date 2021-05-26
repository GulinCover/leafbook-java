package org.leafbook.serviceTopicApi.daoImpl;

import org.leafbook.serviceTopicApi.dao.Article2EntryShowModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

@Service
public class Article2EntryShowModelMapperImpl {
    @Autowired
    private Article2EntryShowModelMapper article2EntryShowModelMapper;
    /**
     * 获取文章展示词条
     *
     * @param articleId
     * @return
     */
    public List<Long> selectMultiEntryIdByArticleId(Long articleId) {
        return article2EntryShowModelMapper.selectMultiEntryIdsByArticleId(articleId);
    }
}
