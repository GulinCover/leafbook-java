package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.recordInfo.BrowseHistoryModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.respAbs.common.SearchHistoryAbs;
import org.leafbook.api.respAbs.indexPage.BrowseHistoryAbs;
import org.leafbook.api.respAbs.indexPage.EntryData;
import org.leafbook.api.respAbs.indexPage.RightBrowseHistoryResp;
import org.leafbook.api.respAbs.indexPage.TopicAbs;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.recordService.RecordServiceRpc;
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
public class IndexPageServiceApi {
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private RecordServiceRpc recordServiceRpc;
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    /**
     * 仓库展示
     * @param userId
     * @return
     */
    public List<TopicAbs> postSelectUserTopics(Long userId) {
        List<TopicModel> topicModelList = topicServiceRpc.postSelectMeTopicInfoRpc(userId);
        List<TopicAbs> topicAbsList = new LinkedList<>();

        if (Objects.nonNull(topicModelList) && !topicModelList.isEmpty()) {
            for (TopicModel topicModel:topicModelList) {
                TopicAbs topicAbs = new TopicAbs();
                topicAbs.setTopicId(topicModel.getTopicId());
                topicAbs.setTopicTitle(topicModel.getTopicTitle());

                topicAbsList.add(topicAbs);
            }
        }
        return topicAbsList;
    }

    public List<SearchHistoryAbs> postSelectMeSearchHistoryTopics(Long userId) {

        return TestModel.createSearchHistoryAbsList();
    }
    /**
     * 浏览历史
     * @param userId
     * @param form: page:一页8条
     * @return
     */
    public List<BrowseHistoryAbs> postSelectMeBrowseHistoryTopics(Long userId,Map<String,String> form) {
        List<BrowseHistoryAbs> browseHistoryAbsList = new LinkedList<>();

        String page = form.get("page");
        if (Objects.isNull(page)) return browseHistoryAbsList;
        if (!Covert2Tools.isDigital(page)) return browseHistoryAbsList;

        List<BrowseHistoryModel> browseHistoryModelList = recordServiceRpc.postSelectBrowseHistoryInfosRpc(userId, Covert2Tools.covertToInteger(page));

        if (Objects.nonNull(browseHistoryModelList) && !browseHistoryModelList.isEmpty()) {
            for (BrowseHistoryModel browseHistoryModel:browseHistoryModelList) {
                BrowseHistoryAbs browseHistoryAbs = new BrowseHistoryAbs();
                browseHistoryAbs.setTopicId(browseHistoryModel.getTopicId());

                TopicModel topicModel = topicServiceRpc.getSelectSingleTopicInfoRpc(browseHistoryModel.getTopicId());

                if (Objects.nonNull(topicModel)) {
                    browseHistoryAbs.setTopicDesc(topicModel.getTopicDesc());
                    browseHistoryAbs.setTopicTitle(topicModel.getTopicTitle());

                    final List<Long> entryIdList = topicServiceRpc.getSelectSingleTopicInfoForEntryIdsRpc(browseHistoryModel.getTopicId());

                    if (Objects.nonNull(entryIdList) && !entryIdList.isEmpty()) {
                        List<EntryShowModel> entryShowInfoList = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIdList);

                        if (Objects.nonNull(entryShowInfoList) && !entryShowInfoList.isEmpty()) {

                            List<EntryData> entryDataList = new LinkedList<>();
                            for (EntryShowModel entryShowModel:entryShowInfoList) {
                                EntryData entryData = new EntryData();
                                entryData.setEntryId(entryShowModel.getEntryId());
                                entryData.setName(entryShowModel.getEntryName());

                                entryDataList.add(entryData);
                            }

                            browseHistoryAbs.setEntryDataList(entryDataList);
                        }
                    }
                }

                Long starAmount = topicServiceRpc.getSelectTopicStarAmountRpc(browseHistoryModel.getTopicId());
                browseHistoryAbs.setLikeNumber(starAmount);

                browseHistoryAbsList.add(browseHistoryAbs);
            }
        }

        return browseHistoryAbsList;
    }

    public List<TopicAbs> postSelectMeTopics(Map<String, String> form) {
        return TestModel.createTopicAbsList();
    }

    public List<TopicAbs> getSelectTopic(String name) {
        return TestModel.createTopicAbsList();
    }
}
