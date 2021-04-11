package org.leafbook.serviceMarketplaceApi.dao;

import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.api.modelApi.billInfo.BidingModel;
import org.leafbook.api.modelApi.billInfo.BillModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class AuctionModelMapper {

    /**
     * 根据topicIds查询拍卖物品
     * @param topicIds
     * @return
     */
    public List<AuctionModel> selectMultiAuctionByTopicIds(List<Long> topicIds) {
        List<AuctionModel> auctionModelList = new LinkedList<>();

        for (int i = 0;i<new Random().nextInt(10) + 5;++i) {
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
            auctionModel.setStatus(2);//正在售卖

            auctionModelList.add(auctionModel);
        }

        return auctionModelList;
    }

    /**
     * 模糊搜索昵称拍卖物品
     * @param type://0:topic,1:nickname,2:renameCard
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    public List<AuctionModel> selectSearchMultiNickname(
            Integer type,
            String content,
            Long startTime,
            Long endTime,
            Long page
    ) {
        List<AuctionModel> auctionModelList = new LinkedList<>();

        for (int i = 0;i<new Random().nextInt(10) + 5;++i) {
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
            auctionModel.setStatus(2);//正在售卖

            auctionModelList.add(auctionModel);
        }

        return auctionModelList;
    }
    /**
     * 获取条数
     * @param type
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    public Long selectSearchMultiNicknameAmount(
            Integer type,
            String content,
            Long startTime,
            Long endTime,
            Long page
    ) {
        return (long)new Random().nextInt(5000);
    }

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
    /**
     * 查询用户正在售卖的物品
     * @param userId
     * @param page
     * @return
     */
    public List<AuctionModel> selectMultiAuctionInfo(Long userId,Long page) {
        List<AuctionModel> auctionModelList = new LinkedList<>();

        for (int i = 0;i<new Random().nextInt(8);++i) {
            AuctionModel auctionModel = new AuctionModel();
            auctionModel.setAuctionId((long) i);
            auctionModel.setUserId(userId);
            auctionModel.setTopicId(1145L);
            auctionModel.setType(0);
            auctionModel.setStatus(2);
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
     * 获取用户正在上架总条数
     * @param userId
     * @return
     */
    public Long selectMultiAuctionInfoAmount(Long userId) {
        return (long)new Random().nextInt(500);
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
