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
    /**
     * 获取number条拍卖物品信息
     * @param number
     * @return
     */
    public List<AuctionModel> selectNumberAuctionInfo(Integer number) {
        List<AuctionModel> auctionModelList = new LinkedList<>();

        for (int i = 0;i<number;++i) {
            AuctionModel auctionModel = new AuctionModel();
            auctionModel.setAuctionId((long) i);
            auctionModel.setUserId(123L);
            auctionModel.setTopicId(1145L);
            auctionModel.setType(0);
            auctionModel.setCurrentPrice(155L);
            auctionModel.setExpireTimestamp(111111111L);
            auctionModel.setStartPrice(100L);

            auctionModelList.add(auctionModel);
        }

        return auctionModelList;
    }

    public List<AuctionModel> selectMultiByAuctionIds(List<Long> auctionIds,Integer page) {
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

    public Long insertSingleForAuctionId(AuctionModel auctionModel) {
        return (long)new Random().nextInt(10000);
    }

    public int update(AuctionModel auctionModel) {
        return new Random().nextInt(100);
    }

    public List<AuctionModel> selectMultiAuctionInfo(Long userId) {
        List<AuctionModel> auctionModelList = new LinkedList<>();

        for (int i = 0;i<new Random().nextInt(8);++i) {
            AuctionModel auctionModel = new AuctionModel();
            auctionModel.setAuctionId((long) i);
            auctionModel.setUserId(userId);
            auctionModel.setTopicId(1145L);
            auctionModel.setType(0);
            auctionModel.setCurrentPrice(155L);
            auctionModel.setExpireTimestamp(111111111L);
            auctionModel.setStartPrice(100L);

            auctionModelList.add(auctionModel);
        }

        return auctionModelList;
    }

    public AuctionModel selectVerifySingleAuctionInfo(Long userId,Long auctionId) {
        AuctionModel auctionModel = new AuctionModel();
        auctionModel.setAuctionId(auctionId);
        auctionModel.setType(0);
        auctionModel.setUserId(userId);
        auctionModel.setTopicId(3333L);

        return auctionModel;
    }

    public int updateMaxPrice(Long price) {
        return new Random().nextInt(100);
    }
}
