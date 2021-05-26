package org.leafbook.serviceapi.serviceApi;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.explorePage.EntryAbs;
import org.leafbook.api.respAbs.explorePage.EntryInfoResp;
import org.leafbook.api.respAbs.explorePage.TopicAbs;
import org.leafbook.api.respAbs.explorePage.UserReplyDataResp;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.leafbook.utils.tools.Covert2Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@GlobalTransactional
@Service
public class ExplorePageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    @Autowired
    private CommonServiceRpc commonServiceRpc;
    /**
     * 获取用户信息，著述回复的数量，评论回复的数量，议论回复的数量
     * @param userId
     * @return
     */
    public UserReplyDataResp postSelectUserReplyNumber(Long userId) {
        UserReplyDataResp userReplyDataResp = new UserReplyDataResp();
        UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
        if (Objects.nonNull(userModel)) {
            userReplyDataResp.setUserAvatar(userModel.getAvatar());
            userReplyDataResp.setUUID(userModel.getUuid());
            userReplyDataResp.setUsername(userModel.getUsername());
        }


        userReplyDataResp.setCommentReplyNumber(new Random().nextInt(100));
        userReplyDataResp.setTopicReplyNumber(new Random().nextInt(100));
        userReplyDataResp.setTalkReplyNumber(new Random().nextInt(100));
        return userReplyDataResp;
    }

    /**
     * 随机获取3~8条词条信息
     * @param userId:
     * @return
     */
    public List<EntryAbs> postSelectEntryInfos(Long userId) {
        List<EntryAbs> entryAbsList = new LinkedList<>();
        List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectRandomMultiEntryInfoRpc();
        if (Objects.nonNull(entryShowModelList) && !entryShowModelList.isEmpty()) {
            for (EntryShowModel entryModel:entryShowModelList) {
                EntryAbs entryAbs = new EntryAbs();
                entryAbs.setEntryAvatar(entryModel.getEntryAvatar());
                entryAbs.setEntryDesc(entryModel.getEntryDesc());
                entryAbs.setEntryId(entryModel.getEntryId());
                entryAbs.setEntryName(entryModel.getEntryName());

                Long starAmount = entryServiceRpc.getSelectEntryInfoStarAmountRpc(entryModel.getEntryId());
                entryAbs.setLikeNumber(starAmount);

                if (userId != 0) {
                    entryAbs.setIsLiked(commonServiceRpc.postSelectTouchedStarRpc(userId,entryModel.getEntryId(), "entry"));
                }

                entryAbsList.add(entryAbs);
            }
        }

        return entryAbsList;
    }
    /**
     * 根据词条随机查询5~8条著述
     * @param form: entryIds
     * @return
     */
    public List<TopicAbs> postSelectTopicInfosByEntryIds(Map<String,List<Long>> form) {
        List<Long> entryIds = form.get("entryIds");
        if (Objects.isNull(entryIds) || entryIds.isEmpty()) return null;

        List<TopicAbs> topicAbsList = new LinkedList<>();
        for (Long entryId:entryIds) {
            List<TopicModel> topicModelList = topicServiceRpc.getSelectRandomMultiTopicInfoByEntryIdRpc(entryId);
            if (Objects.nonNull(topicModelList) && !topicModelList.isEmpty()) {
                for (TopicModel topicModel:topicModelList) {
                    TopicAbs topicAbs = new TopicAbs();
                    topicAbs.setEntryId(entryId);
                    topicAbs.setTopicDesc(topicModel.getTopicDesc());
                    topicAbs.setTopicId(topicModel.getTopicId());
                    topicAbs.setTopicTitle(topicModel.getTopicTitle());

                    UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(topicModel.getUserId());
                    if (Objects.nonNull(userModel)) {
                        topicAbs.setTopicUsername(userModel.getUsername());
                        topicAbs.setUserId(userModel.getId());
                    }

                    topicAbs.setLikeNumber(topicServiceRpc.getSelectTopicStarAmountRpc(topicModel.getTopicId()));

                    topicAbsList.add(topicAbs);
                }
            }
        }

        return topicAbsList;
    }
    /**
     * 随机推荐一条词条
     *
     * @return isLeft为0，单条数据；isLeft为1，及其相关数据；
     */
    public EntryInfoResp getSelectSingleEntryInfoRecommended() {
        EntryInfoResp entryInfoResp = new EntryInfoResp();
        List<EntryShowModel> entryShowInfoList = entryServiceRpc.getSelectRandomHotMultiEntryInfoRpc();
        if (Objects.nonNull(entryShowInfoList) && !entryShowInfoList.isEmpty()) {
            EntryShowModel entryShowModel = entryShowInfoList.get(0);
            entryInfoResp.setEntryAvatar(entryShowModel.getEntryAvatar());
            entryInfoResp.setEntryDesc(entryShowModel.getEntryDesc());
            entryInfoResp.setEntryId(entryShowModel.getEntryId());
            entryInfoResp.setEntryName(entryShowModel.getEntryName());
            entryInfoResp.setIsLeft("0");
        }

        return entryInfoResp;
    }
    /**
     * 随机推荐一条词条,及相关词条
     * @return isLeft为0，单条数据；isLeft为1，及其相关数据；
     */
    public EntryInfoResp getSelectEntryInfoRecommendedAndRelated() {
        EntryInfoResp entryInfoResp = new EntryInfoResp();
        List<EntryShowModel> entryShowInfoList = entryServiceRpc.getSelectRandomHotMultiEntryInfoRpc();
        if (Objects.nonNull(entryShowInfoList) && !entryShowInfoList.isEmpty()) {
            EntryShowModel entryShowModel = entryShowInfoList.get(0);
            entryInfoResp.setEntryAvatar(entryShowModel.getEntryAvatar());
            entryInfoResp.setEntryDesc(entryShowModel.getEntryDesc());
            entryInfoResp.setEntryId(entryShowModel.getEntryId());
            entryInfoResp.setEntryName(entryShowModel.getEntryName());
            entryInfoResp.setIsLeft("1");

            List<EntryAbs> entryAbsList = new LinkedList<>();
            for (EntryShowModel entryShow:entryShowInfoList) {
                EntryAbs entryAbs = new EntryAbs();
                entryAbs.setEntryId(entryShow.getEntryId());
                entryAbs.setEntryAvatar(entryShow.getEntryAvatar());
                entryAbs.setEntryDesc(entryShow.getEntryDesc());
                entryAbs.setEntryName(entryShow.getEntryName());

                entryAbs.setLikeNumber(entryServiceRpc.getSelectEntryInfoStarAmountRpc(entryShow.getEntryId()));

                entryAbsList.add(entryAbs);
            }
            entryInfoResp.setEntryAbsList(entryAbsList);
        }

        return entryInfoResp;
    }
    /**
     * 随机获取热门2~4条词条
     * @param form:userId?可传可不传
     * @return
     */
    public List<EntryAbs> postSelectHotEntryInfos(Map<String,Long> form) {
        List<EntryAbs> entryAbsList = new LinkedList<>();

        List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectRandomHotMultiEntryInfoRpc();
        if (Objects.nonNull(entryShowModelList) && !entryShowModelList.isEmpty()) {
            for (EntryShowModel entryShowModel: entryShowModelList) {
                EntryAbs entryAbs = new EntryAbs();
                entryAbs.setEntryName(entryShowModel.getEntryName());
                entryAbs.setEntryId(entryShowModel.getEntryId());
                entryAbs.setEntryDesc(entryShowModel.getEntryDesc());
                entryAbs.setEntryAvatar(entryShowModel.getEntryAvatar());
                entryAbs.setLikeNumber(entryServiceRpc.getSelectEntryInfoStarAmountRpc(entryShowModel.getEntryId()));

                Long userId = form.get("userId");
                if (Objects.nonNull(userId)) {
                    entryAbs.setIsLiked(commonServiceRpc.postSelectTouchedStarRpc(userId, entryShowModel.getEntryId(),"entry"));
                }

                entryAbsList.add(entryAbs);
            }
        }
        return entryAbsList;
    }

}
