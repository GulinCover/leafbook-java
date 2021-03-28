package org.leafbook.serviceMarketplaceApi.service;

import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.api.modelApi.billInfo.BidingModel;
import org.leafbook.api.modelApi.billInfo.BillModel;
import org.leafbook.serviceMarketplaceApi.dao.Auction2EntryModelMapper;
import org.leafbook.serviceMarketplaceApi.dao.AuctionModelMapper;
import org.leafbook.serviceMarketplaceApi.dao.BidingModelMapper;
import org.leafbook.serviceMarketplaceApi.dao.BillModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketplaceRelatedServiceRpc {
    @Autowired
    private AuctionModelMapper auctionModelMapper;
    @Autowired
    private BillModelMapper billModelMapper;
    @Autowired
    private Auction2EntryModelMapper auction2EntryModelMapper;
    @Autowired
    private BidingModelMapper bidingModelMapper;
    /**
     * 著述结算生成 ‘物品’
     * @param userId
     * @param topicId
     * @return 物品id
     */
    public Long postCreateSettlementSingleAuctionInfo(Long userId,Long topicId) {
        return auctionModelMapper.insertSingleTopicForResId(userId,topicId);
    }
    /**
     * 根据词条id搜索正在售卖的著述
     * @param entryId
     * @return
     */
    public List<AuctionModel> getSelectMultiAuctionInfoByEntryId(Long entryId,Integer page) {
        List<Long> auctionIds = auction2EntryModelMapper.selectMultiAuctionIdByEntryId(entryId);
        return auctionModelMapper.selectMultiByAuctionIds(auctionIds,page);
    }
    /**
     * 购买改名卡
     * @param userId
     * @param uuid
     * @return code
     */
    public int postBuySingleRenameCard(Long userId,String uuid) {
        //生成物品
        AuctionModel auctionModel = new AuctionModel();
        auctionModel.setType(2);
        auctionModel.setUserId(userId);
        auctionModel.setStatus(0);
        Long auctionId = auctionModelMapper.insertSingleForAuctionId(auctionModel);

        //生成账单
        BillModel billModel = new BillModel();
        billModel.setBuyerId(userId);
        billModel.setResId(auctionId);
        billModel.setBuyerUuid(uuid);
        billModel.setPrice(50L);
        billModel.setSellerId(1L);
        billModel.setSellerUuid("1");

        return billModelMapper.insert(billModel);
    }
    /**
     * 上架物品
     * @param userId
     * @param auctionId
     * @param price
     * @param expireTimestamp
     * @return code
     */
    public int postAuctionSingleResInfo(Long userId,Long auctionId,Long price,Long expireTimestamp) {
        AuctionModel auctionModel = new AuctionModel();
        auctionModel.setExpireTimestamp(expireTimestamp);
        auctionModel.setStartPrice(price);
        auctionModel.setStatus(2);
        return auctionModelMapper.update(auctionModel);
    }
    /**
     * 使用改名卡，并将名称存储下来
     * @param userId
     * @param auctionId
     * @param newNickname
     * @return code
     */
    public int postUseSingleRenameCard(Long userId,Long auctionId,String newNickname) {
        AuctionModel auctionModel = new AuctionModel();
        auctionModel.setUserId(userId);
        auctionModel.setAuctionId(auctionId);
        auctionModel.setNickname(newNickname);
        auctionModel.setType(1);
        auctionModel.setStatus(1);
        return auctionModelMapper.update(auctionModel);
    }
    /**
     * 查询用户下所有物品
     * @param userId
     * @return
     */
    public List<AuctionModel> postSelectMultiResInfo(Long userId) {
        return auctionModelMapper.selectMultiAuctionInfo(userId);
    }
    /**
     * 查询校验
     * @param userId
     * @param auctionId
     * @return
     */
    public AuctionModel postSelectSingleResInfo(Long userId,Long auctionId) {
        return auctionModelMapper.selectVerifySingleAuctionInfo(userId,auctionId);
    }

    public int postBidingSingleAuctionInfo(Long userId,Long auctionId,Long price) {
        BidingModel bidingModel = new BidingModel();
        bidingModel.setPrice(price);
        bidingModel.setAuctionId(auctionId);
        bidingModel.setUserId(userId);
        if (bidingModelMapper.insertSingleModel(bidingModel) == 0) return 0;
        return auctionModelMapper.updateMaxPrice(price);
    }
}
