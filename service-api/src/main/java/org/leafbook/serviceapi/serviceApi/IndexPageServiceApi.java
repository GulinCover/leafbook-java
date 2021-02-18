package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.respAbs.common.SearchHistoryAbs;
import org.leafbook.api.respAbs.indexPage.BrowseHistoryAbs;
import org.leafbook.api.respAbs.indexPage.RightBrowseHistoryResp;
import org.leafbook.api.respAbs.indexPage.TopicAbs;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IndexPageServiceApi {

    public List<TopicAbs> postSelectUserTopics(Long userId) {
        return TestModel.createTopicAbs();
    }

    public List<SearchHistoryAbs> postSelectMeSearchHistoryTopics(Long userId) {
        return TestModel.createSearchHistoryAbsList();
    }

    public List<BrowseHistoryAbs> postSelectMeBrowseHistoryTopics(Long userId) {
        return TestModel.createBrowseHistoryAbsList();
    }

    public List<TopicAbs> postSelectMeTopics(Map<String, String> form) {
        return TestModel.createTopicAbs();
    }

    public List<TopicAbs> getSelectTopic(String name) {
        return TestModel.createTopicAbs();
    }
}
