package org.leafbook.serviceTopicApi.service;

import org.leafbook.api.modelApi.topicInfo.*;
import org.leafbook.serviceTopicApi.dao.Topic2EntryModelMapper;
import org.leafbook.serviceTopicApi.daoImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Transactional
@Service
public class TopicRelatedServiceRpc {
    @Autowired
    private TopicModelMapperImpl topicModelMapperImpl;

    @Autowired
    private ManagerModelMapperImpl managerModelMapperImpl;

    @Autowired
    private Topic2EntryModelMapperImpl topic2EntryModelMapperImpl;

    @Autowired
    private TopicEntryInfoShowModelMapperImpl topicEntryInfoShowModelMapperImpl;

    @Autowired
    private TopicLikedAndTreadAndBrowseModelMapperImpl topicLikedAndTreadAndBrowseModelMapperImpl;

    @Autowired
    private DirectoryModelMapper directoryModelMapper;

    @Autowired
    private DirectoryModifyModelMapper directoryModifyModelMapper;

    @Autowired
    private ContributorModelMapperImpl contributorModelMapperImpl;

    /**
     * 获取著述拥有者id
     * @param topicId
     * @return
     */
    public Long getSelectTopicOwner(Long topicId) {
        return topicModelMapperImpl.selectSingleForOwnerId(topicId);
    }
    /**
     * 获取所有著述管理者id
     * @param topicId
     * @return
     */
    public List<Long> getSelectTopicManager(Long topicId) {
        return managerModelMapperImpl.selectMultiForManagerId(topicId);
    }
    /**
     * 添加管理者
     * @param topicId
     * @return
     */
    public int postAddTopicManager(Long userId,Long topicId,Long managerId) {
        int ret = topicModelMapperImpl.selectDecideByUserIdAndTopicId(userId,topicId);
        if (ret == 0) return 403;

        ManagerModel managerModel = new ManagerModel();
        managerModel.setUserId(managerId);
        managerModel.setTopicId(topicId);
        return managerModelMapperImpl.insertByModel(managerModel);
    }
    /**
     * 删除管理者
     * @param topicId
     * @return
     */
    public int postDeleteTopicManager(Long userId,Long topicId,Long managerId) {
        int ret = topicModelMapperImpl.selectDecideByUserIdAndTopicId(userId,topicId);
        if (ret == 0) return 403;

        return managerModelMapperImpl.deleteSingleByManagerUserId(managerId,topicId);
    }


    /**
     * 文章拥有者权限检测
     * @param userId
     * @param topicId
     * @return
     */
    public int postTopicOwnerAuthorityDecide(Long userId,Long topicId) {
        return topicModelMapperImpl.selectDecideByUserIdAndTopicId(userId,topicId);
    }

    /**
     * 文章管理者权限检测
     * @param userId
     * @param topicId
     * @return
     */
    public int postTopicManagerAuthorityDecide(Long userId,Long topicId) {
        return managerModelMapperImpl.selectDecideByManagerUserIdAndTopicId(userId,topicId);
    }

    /**
     * 单查询
     * @param topicId
     * @return
     */
    public TopicModel getSelectSingleTopicInfo(Long topicId) {
        return topicModelMapperImpl.selectById(topicId);
    }

    /**
     * 组查询
     * @param topicIds
     * @return
     */
    public List<TopicModel> getSelectMultiTopicInfo(List<Long> topicIds) {
        return topicModelMapperImpl.selectByIds(topicIds);
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

        Long topicId = topicModelMapperImpl.insert(topicModel);
        if (topicId == 0) return 0L;
        if (topicEntryInfoShowModelMapperImpl.insertByIds(topicId,entryIds) == 0) return 0L;

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
        int ret = topicModelMapperImpl.selectDecideByUserIdAndTopicId(userId,topicId);
        if (ret == 0) return 403;

        return topicModelMapperImpl.updateCover(topicId,cover);
    }

    /**
     * 更改著述描述
     * @param userId
     * @param topicId
     * @param topicDesc
     * @return code
     */
    public int postUpdateTopicInfoDesc(Long userId,Long topicId,String topicDesc) {
        int ret = topicModelMapperImpl.selectDecideByUserIdAndTopicId(userId,topicId);
        if (ret == 0) return 403;

        return topicModelMapperImpl.updateDesc(topicId,topicDesc);
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
        int ret = topicModelMapperImpl.selectDecideByUserIdAndTopicId(userId,topicId);
        int ret2 = managerModelMapperImpl.selectDecideByManagerUserIdAndTopicId(userId,topicId);
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
        return topic2EntryModelMapperImpl.insertById(userId,topicId,entryId);
    }

