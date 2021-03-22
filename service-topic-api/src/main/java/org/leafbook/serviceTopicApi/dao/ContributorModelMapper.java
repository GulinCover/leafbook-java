package org.leafbook.serviceTopicApi.dao;

import org.leafbook.api.modelApi.topicInfo.ContributorModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ContributorModelMapper {
    /**
     * 随机获取贡献者信息
     * @param topicId
     * @param randomNumber
     * @return
     */
    public List<ContributorModel> selectRandomContributorInfoByTopicId(Long topicId,Integer randomNumber) {
        List<ContributorModel> contributorModelList = new LinkedList<>();

        for (int i = 0; i < randomNumber; ++i) {
            ContributorModel contributorModel = new ContributorModel();
            contributorModel.setContributorModelId((long) i);
            contributorModel.setTopicId(topicId);
            contributorModel.setUserId(46L);

            contributorModelList.add(contributorModel);
        }
        return contributorModelList;
    }
}
