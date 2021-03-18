package org.leafbook.serviceMarketplaceApi.dao;

import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class AuctionModelMapper {
    public Long insertSingleTopicForResId(Long userId,Long topic) {
        return (long)new Random().nextInt(10000);
    }

    public List<AuctionModel> selectMultiByAuctionIds(List<Long> auctionIds) {
        List<AuctionModel> auctionModelList = new LinkedList<>();

        for (Long auctionId:auctionIds) {
            AuctionModel auctionModel = new AuctionModel();
            auctionModel.setAuctionId(auctionId);
            auctionModel.setUserId(1111L);
            auctionModel.setTopicId(1145L);
            auctionModel.setType(0);
            auctionModel.setCurrentPrice(155L);
            auctionModel.setExpireTimestamp(111111111L);
            auctionModel.setStartPrice(100L);

            auctionModelList.add(auctionModel);
        }

        return auctionModelList;
    }
}