    /**
     * 查询著述词条数量大于50的进行展示
     * @param topicId
     * @return entryIds
     */
    public List<Long> getSelectSingleTopicInfoForEntryIds(Long topicId) {
        return topicEntryInfoShowModelMapperImpl.selectMultiEntryIdsById(topicId);
    }
    /**
     * 给著述点赞，著述赞数加1
     * @param topicId
     * @return
     */
    public int postInsertTouchTopicStar(Long topicId) {
        TopicLikedAndTreadAndBrowseModel model = topicLikedAndTreadAndBrowseModelMapperImpl.selectSingleByTopicId(topicId);
        Long likedAmount = model.getLikedAmount()+ 1;
        model.setLikedAmount(likedAmount);
        return topicLikedAndTreadAndBrowseModelMapperImpl.updateByModel(model);
    }

    /**
     * 给著述点踩，著述踩数加1
     * @param topicId
     * @return
     */
    public int postInsertTouchTopicTread(Long topicId) {
        TopicLikedAndTreadAndBrowseModel model = topicLikedAndTreadAndBrowseModelMapperImpl.selectSingleByTopicId(topicId);
        Long treadAmount = model.getTreadAmount()+ 1;
        model.setLikedAmount(treadAmount);
        return topicLikedAndTreadAndBrowseModelMapperImpl.updateByModel(model);
    }
    /**
     * 浏览著述，浏览量加1
     * @param topicId
     * @return
     */
    public int postInsertTouchTopicBrowse(Long topicId) {
        TopicLikedAndTreadAndBrowseModel model = topicLikedAndTreadAndBrowseModelMapperImpl.selectSingleByTopicId(topicId);
        Long browseAmount = model.getBrowseAmount()+ 1;
        model.setBrowseAmount(browseAmount);
        return topicLikedAndTreadAndBrowseModelMapperImpl.updateByModel(model);
    }
    /**
     * 获取某词条下的著述数量
     * @param entryId
     * @return
     */
    public Long getSelectMultiTopicInfoAmountByEntryInfo(Long entryId) {
        return topicEntryInfoShowModelMapperImpl.selectTopicInfoAmountByEntryId(entryId);
    }
    /**
     * 获取某词条下的所有著述
     * @param entryId
     * @return
     */
    public List<TopicModel> getSelectMultiTopicInfoRpcByEntryId(Long entryId,Long page) {
        return topicEntryInfoShowModelMapperImpl.joinSelectMultiTopicInfoByEntryId(entryId,page);
    }

    /**
     * 获取著述赞数量
     * @param topicId
     * @return
     */
    public Long getSelectTopicStarAmount(Long topicId) {
        return topicLikedAndTreadAndBrowseModelMapperImpl.selectTopicStarAmountByTopicId(topicId);
    }
    /**
     * 获取著述踩数量
     * @param topicId
     * @return
     */
    public Long getSelectTopicTreadAmount(Long topicId) {
        return topicLikedAndTreadAndBrowseModelMapperImpl.selectTopicTreadAmountByTopicId(topicId);
    }
    /**
     * 获取著述浏览数量
     * @param topicId
     * @return
     */
    public Long getSelectTopicBrowseAmount(Long topicId) {
        return topicLikedAndTreadAndBrowseModelMapperImpl.selectTopicBrowseAmountByTopicId(topicId);
    }
    /**
     * 随机获取贡献者信息
     * @param topicId
     * @param randomNumber
     * @return
     */
    public List<ContributorModel> getSelectRandomContributorInfo(Long topicId,Integer randomNumber) {
        return contributorModelMapperImpl.selectRandomContributorInfoByTopicId(topicId,randomNumber);
    }

    /**
     * 获取用户发布的著述
     * @param userId
     * @return
     */
    public List<TopicModel> postSelectMeTopicInfo(Long userId,Long page) {
        return topicModelMapperImpl.selectMultiTopicInfoByUserId(userId,page);
    }
    /**
     * 获取数量
     * @param userId
     * @return
     */
    public Long postSelectMeTopicInfoPage(Long userId) {
        return topicModelMapperImpl.selectTopicInfoAmount(userId);
    }

