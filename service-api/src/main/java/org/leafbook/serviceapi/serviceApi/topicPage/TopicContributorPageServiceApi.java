package org.leafbook.serviceapi.serviceApi.topicPage;

import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.topicPublicPage.contributor.TopicContributorAbs;
import org.leafbook.api.respAbs.topicPublicPage.contributor.TopicContributorInfoResp;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class TopicContributorPageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    /**
     * 获取著述所有的贡献者
     * @param userId
     * @param form:topicId,page
     * @return
     */
    public TopicContributorInfoResp postSelectMultiTopicContributorUserInfo(Long userId, Map<String,Long> form) {
        TopicContributorInfoResp resp = new TopicContributorInfoResp();
        List<TopicContributorAbs> topicContributorAbsList = new LinkedList<>();

        final Long topicId = form.get("topicId");
        Long page = form.get("page");

        if (Objects.isNull(topicId)) return resp;
        if (Objects.isNull(page)) page = 1L;

        List<Long> userIds = topicServiceRpc.postSelectMultiContributorIdByTopicIdRpc(topicId, page);
        if (Objects.nonNull(userIds) && !userIds.isEmpty()) {
            List<UserModel> userModelList = userServiceRpc.postSelectMultiUserInfoRpc(userIds);
            if (Objects.nonNull(userModelList) && !userModelList.isEmpty()) {
                for (UserModel userModel:userModelList) {
                    TopicContributorAbs contributorAbs = new TopicContributorAbs();
                    contributorAbs.setUserAvatar(userModel.getAvatar());
                    contributorAbs.setUserDesc(userModel.getDesc());
                    contributorAbs.setUserId(userModel.getId());
                    contributorAbs.setUsername(userModel.getUsername());
                    contributorAbs.setUuid(userModel.getUuid());

                    contributorAbs.setIsAttention(userServiceRpc.postIsExistAttentionRpc(userId, userModel.getId()));

                    topicContributorAbsList.add(contributorAbs);
                }
            }
        }
        resp.setTopicContributorAbsList(topicContributorAbsList);


        Long maxPage = topicServiceRpc.postSelectMultiContributorIdByTopicIdPageRpc(topicId);
        resp.setPage((long)Math.ceil(maxPage / 20));
        return resp;
    }
}
