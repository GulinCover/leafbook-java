package org.leafbook.serviceRecordApi.daoImpl;

import org.leafbook.api.modelApi.recordInfo.BrowseHistoryModel;
import org.leafbook.serviceRecordApi.dao.BrowseHistoryModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class BrowseHistoryModelMapperImpl {
    @Autowired
    private BrowseHistoryModelMapper browseHistoryModelMapper;
    /**
     * 查询用户是否浏览过某文章
     * @param userId
     * @param articleId
     * @return
     */
    public int selectUserIdBrowseArticle(Long userId,Long articleId) {
        return browseHistoryModelMapper.selectDetectIsExistArticleIdByUserId(userId,articleId);
    }
    /**
     * 获取topic的浏览数量
     * @param topicId
     * @return
     */
    public Long selectTopicInfoAmount(Long topicId) {
        return browseHistoryModelMapper.selectTopicBrowseAmount(topicId);
    }

    public List<BrowseHistoryModel> selectMultiBrowseHistoryByUserIdAndPage(Long userId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return browseHistoryModelMapper.selectMultiBrowseHistoryByUserId(userId,start,end);
    }

    public int insert(BrowseHistoryModel browseHistoryModel) {
        browseHistoryModel.setVersion(1);
        browseHistoryModel.setIsBlack(0);
        browseHistoryModel.setHistoryModelId(IdGeneratorTools.nextId());
        return browseHistoryModelMapper.insert(browseHistoryModel);
    }

    /**
     * 单逻辑删除
     * @param userId
     * @param browseHistoryId
     * @return
     */
    public int deleteSingleBrowseHistoryByBrowseHistoryId(Long userId,Long browseHistoryId) {
        return browseHistoryModelMapper.deleteSingleBrowseHistoryByUserId(userId,browseHistoryId);
    }
    /**
     * 组逻辑删除
     * @param userId
     * @param browseHistoryIds
     * @return
     */
    public int deleteMultiBrowseHistoryByBrowseHistoryIds(Long userId,List<Long> browseHistoryIds) {
        return browseHistoryModelMapper.deleteMultiBrowseHistoryByUserId(userId,browseHistoryIds);
    }

}
