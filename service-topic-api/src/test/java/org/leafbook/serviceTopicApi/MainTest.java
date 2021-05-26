package org.leafbook.serviceTopicApi;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.leafbook.api.modelApi.topicInfo.TopicEntryInfoShowModel;
import org.leafbook.serviceTopicApi.dao.ArticleLikedAndTreadAndBrowseModelMapper;
import org.leafbook.serviceTopicApi.dao.TopicEntryInfoShowModelMapper;
import org.leafbook.serviceTopicApi.daoImpl.TopicEntryInfoShowModelMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class MainTest {
    @Autowired
    private TopicEntryInfoShowModelMapperImpl topicEntryInfoShowModelMapper;
    @Autowired
    private ArticleLikedAndTreadAndBrowseModelMapper articleLikedAndTreadAndBrowseModelMapper;
    @Test
    void test01() {
        List<Long> ids = new LinkedList<>();
        ids.add(111L);
        ids.add(222L);
        ids.add(333L);
        int ret = topicEntryInfoShowModelMapper.insertByIds(111111L,ids);
        System.out.println(ret);
    }

    @Test
    void test02() {
        int ret = articleLikedAndTreadAndBrowseModelMapper.updateArticleTreadAmountByArticleId(111L);
        System.out.println(ret);
    }
}
