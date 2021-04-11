package org.leafbook.serviceapi.serviceApi.topicPage;

import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.topicPublicPage.manager.TopicManagerAbs;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class TopicManagerPageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    /**
     * 获取著述的所有管理者
     * @param userId
     * @param form:topicId
     * @return
     */
    public List<TopicManagerAbs> postSelectAllTopicManagerUser(Long userId, Map<String, Long> form) {
        List<TopicManagerAbs> topicManagerAbsList = new LinkedList<>();
        Long topicId = form.get("topicId");
        if (Objects.isNull(topicId)) return topicManagerAbsList;

        List<Long> userIds = topicServiceRpc.postSelectAllManagerUserIdsRpc(topicId);
        if (Objects.nonNull(userIds) && !userIds.isEmpty()) {
            List<UserModel> userModelList = userServiceRpc.postSelectMultiUserInfoRpc(userIds);
            if (Objects.nonNull(userModelList) && !userModelList.isEmpty()) {
                for (UserModel userModel:userModelList) {
                    TopicManagerAbs topicManagerAbs = new TopicManagerAbs();
                    topicManagerAbs.setUserAvatar(userModel.getAvatar());
                    topicManagerAbs.setUserDesc(userModel.getDesc());
                    topicManagerAbs.setUserId(userModel.getId());
                    topicManagerAbs.setUsername(userModel.getUsername());
                    topicManagerAbs.setUuid(userModel.getUuid());

                    topicManagerAbs.setIsAttention(userServiceRpc.postIsExistAttentionRpc(userId,userModel.getId()));

                    topicManagerAbsList.add(topicManagerAbs);
                }
            }
        }

        return topicManagerAbsList;
    }
}
