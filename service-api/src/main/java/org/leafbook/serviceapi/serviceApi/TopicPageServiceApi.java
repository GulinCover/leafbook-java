package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.modelApi.entryInfo.EntryModel;
import org.leafbook.api.respAbs.topicPage.EntryAbs;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class TopicPageServiceApi {
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    @Autowired
    private CommonServiceRpc commonServiceRpc;
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
    public List<EntryAbs> getSelectAllEntriesInfo(Long userId,Long page) {
        List<EntryModel> entryModelList = entryServiceRpc.getSelectAllEntryInfoRpc(page);
        if (Objects.isNull(entryModelList)) return null;

        List<EntryAbs> entryAbsList = new LinkedList<>();

        for (EntryModel entryModel:entryModelList) {
            EntryAbs entryAbs = new EntryAbs();
            entryAbs.setEntryName(entryModel.getEntryName());
            entryAbs.setEntryDesc(entryModel.getEntryDesc());
            entryAbs.setEntryId(entryModel.getEntryId());
            entryAbs.setEntryAvatar(entryModel.getEntryAvatar());

            if (userId == 0) {
                entryAbs.setIsLiked(0);
            } else {
                entryAbs.setIsLiked(commonServiceRpc.postSelectTouchedStarRpc(userId,entryModel.getEntryId(),"entry"));
            }

            final Long starAmountRpc = entryServiceRpc.getSelectEntryInfoStarAmountRpc(entryModel.getEntryId());
            entryAbs.setLikeNumber(starAmountRpc);

            entryAbsList.add(entryAbs);
        }
        return entryAbsList;
    }
}