    /**
     * 模糊搜索自己拥有的著述
     * @param userId
     * @param blurry
     * @param page
     * @return
     */
    public List<TopicModel> postSelectMultiTopicInfoByUserIdRpc(Long userId,String blurry,Long page) {
        return topicModelMapperImpl.selectMultiTopicInfoByUserId(userId,blurry,page);
    }

    /**
     * 获取点赞排行著述id,每页20条
     * @param page
     * @return
     */
    public List<Long> getSelectMultiTopicIdByStarRank(Long page) {
        return topicLikedAndTreadAndBrowseModelMapperImpl.selectStarRank(page);
    }
    /**
     * 获取topic总数量
     * @return
     */
    public Long getSelectAllTopicPageAmount() {
        return topicModelMapperImpl.selectAllTopicInfoPageAmount();
    }

    /**
     * 获取著述的管理者数量
     * @param topicId
     * @return
     */
    public Long getSelectManagerAmountByTopicId(Long topicId) {
        return managerModelMapperImpl.selectManagerAmount(topicId);
    }
    /**
     * 获取著述的贡献者id
     *
     * @param topicId
     * @param page
     * @return
     */
    public List<Long> postSelectMultiContributorIdByTopicId(Long topicId,Long page) {
        return contributorModelMapperImpl.selectMultiContributorId(topicId, page);
    }

    /**
     * 获取著述的贡献者数量
     * @param topicId
     * @return
     */
    public Long postSelectMultiContributorIdByTopicIdPage(Long topicId) {
        return contributorModelMapperImpl.selectContributorAmount(topicId);
    }

    /**
     * 根据词条随机查询5~8条著述
     * @param entryId
     * @return
     */
    public List<TopicModel> getSelectRandomMultiTopicInfoByEntryId(Long entryId) {
        Long number = (long)(new Random().nextInt(4)+5);
        List<Long> topicIds = topicEntryInfoShowModelMapperImpl.selectRandomTopicIdsByEntryId(entryId,number);
        return topicModelMapperImpl.selectByIds(topicIds);
    }

    /**
     * 更改著述点赞数量
     * @param topicId
     * @return
     */
    public int postUpdateTopicStarAmount(Long topicId) {
        return topicLikedAndTreadAndBrowseModelMapperImpl.updateStarAmountByTopicId(topicId);
    }
    /**
     * 更改著述点踩数量
     * @param topicId
     * @return
     */
    public int postUpdateTopicTreadAmount(Long topicId) {
        return topicLikedAndTreadAndBrowseModelMapperImpl.updateTreadAmountByTopicId(topicId);
    }

    /**
     * 搜索指定著述
     * @param status://0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用
     * @param entryIds
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    public List<TopicModel> getSelectSearchMultiTopicInfo(
            Integer status,
            List<Long> entryIds,
            String content,
            Long startTime,
            Long endTime,
            Long page
    ) {
        return topicModelMapperImpl.jointTopicShowEntryTableSelectSearchMultiTopicInfo(status,entryIds,content,startTime,endTime,page);
    }
    /**
     * 获取搜索条数
     * @param status
     * @param entryIds
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    public Long getSelectSearchMultiTopicInfoPage(
            Integer status,
            List<Long> entryIds,
            String content,
            Long startTime,
            Long endTime,
            Long page
    ) {
        return topicModelMapperImpl.jointTopicShowEntryTableSelectSearchMultiTopicInfoAmount(status,entryIds,content,startTime,endTime,page);
    }

    /**
     * 搜索指定著述
     * @param status://0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用
     * @param content
     * @return
     */
    public List<TopicModel> getSelectSearchMultiTopicInfoByContent(Integer status,String content,Long page) {
        return topicModelMapperImpl.jointTopicShowEntryTableSelectSearchMultiTopicInfo(status,content,page);
    }
    /**
     * 指定著述数量
     * @param status://0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用
     * @param content
     * @return
     */
    public Long getSelectSearchMultiTopicInfoByContentPage(
            Integer status,
            String content
    ) {
        return topicModelMapperImpl.jointTopicShowEntryTableSelectSearchMultiTopicInfoAmount(status,content);
    }
    /**
     * 限定搜索
     * @param status://0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用
     * @param entryId
     * @param content
     * @param page
     * @return
     */
    public List<TopicModel> getSelectSearchMultiTopicInfoByContentAndEntryId(Integer status,Long entryId, String content,Long page) {
        return topicModelMapperImpl.jointTopicShowEntryTableSelectSearchMultiTopicInfo(status,entryId,content,page);
    }
    /**
     * 根据topicIds组查entryIds
     * @param topicIds
     * @return
     */
    public List<Long> getSelectEntryIdsByTopicIds(List<Long> topicIds) {
        return topicEntryInfoShowModelMapperImpl.selectMultiByTopicIds(topicIds);
    }
    /**
     * 根据entryIds获取著述数量
     * @param entryIds
     * @return
     */
    public Map<Long,Long> getSelectEntryAmountByEntryIds(List<Long> entryIds) {
        return topicEntryInfoShowModelMapperImpl.selectTopicAmountByEntryIds(entryIds);
    }

