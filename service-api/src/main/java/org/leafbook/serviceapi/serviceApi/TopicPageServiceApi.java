package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.respAbs.topicPage.EntryAbs;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicPageServiceApi {
    public List<EntryAbs> getSelectRecommendedEntriesInfo() {
        return TestModel.createRecommendedEntryAbsList();
    }

    public List<EntryAbs> getSelectAllEntriesInfo(String page) {
        return TestModel.createAllEntryAbsList();
    }
}
