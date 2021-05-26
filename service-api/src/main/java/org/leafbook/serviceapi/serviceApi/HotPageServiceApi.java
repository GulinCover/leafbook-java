package org.leafbook.serviceapi.serviceApi;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.Data;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.hotPage.AllTopicInfosResp;
import org.leafbook.api.respAbs.hotPage.EntryAbs;
import org.leafbook.api.respAbs.hotPage.SearchTopicsResp;
import org.leafbook.api.respAbs.hotPage.TopicInfoAbs;
import org.leafbook.api.testModel.hotPage.HotTextModel;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@GlobalTransactional
@Service
public class HotPageServiceApi {
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    @Autowired
    private UserServiceRpc userServiceRpc;

    /**
     * 获取点赞排行著述id,每页20条
     *
     * @param userId
     * @param page
     * @return
     */
    public AllTopicInfosResp getSelectAllRankTopicInfos(Long userId, Long page) {
        if (page <= 0) page = 1L;

        AllTopicInfosResp resp = new AllTopicInfosResp();
        List<Long> topicIdList = topicServiceRpc.getSelectMultiTopicIdByStarRankRpc(page);
        List<TopicInfoAbs> topicInfoAbsList = new LinkedList<>();
        if (Objects.nonNull(topicIdList) && !topicIdList.isEmpty()) {
            List<TopicModel> topicInfoList = topicServiceRpc.getSelectMultiTopicInfoRpc(topicIdList);

            if (Objects.nonNull(topicInfoList) && !topicInfoList.isEmpty()) {
                for (TopicModel topicModel : topicInfoList) {
                    TopicInfoAbs topicInfoAbs = new TopicInfoAbs();
                    topicInfoAbs.setTopicId(topicModel.getTopicId());
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

//                    //获取最新一篇文章
//                    ArticleModel articleInfo = topicServiceRpc.getSelectLastTimeArticleInfoByTopicIdRpc(topicModel.getTopicId());
//                    if (Objects.nonNull(articleInfo)) {
//                        topicInfoAbs.setPublicTime(articleInfo.getCreateTime());
//                    }
                    topicInfoAbs.setPublicTime(topicModel.getCreateTime());

                    List<Long> entryIdList = topicServiceRpc.getSelectSingleTopicInfoForEntryIdsRpc(topicModel.getTopicId());
                    if (Objects.nonNull(entryIdList) && !entryIdList.isEmpty()) {

                        List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIdList);
                        if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
                            List<EntryAbs> entryAbsList = new LinkedList<>();
                            for (EntryShowModel entryShowModel : entryInfoList) {
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

        resp.setPage(topicServiceRpc.getSelectMultiTopicIdByStarRankPageAmountRpc());
        resp.setTopicInfoAbsList(topicInfoAbsList);
        return resp;
    }
    /**
     * 搜索
     * @param userId
     * @param page
     * @param blurry
     * @param entry
     * @param time
     * @return
     */
    public SearchTopicsResp getSelectSearchHotEntryTopics(
            Long userId,Long page,String blurry,Long entry,String time) {
        SearchTopicsResp resp = new SearchTopicsResp();
        TopicInfoListAbs topicInfoListAbs = new TopicInfoListAbs();

        if (Objects.isNull(time)) return resp;
        topicInfoListAbs = _searchHotEntryTopics(page, blurry, entry, time);

        resp.setPage(topicInfoListAbs.getPage());
        resp.setTopicInfoAbsList(topicInfoListAbs.getTopicInfoAbsList());
        return resp;
    }

    @Data
    class TopicInfoListAbs {
        private List<TopicInfoAbs> topicInfoAbsList;
        private Long page;
    }
    private TopicInfoListAbs _searchHotEntryTopics(Long page,String blurry,Long entry,String time) {
        if (page <= 0) page = 1L;

        TopicInfoListAbs topicInfoListAbs = new TopicInfoListAbs();
        List<TopicInfoAbs> topicInfoAbsList = new LinkedList<>();
        long startTime = 0L;
        long endTime = 0L;

        if ("today".equals(time)) {
            endTime = new Date().getTime();
            startTime = endTime - 86400 * 1000L;
        } else if ("week".equals(time)) {
            endTime = new Date().getTime();
            startTime = endTime - 7 * 86400 * 1000L;
        } else if ("mouth".equals(time)) {
            endTime = new Date().getTime();
            startTime = endTime - 30 * 86400 * 1000L;
        } else {
            endTime = new Date().getTime();
        }

        List<TopicModel> topicModelList = topicServiceRpc.getSelectMultiBlurrySearchRpc(page, blurry,entry, startTime, endTime);
        if (Objects.nonNull(topicModelList) && !topicModelList.isEmpty()) {
            for (TopicModel topicModel:topicModelList) {
                TopicInfoAbs topicInfoAbs = new TopicInfoAbs();
                topicInfoAbs.setTopicId(topicModel.getTopicId());
                topicInfoAbs.setPublicTime(topicModel.getCreateTime());
                topicInfoAbs.setTopicDesc(topicModel.getTopicDesc());
                topicInfoAbs.setTopicTitle(topicModel.getTopicTitle());

                topicInfoAbs.setUserId(topicModel.getUserId());
                UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(topicModel.getUserId());
                if (Objects.nonNull(userModel)) {
                    topicInfoAbs.setUserName(userModel.getUsername());
                }

                topicInfoAbs.setContributorNumber(topicServiceRpc.getSelectContributorAmountByTopicIdRpc(topicModel.getTopicId()));
                topicInfoAbs.setLikeNumber(topicServiceRpc.getSelectTopicStarAmountRpc(topicModel.getTopicId()));

                List<Long> entryIdList = topicServiceRpc.getSelectSingleTopicInfoForEntryIdsRpc(topicModel.getTopicId());
                if (Objects.nonNull(entryIdList) && !entryIdList.isEmpty()) {
                    List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIdList);
                    if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
                        List<EntryAbs> entryAbsList = new LinkedList<>();
                        for (EntryShowModel entryShowModel : entryInfoList) {
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


        topicInfoListAbs.setPage(topicServiceRpc.getSelectMultiBlurrySearchPageAmountRpc(page, blurry,entry, startTime, endTime));
        topicInfoListAbs.setTopicInfoAbsList(topicInfoAbsList);
        return topicInfoListAbs;
    }

    /**
     * 获取所有标签
     *
     * @return
     */
    public List<EntryAbs> getSelectAllEntryListApi(Long userId) {
        List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectAllEntryInfoRpc();
        List<EntryAbs> entryAbsList = new LinkedList<>();
        if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
            for (EntryShowModel entryShowModel : entryInfoList) {
                EntryAbs entryAbs = new EntryAbs();
                entryAbs.setEntryId(entryShowModel.getEntryId());
                entryAbs.setEntryName(entryShowModel.getEntryName());

                entryAbsList.add(entryAbs);
            }
        }

        return entryAbsList;
    }
}
