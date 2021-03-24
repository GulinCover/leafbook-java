package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.modelApi.entryInfo.EntryModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.hotPage.EntryAbs;
import org.leafbook.api.respAbs.hotPage.TopicInfoAbs;
import org.leafbook.api.testModel.hotPage.HotTextModel;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.leafbook.utils.tools.Covert2Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class HotPageServiceApi {
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    @Autowired
    private UserServiceRpc userServiceRpc;
    /**
     * 获取点赞排行著述id,每页15条
     * @param page
     * @return
     */
    public List<TopicInfoAbs> getSelectAllRankTopicInfos(Long page) {
        List<Long> topicIdList = topicServiceRpc.getSelectMultiTopicIdByStarRankRpc(page);
        List<TopicInfoAbs> topicInfoAbsList = new LinkedList<>();
        if (Objects.nonNull(topicIdList) && !topicIdList.isEmpty()) {
            List<TopicModel> topicInfoList = topicServiceRpc.getSelectMultiTopicInfoRpc(topicIdList);

            if (Objects.nonNull(topicInfoList) && !topicInfoList.isEmpty()) {
                for (TopicModel topicModel:topicInfoList) {
                    TopicInfoAbs topicInfoAbs = new TopicInfoAbs();
                    topicInfoAbs.setUserId(topicModel.getUserId());
                    topicInfoAbs.setTopicTitle(topicModel.getTopicTitle());
                    topicInfoAbs.setTopicDesc(topicModel.getTopicDesc());

                    //获取用户信息
                    UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(topicModel.getUserId());
                    if (Objects.nonNull(userModel)) {
                        topicInfoAbs.setUserName(userModel.getUsername());
                    }

                    Long starAmount = topicServiceRpc.getSelectTopicStarAmountRpc(topicModel.getTopicId());
                    topicInfoAbs.setLikeNumber(starAmount);

                    Long contributorAmount = topicServiceRpc.getSelectContributorAmountByTopicIdRpc(topicModel.getTopicId());
                    topicInfoAbs.setContributorNumber(contributorAmount);

                    //获取最新一篇文章
                    ArticleModel articleInfo = topicServiceRpc.getSelectLastTimeArticleInfoByTopicIdRpc(topicModel.getTopicId());
                    if (Objects.nonNull(articleInfo)) {
                        topicInfoAbs.setPublicTime(articleInfo.getPublicTime());
                    }

                    List<Long> entryIdList = topicServiceRpc.getSelectSingleTopicInfoForEntryIdsRpc(topicModel.getTopicId());
                    if (Objects.nonNull(entryIdList) && !entryIdList.isEmpty()) {

                        List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIdList);
                        if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
                            List<EntryAbs> entryAbsList = new LinkedList<>();
                            for (EntryShowModel entryShowModel:entryInfoList) {
                                EntryAbs entryAbs = new EntryAbs();
                                entryAbs.setEntryId(entryShowModel.getEntryId());
                                entryAbs.setEntryName(entryShowModel.getEntryName());

                                entryAbsList.add(entryAbs);
                            }

                            topicInfoAbs.setEntryAbsList(entryAbsList);
                        }
                    }

                    topicInfoAbsList.add(topicInfoAbs);
                }
            }
        }
        return topicInfoAbsList;
    }

    public List<EntryAbs> getSelectEntryInfoByTopicId(Long topicId) {
        return HotTextModel.createEntryAbsByTopicId();
    }

    /**
     * 获取所有标签
     * @return
     */
    public List<EntryAbs> getSelectAllEntryListApi() {
        List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectAllEntryInfoRpc();
        List<EntryAbs> entryAbsList = new LinkedList<>();
        if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
            for (EntryShowModel entryShowModel:entryInfoList) {
                EntryAbs entryAbs = new EntryAbs();
                entryAbs.setEntryId(entryShowModel.getEntryId());
                entryAbs.setEntryName(entryShowModel.getEntryName());

                entryAbsList.add(entryAbs);
            }
        }

        return entryAbsList;
    }
}
