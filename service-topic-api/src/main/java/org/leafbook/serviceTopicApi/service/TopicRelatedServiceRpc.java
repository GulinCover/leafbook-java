package org.leafbook.serviceTopicApi.service;

import org.leafbook.api.modelApi.topicInfo.DirectoryModel;
import org.leafbook.api.modelApi.topicInfo.DirectoryModifyModel;
import org.leafbook.api.modelApi.topicInfo.TopicLikedAndTreadAndBrowseModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.serviceTopicApi.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TopicRelatedServiceRpc {
    @Autowired
    private TopicModelMapper topicModelMapper;

    @Autowired
    private Topic2EntryModelMapper topic2EntryModelMapper;

    @Autowired
    private TopicEntryInfoShowModelMapper topicEntryInfoShowModelMapper;

    @Autowired
    private TopicLikedAndTreadAndBrowseModelMapper topicLikedAndTreadAndBrowseModelMapper;

    @Autowired
    private DirectoryModelMapper directoryModelMapper;

    @Autowired
    private DirectoryModifyModelMapper directoryModifyModelMapper;

    /**
     * 单查询
     * @param topicId
     * @return
     */
    public TopicModel getSelectSingleTopicInfo(Long topicId) {
        return topicModelMapper.selectById(topicId);
    }

    /**
     * 组查询
     * @param topicIds
     * @return
     */
    public List<TopicModel> getSelectMultiTopicInfo(List<Long> topicIds) {
        return topicModelMapper.selectByIds(topicIds);
    }
    /**
     * 创建著述
     * @param userId
     * @param topicTitle
     * @param topicDesc
     * @param entryIds
     * @return topicId
     */
    public Long postCreateTopicInfo(Long userId,String topicTitle,String topicDesc,List<Long> entryIds) {
        TopicModel topicModel = new TopicModel();
        topicModel.setTopicTitle(topicTitle);
        topicModel.setTopicDesc(topicDesc);
        topicModel.setUserId(userId);
        int ret = topic2EntryModelMapper.insertByIds(userId ,topicModel.getTopicId(),entryIds);
        if (ret == 0) return 0L;

        return topicModelMapper.insert(topicModel);
    }
    /**
     * 更换著述封面
     * @param userId
     * @param topicId
     * @param cover
     * @return
     */
    public int postUpdateSingleTopicInfoForCover(Long userId,Long topicId,String cover) {
        TopicModel topicModel = topicModelMapper.selectById(topicId);
        if (!topicModel.getUserId().equals(userId)) return 403;

        topicModel.setTopicAvatar(cover);

        return topicModelMapper.updateByModel(topicModel);
    }

    /**
     * 更改著述描述
     * @param userId
     * @param topicId
     * @param topicDesc
     * @return code
     */
    public int postUpdateTopicInfoDesc(Long userId,Long topicId,String topicDesc) {
        TopicModel topicModel = topicModelMapper.selectById(topicId);
        if (Objects.equals(topicModel.getUserId(), userId)) {
            return topicModelMapper.updateForDescByTopicId(topicId,topicDesc);
        }
        return 403;
    }
    /**
     * 查询著述下默认文章目录顺序
     * @param topicId
     * @return
     */
    public List<DirectoryModel> getSelectDirectoryInfo(Long topicId) {
        return directoryModelMapper.selectByTopicId(topicId);
    }
    /**
     * 修改著述默认文章顺序,
     * 记录修改者，原文章id和新文章id
     * @param userId
     * @param topicId
     * @param pageId
     * @param articleId
     * @return code
     */
    public int postUpdateDirectoryInfo( Long userId, Long topicId,Long pageId, Long articleId) {
        TopicModel topicModel = topicModelMapper.selectById(topicId);
        if (!topicModel.getUserId().equals(userId)) return 403;

        DirectoryModifyModel directoryModifyModel = new DirectoryModifyModel();
        directoryModifyModel.setDistArticleId(articleId);

        //判断是否有这一页
        boolean flag = true;
        List<DirectoryModel> directoryModelList = directoryModelMapper.selectByTopicId(userId);
        for (DirectoryModel directoryModel:directoryModelList) {
            if (directoryModel.getPageNumber().equals(pageId)) {
                flag = false;
                directoryModifyModel.setSrcArticleId(directoryModel.getArticleId());
                directoryModel.setArticleId(articleId);
                if (directoryModelMapper.updateByModel(directoryModel) == 0) return 500;
            }
        }
        if (flag) return 4000;

        return directoryModifyModelMapper.insertByModel(directoryModifyModel);
    }
    /**
     * 添加词条
     * @param userId
     * @param topicId
     * @param entryId
     * @return code
     */
    public int postAddTopicInfoEntry(Long userId,Long topicId,Long entryId) {
        return topic2EntryModelMapper.insertById(userId,topicId,entryId);
    }

    /**
     * 查询著述词条数量大于50的进行展示
     * @param topicId
     * @return entryIds
     */
    public List<Long> getSelectSingleTopicInfoForEntryIds(Long topicId) {
        return topicEntryInfoShowModelMapper.selectMultiById(topicId);
    }
    /**
     * 给著述点赞，著述赞数加1
     * @param topicId
     * @return
     */
    public int postInsertTouchTopicStar(Long topicId) {
        TopicLikedAndTreadAndBrowseModel model = topicLikedAndTreadAndBrowseModelMapper.selectSingleByTopicId(topicId);
        Long likedAmount = model.getLikedAmount()+ 1;
        model.setLikedAmount(likedAmount);
        return topicLikedAndTreadAndBrowseModelMapper.updateByModel(model);
    }
    /**
     * 给著述点踩，著述踩数加1
     * @param topicId
     * @return
     */
    public int postInsertTouchTopicTread(Long topicId) {
        TopicLikedAndTreadAndBrowseModel model = topicLikedAndTreadAndBrowseModelMapper.selectSingleByTopicId(topicId);
        Long treadAmount = model.getTreadAmount()+ 1;
        model.setLikedAmount(treadAmount);
        return topicLikedAndTreadAndBrowseModelMapper.updateByModel(model);
    }
    /**
     * 浏览著述，浏览量加1
     * @param topicId
     * @return
     */
    public int postInsertTouchTopicBrowse(Long topicId) {
        TopicLikedAndTreadAndBrowseModel model = topicLikedAndTreadAndBrowseModelMapper.selectSingleByTopicId(topicId);
        Long browseAmount = model.getBrowseAmount()+ 1;
        model.setBrowseAmount(browseAmount);
        return topicLikedAndTreadAndBrowseModelMapper.updateByModel(model);
    }
}
