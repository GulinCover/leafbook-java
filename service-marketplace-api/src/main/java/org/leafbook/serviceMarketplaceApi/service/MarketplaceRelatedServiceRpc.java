package org.leafbook.serviceMarketplaceApi.service;

import org.leafbook.api.modelApi.billInfo.Auction2EntryModel;
import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.serviceMarketplaceApi.dao.Auction2EntryModelMapper;
import org.leafbook.serviceMarketplaceApi.dao.AuctionModelMapper;
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
    /**
     * 著述结算生成 ‘物品’
     * @param userId
     * @param topicId
     * @return 物品id
     */
    public Long postCreateSettlementSingleAuctionInfo(Long userId,Long topicId) {
        return auctionModelMapper.insertSingleTopicForResId(userId,topicId);
    }

    public List<AuctionModel> getSelectMultiAuctionInfoByEntryId(Long auctionId) {
        List<Long> auctionIds = auction2EntryModelMapper.selectMultiAuctionIdByEntryId(auctionId);
        return auctionModelMapper.selectMultiByAuctionIds(auctionIds);
    }
}
