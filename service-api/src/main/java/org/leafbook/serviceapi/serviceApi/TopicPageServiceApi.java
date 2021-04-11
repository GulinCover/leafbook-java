package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.respAbs.hotPage.SearchTopicsResp;
import org.leafbook.api.respAbs.hotPage.TopicInfoAbs;
import org.leafbook.api.respAbs.topicPage.EntryAbs;
import org.leafbook.api.respAbs.topicSearch.SearchEntryAbs;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
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
public class TopicPageServiceApi {
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    @Autowired
    private CommonServiceRpc commonServiceRpc;
    @Autowired
    private RecordServiceRpc recordServiceRpc;
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;

    /**
     * 获取推荐词条信息,随机3条
     *
     * @return
     */
    public List<EntryAbs> getSelectRecommendedEntriesInfo() {

        return TestModel.createRecommendedEntryAbsList();
    }

    /**
     * 获取全部词条信息
     *
     * @param page
     * @return
     */
    public List<EntryAbs> getSelectAllEntriesInfo(Long userId, Long page) {
        List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectAllEntryInfoRpc(page);
        if (Objects.isNull(entryShowModelList)) return null;

        List<EntryAbs> entryAbsList = new LinkedList<>();

        for (EntryShowModel entryModel : entryShowModelList) {
            EntryAbs entryAbs = new EntryAbs();
            entryAbs.setEntryName(entryModel.getEntryName());
            entryAbs.setEntryDesc(entryModel.getEntryDesc());
            entryAbs.setEntryId(entryModel.getEntryId());
            entryAbs.setEntryAvatar(entryModel.getEntryAvatar());

            if (userId == 0) {
                entryAbs.setIsLiked(0);
            } else {
                entryAbs.setIsLiked(commonServiceRpc.postSelectTouchedStarRpc(userId, entryModel.getEntryId(), "entry"));
            }

            final Long starAmountRpc = entryServiceRpc.getSelectEntryInfoStarAmountRpc(entryModel.getEntryId());
            entryAbs.setLikeNumber(starAmountRpc);

            entryAbsList.add(entryAbs);
        }
        return entryAbsList;
    }

    /**
     * 搜索著述
     *
     * @param userId
     * @param form:content,entryId,page
     * @return
     */
    public SearchTopicsResp getSelectSearchMultiTopicInfo(Long userId, Map<String,String> form) {
        SearchTopicsResp resp = new SearchTopicsResp();
        String content = form.get("content");
        String pageNumber = form.get("page");
        String entryIdNumber = form.get("entryId");
        Long page = 1L;
        if (Objects.nonNull(pageNumber) && Covert2Tools.isDigital(pageNumber)) {
            page = Covert2Tools.covertToLong(pageNumber);
        }

        Long entryId = 0L;
        if (Objects.nonNull(entryIdNumber) && Covert2Tools.isDigital(entryIdNumber)) {
            entryId = Covert2Tools.covertToLong(pageNumber);
        }

        List<TopicInfoAbs> topicInfoAbsList = new LinkedList<>();
        List<SearchEntryAbs> searchEntryAbsList = new LinkedList<>();

        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return resp;

        recordServiceRpc.postInsertSingleSearchHistoryInfoRpc(userId, content);

        List<TopicModel> topicModelList;
        if (entryId != 0) {
            topicModelList = topicServiceRpc.getSelectSearchMultiTopicInfoByContentAndEntryIdRpc(0, entryId, content, page);
        } else {
            topicModelList = topicServiceRpc.getSelectSearchMultiTopicInfoByContentRpc(0,content, page);
        }

        Long maxPage = topicServiceRpc.getSelectSearchMultiTopicInfoByContentPageRpc(0,content);
        resp.setMaxAmount(maxPage);
        resp.setMaxPage((long)Math.ceil(maxPage / 20));

        List<Long> topicIds = new LinkedList<>();

        //装载topicInfo
        if (Objects.nonNull(topicModelList) && !topicModelList.isEmpty()) {
            for (TopicModel topicModel:topicModelList) {
                topicIds.add(topicModel.getTopicId());

                TopicInfoAbs topicInfoAbs = new TopicInfoAbs();
                topicInfoAbs.setTopicTitle(topicModel.getTopicTitle());
                topicInfoAbs.setTopicDesc(topicModel.getTopicDesc());
                topicInfoAbs.setPublicTime(topicModel.getUpdateTime());

                List<org.leafbook.api.respAbs.hotPage.EntryAbs> entryAbsList = new LinkedList<>();
                List<Long> entryIds = topicServiceRpc.getSelectSingleTopicInfoForEntryIdsRpc(topicModel.getTopicId());

                List<EntryShowModel> entryInfos = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIds);
                if (Objects.nonNull(entryInfos) && !entryInfos.isEmpty()) {
                    for (EntryShowModel entryShowModel:entryInfos) {
                        org.leafbook.api.respAbs.hotPage.EntryAbs entryAbs = new org.leafbook.api.respAbs.hotPage.EntryAbs();
                        entryAbs.setEntryId(entryShowModel.getEntryId());
                        entryAbs.setEntryName(entryShowModel.getEntryName());
                        entryAbsList.add(entryAbs);
                    }
                }
                topicInfoAbs.setEntryAbsList(entryAbsList);

                topicInfoAbsList.add(topicInfoAbs);
            }
        }

        //装载entryInfo
        List<Long> entryIds = topicServiceRpc.getSelectEntryIdsByTopicIdsRpc(topicIds);
        Map<Long,Long> entryInfoAmount = topicServiceRpc.getSelectEntryAmountByEntryIdsRpc(entryIds);

        for (Map.Entry<Long,Long> map:entryInfoAmount.entrySet()) {
            SearchEntryAbs searchEntryAbs = new SearchEntryAbs();
            searchEntryAbs.setEntryId(map.getKey());
            EntryShowModel entryInfo = entryServiceRpc.getSelectSingleEntryInfoRpc(map.getKey());
            searchEntryAbs.setEntryName(entryInfo.getEntryName());
            searchEntryAbs.setTopicAmount(map.getValue());

            searchEntryAbsList.add(searchEntryAbs);
        }

        resp.setSearchEntryAbsList(searchEntryAbsList);
        resp.setTopicInfoAbsList(topicInfoAbsList);

        return resp;
    }
}
