package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.respAbs.hotPage.EntryAbs;
import org.leafbook.api.respAbs.hotPage.TopicInfoAbs;
import org.leafbook.api.testModel.hotPage.HotTextModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotPageServiceApi {

    public List<TopicInfoAbs> getSelectAllHotTopicInfos(Integer page) {
        return HotTextModel.createTopicInfosList();
    }

    public List<EntryAbs> getSelectEntryInfoByTopicId(Long topicId) {
        return HotTextModel.createEntryAbsByTopicId();
    }

    public List<EntryAbs> getSelectAllHotEntryListApi() {
        return HotTextModel.createAllHotEntryAbsByTopicId();
    }
}
