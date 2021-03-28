package org.leafbook.serviceapi.serviceApi.userManager;

import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.respAbs.userManagerPage.RepositoryManagerTopicAbs;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class RepositoryManagerPageServiceApi {
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    /**
     * 获取用户已发布的著述
     * @param userId
     * @return
     */
    public List<RepositoryManagerTopicAbs> postSelectMultiMeTopicInfo(Long userId) {
        List<RepositoryManagerTopicAbs> repositoryManagerTopicAbsList = new LinkedList<>();
        List<TopicModel> topicModelList = topicServiceRpc.postSelectMeTopicInfoRpc(userId);
        if (Objects.nonNull(topicModelList) && !topicModelList.isEmpty()) {
            for (TopicModel topicModel:topicModelList) {
                RepositoryManagerTopicAbs topicAbs = new RepositoryManagerTopicAbs();
                topicAbs.setTopicId(topicModel.getTopicId());
                topicAbs.setStartAmount(topicServiceRpc.getSelectTopicStarAmountRpc(topicModel.getTopicId()));
                topicAbs.setCommentAmount(topicServiceRpc.getSelectAllCommentAmountRpc(topicModel.getTopicId()));
                topicAbs.setContributorAmount(topicServiceRpc.getSelectContributorAmountByTopicIdRpc(topicModel.getTopicId()));
                topicAbs.setTopicTitle(topicModel.getTopicTitle());

                repositoryManagerTopicAbsList.add(topicAbs);
            }
        }

        return repositoryManagerTopicAbsList;
    }
}
