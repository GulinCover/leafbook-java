package org.leafbook.serviceMarketplaceApi.service;

import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.api.modelApi.billInfo.BidingModel;
import org.leafbook.api.modelApi.billInfo.BillModel;
import org.leafbook.serviceMarketplaceApi.daoImpl.AuctionModelMapperImpl;
import org.leafbook.serviceMarketplaceApi.daoImpl.BidingModelMapperImpl;
import org.leafbook.serviceMarketplaceApi.daoImpl.BillModelMapperImpl;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MarketplaceRelatedServiceRpc {
    @Autowired
    private AuctionModelMapperImpl auctionModelMapperImpl;
    @Autowired
    private BillModelMapperImpl billModelMapperImpl;
    @Autowired
    private BidingModelMapperImpl bidingModelMapperImpl;

    /**
     * 购买改名卡
     *
     * @param userId
     * @param uuid
     * @return code
     */
    public int postBuySingleRenameCard(Long userId, String uuid, Long resId) {
        //生成账单
        BillModel billModel = new BillModel();
        billModel.setBuyerId(userId);
        billModel.setResId(IdGeneratorTools.nextId());
        billModel.setBuyerUuid(uuid);
        billModel.setPrice(50L);
        billModel.setSellerId(1L);
        billModel.setSellerUuid("1");

        return billModelMapperImpl.insert(billModel);
    }

    /**
     * 上架物品
     *
     * @param userId
     * @param auctionId
     * @param price
     * @param expireTimestamp
     * @return code
     */
    public int postAuctionSingleResInfo(Long userId, Long auctionId, Long price, Long expireTimestamp) {
        AuctionModel auctionModel = new AuctionModel();
        auctionModel.setExpireTimestamp(expireTimestamp);
        auctionModel.setStartPrice(price);
        auctionModel.setStatus(2);
        return auctionModelMapperImpl.update(auctionModel);
    }

    /**
     * 使用改名卡，并将名称存储下来
     *
     * @param userId
     * @param auctionId
     * @param newNickname
     * @return code
     */
    public int postUseSingleRenameCard(Long userId, Long auctionId, String newNickname) {
        AuctionModel auctionModel = new AuctionModel();
        auctionModel.setUserId(userId);
        auctionModel.setAuctionId(auctionId);
        auctionModel.setNickname(newNickname);
        auctionModel.setAuctionType(1);
        auctionModel.setStatus(1);
        return auctionModelMapperImpl.update(auctionModel);
    }

    /**
     * 查询用户下所有正在售卖的物品
     *
     * @param userId
     * @return
     */
    public List<AuctionModel> postSelectMultiAuctionInfo(Long userId) {
        return auctionModelMapperImpl.selectMultiAuctionInfo(userId);
    }

    /**
     * 查询校验
     *
     * @param userId
     * @param auctionId
     * @return
     */
    public AuctionModel postSelectSingleResInfo(Long userId, Long auctionId) {
        return auctionModelMapperImpl.selectVerifySingleAuctionInfo(userId, auctionId);
    }

    /**
     * 竞拍物品
     *
     * @param userId
     * @param auctionId
     * @param price
     * @return code
     */
    public int postBidingSingleAuctionInfo(Long userId, Long auctionId, Long price) {
        BidingModel bidingModel = new BidingModel();
        bidingModel.setPrice(price);
        bidingModel.setAuctionId(auctionId);
        bidingModel.setUserId(userId);
        return bidingModelMapperImpl.insertSingleModel(bidingModel);
    }

    /**
     * 获取number条拍卖物品信息
     *
     * @param number
     * @return
     */
    public List<AuctionModel> getSelectRandomMultiAuctionInfo(Integer number) {
        return auctionModelMapperImpl.selectRandomNumberAuctionInfo(number);
    }

    /**
     * 随机获取最新number条拍卖品信息
     *
     * @return
     */
    public List<AuctionModel> getSelectRandomMultiLatestAuction(Integer number) {
        return auctionModelMapperImpl.selectNumberLatestAuctionInfo(number);
    }

    /**
     * 获取拍卖物品信息
     *
     * @param auctionId
     * @return
     */
    public AuctionModel getSelectSingleAuctionInfo(Long auctionId) {
        return auctionModelMapperImpl.selectSingleAuctionInfoByAuctionId(auctionId);
    }

    /**
     * 检测auctionId合法性
     *
     * @param auctionId
     * @return
     */
    public int postSelectDetectAuctionIdLegality(Long auctionId) {
        return auctionModelMapperImpl.selectAuctionInfoIsExist(auctionId);
    }

    /**
     * 更改买拍信息当前最高价
     *
     * @param auctionModel
     * @return
     */
    public int postUpdateAuctionInfoByAuctionInfo(AuctionModel auctionModel) {
        return auctionModelMapperImpl.updateAuctionInfoByAuctionInfo(auctionModel);
    }

    /**
     * 模糊搜索昵称拍卖物品
     *
     * @param type://0:topic,1:nickname,2:renameCard
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    public List<AuctionModel> getSelectSearchMultiNicknameAuctionInfo(
            Integer type,
            String content,
            Long startTime,
            Long endTime,
            Long page) {
        return auctionModelMapperImpl.selectSearchMultiNickname(type, content, startTime, endTime, page);
    }

    /**
     * 获取条数
     *
     * @param type
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    public Long getSelectSearchMultiNicknameAuctionInfoPage(
            Integer type,
            String content,
            Long startTime,
            Long endTime,
            Long page) {
        return auctionModelMapperImpl.selectSearchMultiNicknameAmount(type, content, startTime, endTime, page);
    }

    /**
     * 根据topicIds查询拍卖物品
     *
     * @param topicIds
     * @return
     */
    public List<AuctionModel> getSelectMultiAuctionInfoByTopicIds(List<Long> topicIds) {
        return auctionModelMapperImpl.selectMultiAuctionByTopicIds(topicIds);
    }

    /**
     * 查询用户账单
     *
     * @param userId
     * @param page
     * @return
     */
    public List<BillModel> postSelectMultiBillInfoByUserId(Long userId, Long page) {
        return billModelMapperImpl.selectMultiBillInfoByUserId(userId, page);
    }

    /**
     * 查询用户账单总条数
     *
     * @param userId
     * @return
     */
    public Long postSelectMultiBillInfoByUserIdPage(Long userId) {
        return billModelMapperImpl.selectMultiBillInfoAmountByUserId(userId);
    }

    /**
     * 查询用户正在售卖的物品
     *
     * @param userId
     * @param page
     * @return
     */
    public List<AuctionModel> postSelectMultiAuctionInfoByUserId(Long userId, Long page) {
        return auctionModelMapperImpl.selectMultiAuctionInfo(userId, page);
    }

    /**
     * 获取用户正在上架总条数
     *
     * @param userId
     * @return
     */
    public Long postSelectMultiAuctionInfoByUserIdPage(Long userId) {
        return auctionModelMapperImpl.selectMultiAuctionInfoAmount(userId);
    }

    /**
     * 获取用户竞拍成功的账单
     *
     * @param userId
     * @param page
     * @return
     */
    public List<BillModel> postSelectMultiPhotographedBillInfoByUserId(Long userId, Long page) {
        return billModelMapperImpl.selectMultiPhotographedBillInfoByUserId(userId, page);
    }

    /**
     * 获取用户竞拍成功的条数
     *
     * @param userId
     * @return
     */
    public Long postSelectMultiPhotographedBillInfoByUserIdPage(Long userId) {
        return billModelMapperImpl.selectMultiPhotographedBillInfoAmountByUserId(userId);
    }

    /**
     * 获取用户竞拍失败的信息
     *
     * @param userId
     * @param page
     * @return
     */
    public List<BidingModel> postSelectMultiBidingFailedInfo(Long userId, Long page) {
        return bidingModelMapperImpl.selectMultiUserBidingFailedInfo(userId, page);
    }

    /**
     * 获取用户竞拍失败的信息数量
     *
     * @param userId
     * @return
     */
    public Long postSelectMultiBidingFailedInfoPage(Long userId) {
        return bidingModelMapperImpl.selectMultiUserBidingFailedInfoAmount(userId);
    }

    /**
     * 获取用户正在竞拍的信息
     *
     * @param userId
     * @param page
     * @return
     */
    public List<BidingModel> postSelectMultiBidingInfo(Long userId, Long page) {
        return bidingModelMapperImpl.selectMultiUserBidingInfo(userId, page);
    }

    /**
     * 获取用户正在竞拍的信息的数量
     *
     * @param userId
     * @return
     */
    public Long postSelectMultiBidingInfoPage(Long userId) {
        return bidingModelMapperImpl.selectMultiUserBidingInfoAmount(userId);
    }
}
