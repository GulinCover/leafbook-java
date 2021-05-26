package org.leafbook.serviceapi.serviceApi.create;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.respAbs.createTopicPage.EntryAbs;
import org.leafbook.api.testModel.createTopicPage.CreateTopicTestModel;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@GlobalTransactional
@Service
public class CreateTopicPageServiceApi {
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private UserServiceRpc userServiceRpc;
    /**
     *
     * @param userId
     * @param form:topicTitle?String,
     *            topicDesc?String,
     *            topicEntryList?List<entryId?Long>
     * @return 返回创建的topicId
     */
    public Long postCreateTopicInfo(Long userId, Map<String, Object> form) {
        String topicTitle = (String) form.get("topicTitle");
        String topicDesc = (String) form.get("topicDesc");

        if (Objects.isNull(topicTitle) || Objects.isNull(topicDesc)) return 0L;

        //检测用户合法性
        if (userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId) != 1) {
            return 0L;
        }

        //检测词条合法性
        List<Long> topicEntryList = new LinkedList<>();
        if (form.get("topicEntryList") instanceof List<?>) {
            for (Object o:(List<?>) form.get("topicEntryList")) {
                topicEntryList.add(Long.parseLong(o.toString()));
            }
        }

        if (entryServiceRpc.postSelectDetectLegalityWithEntryIdsRpc(topicEntryList) != 1) {
            return 0L;
        }

        Long topicId = topicServiceRpc.postCreateTopicInfoRpc(userId, topicTitle,topicDesc,topicEntryList);
        if (topicId == 0) return 0L;

        return topicServiceRpc.postCreateTopicLikedAndTreadRpc(topicId) == 1 ? topicId : 0L;
    }
    /**
     * 获取官方词条
     * @return
     */
    public List<EntryAbs> getSelectOfficialEntryInfos() {
        List<EntryAbs> entryAbsList = new LinkedList<>();
        List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectAllEntryInfoWithTypeRpc("official");
        if (Objects.nonNull(entryShowModelList) && !entryShowModelList.isEmpty()) {
            for (EntryShowModel entryShowModel:entryShowModelList) {
                EntryAbs entryAbs = new EntryAbs();
                entryAbs.setEntryId(entryShowModel.getEntryId());
                entryAbs.setEntryDesc(entryShowModel.getEntryDesc());
                entryAbs.setEntryName(entryShowModel.getEntryName());

                entryAbsList.add(entryAbs);
            }
        }

        return entryAbsList;
    }
    /**
     * 获取非官方词条
     * @return
     */
    public List<EntryAbs> getSelectNonofficialEntryInfos() {
        List<EntryAbs> entryAbsList = new LinkedList<>();
        List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectAllEntryInfoWithTypeRpc("nonofficial");
        if (Objects.nonNull(entryShowModelList) && !entryShowModelList.isEmpty()) {
            for (EntryShowModel entryShowModel:entryShowModelList) {
                EntryAbs entryAbs = new EntryAbs();
                entryAbs.setEntryId(entryShowModel.getEntryId());
                entryAbs.setEntryDesc(entryShowModel.getEntryDesc());
                entryAbs.setEntryName(entryShowModel.getEntryName());

                entryAbsList.add(entryAbs);
            }
        }

        return entryAbsList;
    }

    /**
     * 获取hot词条
     * @return
     */
    public List<EntryAbs> getSelectHotEntryInfos() {
        List<EntryAbs> entryAbsList = new LinkedList<>();
        List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectAllEntryInfoWithTypeRpc("hot");
        if (Objects.nonNull(entryShowModelList) && !entryShowModelList.isEmpty()) {
            for (EntryShowModel entryShowModel:entryShowModelList) {
                EntryAbs entryAbs = new EntryAbs();
                entryAbs.setEntryId(entryShowModel.getEntryId());
                entryAbs.setEntryDesc(entryShowModel.getEntryDesc());
                entryAbs.setEntryName(entryShowModel.getEntryName());

                entryAbsList.add(entryAbs);
            }
        }

        return entryAbsList;
    }
}
