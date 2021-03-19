package org.leafbook.serviceTopicApi.dao;

import org.leafbook.api.modelApi.topicInfo.articleInfo.Article2EntryModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Article2EntryModelMapper {
    public int insert(Article2EntryModel article2EntryModel) {
        return new Random().nextInt(100);
    }
}
