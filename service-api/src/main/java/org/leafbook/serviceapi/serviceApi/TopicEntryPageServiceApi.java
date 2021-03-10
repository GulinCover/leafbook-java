package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.respAbs.topicEntryPage.EntryInfoResp;
import org.leafbook.api.respAbs.topicEntryPage.TopicDetailAbs;
import org.leafbook.api.testModel.topicEntryPage.TopicEntryTestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicEntryPageServiceApi {

    public EntryInfoResp getSelectEntryInfo(Long entryId) {
        return TopicEntryTestModel.createEntryInfo();
    }

    public List<TopicDetailAbs> getSelectTopicInfos() {
        return TopicEntryTestModel.createTopicDetailAbsList();
    }
}
