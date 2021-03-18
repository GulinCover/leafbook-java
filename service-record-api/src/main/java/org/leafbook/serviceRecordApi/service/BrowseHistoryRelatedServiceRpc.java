package org.leafbook.serviceRecordApi.service;

import org.leafbook.api.modelApi.recordInfo.BrowseHistoryModel;
import org.leafbook.serviceRecordApi.dao.BrowseHistoryModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrowseHistoryRelatedServiceRpc {
    @Autowired
    private BrowseHistoryModelMapper browseHistoryModelMapper;

    /**
     * 查询浏览历史
     * @param userId
     * @param page
     * @return
     */
    public List<BrowseHistoryModel> postSelectBrowseHistoryInfos(Long userId,Integer page) {
        return browseHistoryModelMapper.selectMultiBrowseHistoryByUserIdAndPage(userId,page);
    }

    /**
     * 删除单一浏览历史
     * @param userId
     * @param browseHistoryId
     * @return code
     */
    public int postDeleteSingleBrowseHistoryInfo(Long userId,Long browseHistoryId) {
        return browseHistoryModelMapper.deleteSingleBrowseHistoryByBrowseHistoryId(userId,browseHistoryId);
    }

    /**
     * 增加浏览历史
     * @param userId
     * @param topicId
     * @return code
     */
    public int postInsertSingleBrowseHistoryInfo(Long userId,Long topicId) {
        BrowseHistoryModel browseHistoryModel = new BrowseHistoryModel();
        browseHistoryModel.setUserId(userId);
        browseHistoryModel.setTopicId(topicId);
        return browseHistoryModelMapper.insert(browseHistoryModel);
    }

    /**
     * 组删浏览记录
     * @param userId
     * @param browseHistoryIds
     * @return code
     */
    public int postDeleteMultiBrowseHistoryInfo(Long userId,List<Long> browseHistoryIds) {
        return browseHistoryModelMapper.deleteMultiBrowseHistoryByBrowseHistoryIds(userId,browseHistoryIds);
    }

}
