package org.leafbook.serviceapi.serviceApi.repository;

import org.leafbook.api.respAbs.repository.publicCommentPage.BillAbs;
import org.leafbook.api.respAbs.repository.publicTopicPage.EntryAbs;
import org.leafbook.api.testModel.repositoryPage.RepositoryTestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletPageServiceApi {

    public List<EntryAbs> postSelectPublicTopicInfoAllEntryList() {
        return RepositoryTestModel.createPublicTopicAllEntryList();
    }

    public List<BillAbs> postSelectBillInfos() {
        return RepositoryTestModel.createBillAbsList();
    }
}
