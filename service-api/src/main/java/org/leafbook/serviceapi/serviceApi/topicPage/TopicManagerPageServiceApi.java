package org.leafbook.serviceapi.serviceApi.topicPage;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.topicPublicPage.manager.TopicManagerAbs;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.leafbook.utils.tools.Covert2Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@GlobalTransactional
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
                    topicManagerAbs.setUserDesc(userModel.getUserDesc());
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
    /**
     * 添加管理员
     * @param userId
     * @param form:userId,uuid,topicId
     * @return
     */
    public int postAddManagerUser(Long userId,Map<String,String> form) {
        String managerUserId = form.get("userId");
        String uuid = form.get("uuid");
        String topicId = form.get("topicId");

        if (Objects.isNull(topicId) || !Covert2Tools.isDigital(topicId)) {
            return 4000;
        }

        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret != 1) return ret;

        if (Objects.nonNull(managerUserId)) {

            ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(Covert2Tools.covertToLong(managerUserId));
            if (ret != 1) return ret;

            return topicServiceRpc.postAddTopicManagerRpc(userId,Covert2Tools.covertToLong(topicId), Covert2Tools.covertToLong(managerUserId));

        } else if (Objects.nonNull(uuid)) {

            UserModel userModel = userServiceRpc.postSelectSingleUserInfoByUuidRpc(uuid);
            if (Objects.isNull(userModel)) return 403;

            return topicServiceRpc.postAddTopicManagerRpc(userId,Covert2Tools.covertToLong(topicId), userModel.getId());

        } else {
            return 403;
        }
    }
    

    /**
     * 删除管理员
     * @param userId
     * @param form:userId,uuid,topicId
     * @return
     */
    public int postDelManagerUser(Long userId,Map<String,Long> form) {
        Long managerUserId = form.get("userId");
        Long topicId = form.get("topicId");
        if (Objects.isNull(managerUserId)) {
            return 4000;
        }
        if (Objects.isNull(topicId)) {
            return 4000;
        }

        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(managerUserId);
        if (ret != 1) return ret;

        ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret != 1) return ret;

        return topicServiceRpc.postAddTopicManagerRpc(userId, topicId, managerUserId);
    }
}
