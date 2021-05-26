package org.leafbook.serviceRecordApi.service;

import org.leafbook.api.modelApi.recordInfo.BrowseHistoryModel;
import org.leafbook.serviceRecordApi.daoImpl.BrowseHistoryModelMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BrowseHistoryRelatedServiceRpc {
    @Autowired
    private BrowseHistoryModelMapperImpl browseHistoryModelMapperImpl;

    /**
     * 查询浏览历史
     * @param userId
     * @param page
     * @return
     */
    public List<BrowseHistoryModel> postSelectBrowseHistoryInfos(Long userId,Long page) {
        return browseHistoryModelMapperImpl.selectMultiBrowseHistoryByUserIdAndPage(userId,page);
    }

    /**
     * 删除单一浏览历史
     * @param userId
     * @param browseHistoryId
     * @return code
     */
    public int postDeleteSingleBrowseHistoryInfo(Long userId,Long browseHistoryId) {
        return browseHistoryModelMapperImpl.deleteSingleBrowseHistoryByBrowseHistoryId(userId,browseHistoryId);
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
        return browseHistoryModelMapperImpl.insert(browseHistoryModel);
    }

    /**
     * 组删浏览记录
     * @param userId
     * @param browseHistoryIds
     * @return code
     */
    public int postDeleteMultiBrowseHistoryInfo(Long userId,List<Long> browseHistoryIds) {
        return browseHistoryModelMapperImpl.deleteMultiBrowseHistoryByBrowseHistoryIds(userId,browseHistoryIds);
    }
    /**
     * 获取topic的浏览数量
     * @param topicId
     * @return
     */
    public Long getSelectTopicBrowseInfoAmountByTopicId(Long topicId) {
        return browseHistoryModelMapperImpl.selectTopicInfoAmount(topicId);
    }
    /**
     * 查询用户是否浏览过某文章
     * @param userId
     * @param articleId
     * @return
     */
    public int postSelectUserIsBrowseArticle(Long userId,Long articleId) {
        return browseHistoryModelMapperImpl.selectUserIdBrowseArticle(userId, articleId);
    }

}