    /**
     * 更改用topic拥有者,用于购买后使用时使用
     * @param userId
     * @param topicId
     * @return
     */
    public int postUpdateSingleTopicInfoWithOwner(Long userId,Long topicId) {
        return topicModelMapperImpl.updateSingleTopicInfoByTopicIdWithOwner(userId,topicId);
    }

    /**
     * 获取用户最新几条著述信息
     * @param userId
     * @param number
     * @return
     */
    public List<TopicModel> postSelectMultiLastTopicInfo(Long userId,Integer number) {
        return topicModelMapperImpl.selectMultiLastTopicInfo(userId,number);
    }
    /**
     * 获取著述管理者ids
     * @param topicId
     * @return
     */
    public List<Long> postSelectAllManagerUserIds(Long topicId) {
        return managerModelMapperImpl.selectAllManagerUserIds(topicId);
    }
    /**
     * 判断topicId是否存在
     * @param topicId
     * @return
     */
    public int postIsExistForTopicInfo(Long topicId) {
        return topicModelMapperImpl.selectDetectTopicInfoByTopicId(topicId);
    }

    /**
     * 获取用户发布了的著述数量
     * @param userId
     * @return
     */
    public Long getSelectTopicAmountByUserId(Long userId) {
        return topicModelMapperImpl.selectTopicInfoAmount(userId);
    }

    /**
     * 获取点赞排行总数量
     *
     * @return
     */
    public Long getSelectMultiTopicIdByStarRankPageAmount() {
        return topicLikedAndTreadAndBrowseModelMapperImpl.selectAllStarRankPageAmount();
    }

    /**
     * 创建赞踩表
     * @param topicId
     * @return
     */
    public int postCreateTopicLikedAndTread(Long topicId) {
        return topicLikedAndTreadAndBrowseModelMapperImpl.insertByTopicId(topicId);
    }

    /**
     * 点赞排行模糊搜索
     * @param page
     * @param blurry
     * @param entry
     * @param startTime
     * @param endTime
     * @return
     */
    public List<TopicModel> getSelectMultiBlurrySearchRpc(
            Long page,String blurry,Long entry,Long startTime,Long endTime
    ) {
        return topicModelMapperImpl.jointTopicLikedAndTreadModelTableSelectSearchMultiTopicInfo(page,blurry,entry,startTime,endTime);
    }

    /**
     * 点赞排行模糊搜索数据数量
     * @param page
     * @param blurry
     * @param entry
     * @param startTime
     * @param endTime
     * @return
     */
    public Long getSelectMultiBlurrySearchPageAmountRpc(
            Long page,String blurry,Long entry,Long startTime,Long endTime
    ) {
        return topicModelMapperImpl.jointTopicLikedAndTreadModelTableSelectSearchMultiTopicInfoPageAmount(page,blurry,entry,startTime,endTime);
    }

    /**
     * 获取词条下所有topic数量
     * @param entryId
     * @return
     */
    public Long getSelectAllTopicInfoPageAmountByEntryId(Long entryId) {
        return topicModelMapperImpl.joinSelectTopicAmountByEntryId(entryId);
    }
    /**
     * 随机获取正在拍卖的topic
     * @param entryId
     * @return
     */
    public List<Long> getSelectRandomMultiTopicIdsByEntryIdForAuctionInfo(Long entryId) {
        return topicModelMapperImpl.selectRandomTopicIdsByEntryIdForAuction(entryId);
    }
}
