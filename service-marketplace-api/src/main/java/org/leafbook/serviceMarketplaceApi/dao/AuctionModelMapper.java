package org.leafbook.serviceMarketplaceApi.dao;

import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class AuctionModelMapper {
    /**
     * 检测auctionId合法性
     * @param auctionId
     * @return
     */
    public int selectAuctionInfoIsExist(Long auctionId) {
        return 1;
    }

    /**
     * 获取拍卖物品信息
     * @param auctionId
     * @return
     */
    public AuctionModel selectSingleAuctionInfoByAuctionId(Long auctionId) {
        AuctionModel auctionModel = new AuctionModel();
        auctionModel.setAuctionId(auctionId);
        auctionModel.setUserId(31232L);
        auctionModel.setTopicId(1145L);
        auctionModel.setType(0);
        auctionModel.setCurrentPrice(155L);
        auctionModel.setCurrentPriceUserUuid("dasd-231w-321d-das2");
        auctionModel.setCurrentPriceUserId(3122L);
        auctionModel.setExpireTimestamp(111111111L);
        auctionModel.setStartPrice(100L);
        return auctionModel;
    }

    /**
     * 获取number条拍卖物品信息
     * @param number
     * @return
     */
    public List<AuctionModel> selectRandomNumberAuctionInfo(Integer number) {
        List<AuctionModel> auctionModelList = new LinkedList<>();

        for (int i = 0;i<number;++i) {
            AuctionModel auctionModel = new AuctionModel();
            auctionModel.setAuctionId((long) i);
            auctionModel.setUserId(31231L);
            auctionModel.setTopicId(1145L);
            auctionModel.setType(0);
            auctionModel.setCurrentPrice(155L);
            auctionModel.setCurrentPriceUserUuid("dasd-231w-321d-das2");
            auctionModel.setCurrentPriceUserId(3122L);
            auctionModel.setExpireTimestamp(111111111L);
            auctionModel.setStartPrice(100L);

            auctionModelList.add(auctionModel);
        }

        return auctionModelList;
    }
    /**
     * 随机获取最新number条拍卖品信息
     * @return
     */
    public List<AuctionModel> selectRandomNumberLatestAuctionInfo(Integer number) {
        return selectRandomNumberAuctionInfo(number);
    }

    public List<AuctionModel> selectMultiByAuctionIds(List<Long> auctionIds,Integer page) {
        List<AuctionModel> auctionModelList = new LinkedList<>();

        for (Long auctionId:auctionIds) {
            AuctionModel auctionModel = new AuctionModel();
            auctionModel.setAuctionId(auctionId);
            auctionModel.setUserId(312L);
            auctionModel.setTopicId(1145L);
            auctionModel.setType(0);
            auctionModel.setCurrentPrice(155L);
            auctionModel.setCurrentPriceUserUuid("dasd-231w-321d-das2");
            auctionModel.setCurrentPriceUserId(3122L);
            auctionModel.setExpireTimestamp(111111111L);
            auctionModel.setStartPrice(100L);

            auctionModelList.add(auctionModel);
        }

        return auctionModelList;
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
            auctionModel.setCurrentPriceUserUuid("dasd-231w-321d-das2");
            auctionModel.setCurrentPriceUserId(3122L);
            auctionModel.setExpireTimestamp(111111111L);
            auctionModel.setStartPrice(100L);

            auctionModelList.add(auctionModel);
        }

        return auctionModelList;
    }

    public AuctionModel selectVerifySingleAuctionInfo(Long userId,Long auctionId) {
        if (new Random().nextInt(2) == 1) return null;

        AuctionModel auctionModel = new AuctionModel();
        auctionModel.setAuctionId(auctionId);
        auctionModel.setUserId(userId);
        auctionModel.setTopicId(1145L);
        auctionModel.setType(0);
        auctionModel.setCurrentPrice(155L);
        auctionModel.setCurrentPriceUserUuid("dasd-231w-321d-das2");
        auctionModel.setCurrentPriceUserId(3122L);
        auctionModel.setExpireTimestamp(111111111L);
        auctionModel.setStartPrice(100L);
        return auctionModel;
    }

    public int update(AuctionModel auctionModel) {
        return 1;
    }

    public int updateMaxPrice(Long price) {
        return 1;
    }
    /**
     * 更改买拍信息当
     * @param auctionModel
     * @return
     */
    public int updateAuctionInfoByAuctionInfo(AuctionModel auctionModel) {
        return 1;
    }

    public Long insertSingleTopicForResId(Long userId,Long topic) {
        return (long)new Random().nextInt(10000);
    }

    public Long insertSingleForAuctionId(AuctionModel auctionModel) {
        return (long)new Random().nextInt(10000);
    }
}
