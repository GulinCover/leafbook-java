package org.leafbook.serviceapi.serviceApi.repository;

import org.leafbook.api.respAbs.repository.publicTopicPage.EntryAbs;
import org.leafbook.api.respAbs.repository.publicTopicPage.PublicTopicAbs;
import org.leafbook.api.testModel.repositoryPage.RepositoryTestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicTopicPageServiceApi {

    public List<PublicTopicAbs> postSelectPublicTopicInfo() {
        return RepositoryTestModel.createPublicTopicAbsList();
    }

    public List<EntryAbs> postSelectPublicTopicInfoAllEntryList() {
        return RepositoryTestModel.createPublicTopicAllEntryList();
    }
}
