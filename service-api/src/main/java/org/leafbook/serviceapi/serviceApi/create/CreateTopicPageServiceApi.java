package org.leafbook.serviceapi.serviceApi.create;

import org.leafbook.api.respAbs.createTopicPage.EntryAbs;
import org.leafbook.api.testModel.createTopicPage.CreateTopicTestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateTopicPageServiceApi {
    public List<EntryAbs> getSelectNonofficialEntryInfos() {
        return CreateTopicTestModel.createEntryAbsList();
    }

    public List<EntryAbs> getSelectOfficialEntryInfos() {
        return CreateTopicTestModel.createEntryAbsList();
    }
}
