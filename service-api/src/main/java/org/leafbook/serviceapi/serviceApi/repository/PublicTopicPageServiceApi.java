package org.leafbook.serviceapi.serviceApi.repository;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.respAbs.repository.publicTopicPage.EntryAbs;
import org.leafbook.api.respAbs.repository.publicTopicPage.PublicTopicAbs;
import org.leafbook.api.respAbs.repository.publicTopicPage.PublicTopicInfosResp;
import org.leafbook.api.testModel.repositoryPage.RepositoryTestModel;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@GlobalTransactional
@Service
public class PublicTopicPageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private EntryServiceRpc entryServiceRpc;

    /**
     * 获取用户发布的著述
     * @param userId
     * @return
     */
    public PublicTopicInfosResp postSelectPublicTopicInfo(Long userId, Map<String, Long> form) {
        PublicTopicInfosResp resp = new PublicTopicInfosResp();
        List<PublicTopicAbs> publicTopicAbsList = new LinkedList<>();
        Long page = form.get("page");
        if (Objects.isNull(page) || page <= 0) {
            page = 1L;
        }
        //检测用户合法性
        final int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return resp;

        //查询用户发布的topic
        List<TopicModel> topicModelList = topicServiceRpc.postSelectMeTopicInfoRpc(userId, page);
        if (Objects.nonNull(topicModelList) && !topicModelList.isEmpty()) {
            for (TopicModel topicModel:topicModelList) {
                PublicTopicAbs publicTopicAbs = new PublicTopicAbs();
                publicTopicAbs.setTopicId(topicModel.getTopicId());
                publicTopicAbs.setTopicTitle(topicModel.getTopicTitle());
                publicTopicAbs.setTopicDesc(topicModel.getTopicDesc());
                publicTopicAbs.setUpdateTime(topicModel.getUpdateTime());

                publicTopicAbs.setLikeNumber(topicServiceRpc.getSelectTopicStarAmountRpc(topicModel.getTopicId()));

                List<EntryAbs> entryAbsList = new LinkedList<>();
                List<Long> entryIdList = topicServiceRpc.getSelectSingleTopicInfoForEntryIdsRpc(topicModel.getTopicId());
                if (Objects.nonNull(entryIdList) && !entryIdList.isEmpty()) {

                    List<EntryShowModel> selectMultiEntryInfoRpc = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIdList);
                    if (Objects.nonNull(selectMultiEntryInfoRpc) && !selectMultiEntryInfoRpc.isEmpty()) {
                        for (EntryShowModel entryShowModel:selectMultiEntryInfoRpc) {
                            EntryAbs entryAbs = new EntryAbs();
                            entryAbs.setEntryId(entryShowModel.getEntryId());
                            entryAbs.setEntryName(entryShowModel.getEntryName());

                            entryAbsList.add(entryAbs);
                        }
                    }
                }

                publicTopicAbs.setEntryAbsList(entryAbsList);
                publicTopicAbsList.add(publicTopicAbs);
            }

            resp.setPublicTopicAbsList(publicTopicAbsList);
        }

        //获取最大页数

        Long maxPage = topicServiceRpc.postSelectMeTopicInfoPageRpc(userId);
        resp.setPage(maxPage);

        return resp;
    }

    public List<EntryAbs> postSelectPublicTopicInfoAllEntryList() {
        return RepositoryTestModel.createPublicTopicAllEntryList();
    }
}
