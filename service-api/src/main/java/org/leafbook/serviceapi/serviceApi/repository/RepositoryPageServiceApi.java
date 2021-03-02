package org.leafbook.serviceapi.serviceApi.repository;

import org.leafbook.api.respAbs.repository.repositoryPage.ConsumableInfoAbs;
import org.leafbook.api.testModel.repositoryPage.RepositoryTestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryPageServiceApi {

    public List<ConsumableInfoAbs> postSelectConsumableInfos() {
        return RepositoryTestModel.createConsumableAbsList();
    }
}
