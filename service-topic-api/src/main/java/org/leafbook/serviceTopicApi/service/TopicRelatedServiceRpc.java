package org.leafbook.serviceTopicApi.service;

import org.leafbook.api.modelApi.topicInfo.*;
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
    private MangerModelMapper mangerModelMapper;

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

    @Autowired
    private ContributorModelMapper contributorModelMapper;

    /**
     * 获取著述拥有者id
     * @param topicId
     * @return
     */
    public Long getSelectTopicOwner(Long topicId) {
        return topicModelMapper.selectSingleForOwnerId(topicId);
    }
    /**
     * 获取所有著述管理者id
     * @param topicId
     * @return
     */
    public List<Long> getSelectTopicManager(Long topicId) {
        return mangerModelMapper.selectMultiForManagerId(topicId);
    }
    /**
     * 添加管理者
     * @param topicId
     * @return
     */
    public int postAddTopicManager(Long userId,Long topicId,Long managerId) {
        int ret = topicModelMapper.selectDecideByUserIdAndTopicId(userId,topicId);
        if (ret == 0) return 403;

        ManagerModel managerModel = new ManagerModel();
        managerModel.setUserId(managerId);
        managerModel.setTopicId(topicId);
        return mangerModelMapper.insertByModel(managerModel);
    }
    /**
     * 删除管理者
     * @param topicId
     * @return
     */
    public int postDeleteTopicManager(Long userId,Long topicId,Long managerId) {
        int ret = topicModelMapper.selectDecideByUserIdAndTopicId(userId,topicId);
        if (ret == 0) return 403;

        return mangerModelMapper.deleteSingleByManagerUserId(managerId);
    }


    /**
     * 文章拥有者权限检测
     * @param userId
     * @param topicId
     * @return
     */
    public int postTopicOwnerAuthorityDecide(Long userId,Long topicId) {
        return topicModelMapper.selectDecideByUserIdAndTopicId(userId,topicId);
    }

    /**
     * 文章管理者权限检测
     * @param userId
     * @param topicId
     * @return
     */
    public int postTopicManagerAuthorityDecide(Long userId,Long topicId) {
        return mangerModelMapper.selectDecideByManagerUserIdAndTopicId(userId,topicId);
    }

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

        Long topicId = topicModelMapper.insert(topicModel);
        if (topicId == 0) return 0L;
        if (topicEntryInfoShowModelMapper.insertByIds(topicId,entryIds) == 0) return 0L;

        return topicId;
    }
    /**
     * 更换著述封面
     * @param userId
     * @param topicId
     * @param cover
     * @return
     */
    public int postUpdateSingleTopicInfoForCover(Long userId,Long topicId,String cover) {
        int ret = topicModelMapper.selectDecideByUserIdAndTopicId(userId,topicId);
        if (ret == 0) return 403;

        return topicModelMapper.updateCover(topicId,cover);
    }

    /**
     * 更改著述描述
     * @param userId
     * @param topicId
     * @param topicDesc
     * @return code
     */
    public int postUpdateTopicInfoDesc(Long userId,Long topicId,String topicDesc) {
        int ret = topicModelMapper.selectDecideByUserIdAndTopicId(userId,topicId);
        if (ret == 0) return 403;

        return topicModelMapper.updateDesc(topicId,topicDesc);
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
        int ret = topicModelMapper.selectDecideByUserIdAndTopicId(userId,topicId);
        int ret2 = topicModelMapper.selectDecideByManagerUserIdAndTopicId(userId,topicId);
        if (ret == 0 && ret2 == 0) return 403;

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
    /**
     * 获取某词条下的著述数量
     * @param entryId
     * @return
     */
    public Long getSelectMultiTopicInfoAmountByEntryInfo(Long entryId) {
        return topicEntryInfoShowModelMapper.selectTopicInfoAmountByEntryId(entryId);
    }
    /**
     * 获取某词条下的所有著述
     * @param entryId
     * @return
     */
    public List<TopicModel> getSelectMultiTopicInfoRpcByEntryId(Long entryId,Long page) {
        return topicEntryInfoShowModelMapper.selectMultiTopicInfoByEntryId(entryId,page);
    }

    /**
     * 获取著述赞数量
     * @param topicId
     * @return
     */
    public Long getSelectTopicStarAmount(Long topicId) {
        return topicLikedAndTreadAndBrowseModelMapper.selectTopicStarAmountByTopicId(topicId);
    }
    /**
     * 获取著述踩数量
     * @param topicId
     * @return
     */
    public Long getSelectTopicTreadAmount(Long topicId) {
        return topicLikedAndTreadAndBrowseModelMapper.selectTopicTreadAmountByTopicId(topicId);
    }
    /**
     * 获取著述浏览数量
     * @param topicId
     * @return
     */
    public Long getSelectTopicBrowseAmount(Long topicId) {
        return topicLikedAndTreadAndBrowseModelMapper.selectTopicBrowseAmountByTopicId(topicId);
    }
    /**
     * 随机获取贡献者信息
     * @param topicId
     * @param randomNumber
     * @return
     */
    public List<ContributorModel> getSelectRandomContributorInfo(Long topicId,Integer randomNumber) {
        return contributorModelMapper.selectRandomContributorInfoByTopicId(topicId,randomNumber);
    }
    /**
     * 获取用户发布的著述
     * @param userId
     * @return
     */
    public List<TopicModel> postSelectMeTopicInfo(Long userId) {
        return topicModelMapper.selectMultiTopicInfoByUserId(userId);
    }


}
