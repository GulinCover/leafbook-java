package org.leafbook.serviceMarketplaceApi.daoImpl;

import org.leafbook.api.annotation.RemainingProblem;
import org.leafbook.api.annotation.ToBeOptimized;
import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.serviceMarketplaceApi.dao.AuctionModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AuctionModelMapperImpl {
    @Autowired
    private AuctionModelMapper auctionModelMapper;

    /**
     * 根据topicIds查询拍卖物品
     * @param topicIds
     * @return
     */
    public List<AuctionModel> selectMultiAuctionByTopicIds(List<Long> topicIds) {
        return auctionModelMapper.selectMultiAuctionByTopicIds(topicIds);
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
    @ToBeOptimized
    public List<AuctionModel> selectSearchMultiNickname(
            Integer type,
            String content,
            Long startTime,
            Long endTime,
            Long page
    ) {
        Long end = page * 20;
        Long start = end - 20;
        return auctionModelMapper.selectSearchMultiAuctionForNickname(content,startTime,endTime,start,end);
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
        return auctionModelMapper.selectSearchMultiAuctionAmountForNickname(content,startTime,endTime);
    }

    /**
     * 检测auctionId合法性
     * @param auctionId
     * @return
     */
    public int selectAuctionInfoIsExist(Long auctionId) {
        return auctionModelMapper.selectDetectIsExistSingleAuctionByAuctionId(auctionId);
    }

    /**
     * 获取拍卖物品信息
     * @param auctionId
     * @return
     */
    public AuctionModel selectSingleAuctionInfoByAuctionId(Long auctionId) {
        return auctionModelMapper.selectSingleAuctionByAuctionId(auctionId);
    }

    /**
     * 随机获取number条拍卖物品信息
     * @param number
     * @return
     */
    @ToBeOptimized
    public List<AuctionModel> selectRandomNumberAuctionInfo(Integer number) {
        return auctionModelMapper.selectRandomMultiAuction(number);
    }
    /**
     * 获取最新number条拍卖品信息
     * @return
     */
    @ToBeOptimized
    public List<AuctionModel> selectNumberLatestAuctionInfo(Integer number) {
        return auctionModelMapper.selectLatestMultiAuction(number);
    }

    /**
     * 组查询
     * @param auctionIds
     * @return
     */
    public List<AuctionModel> selectMultiByAuctionIds(List<Long> auctionIds) {
        return auctionModelMapper.selectMultiAuctionByAuctionIds(auctionIds);
    }

    @RemainingProblem
    public List<AuctionModel> selectMultiAuctionInfo(Long userId) {
        return auctionModelMapper.selectAllAuctionByUserId(userId);
    }
    /**
     * 查询用户正在售卖的物品
     * @param userId
     * @param page
     * @return
     */
    public List<AuctionModel> selectMultiAuctionInfo(Long userId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return auctionModelMapper.selectMultiAuctionByUserId(userId,start,end);
    }
    /**
     * 获取用户正在上架总条数
     * @param userId
     * @return
     */
    public Long selectMultiAuctionInfoAmount(Long userId) {
        return auctionModelMapper.selectMultiAuctionAmountByUserId(userId);
    }

    public AuctionModel selectVerifySingleAuctionInfo(Long userId,Long auctionId) {
        return auctionModelMapper.selectSingleAuctionByUserIdAndAuctionId(userId,auctionId);

    }

    @RemainingProblem
    public int update(AuctionModel auctionModel) {
        return 1;
    }

    public int updateMaxPrice(Long auctionId,Long userId,String userUuid,Long maxPrice) {
        AuctionModel auctionModel = auctionModelMapper.selectSingleAuctionByAuctionId(auctionId);
        if (Objects.isNull(auctionModel)) return 0;
        auctionModel.setCurrentPrice(maxPrice);
        auctionModel.setCurrentPriceUserId(userId);
        auctionModel.setCurrentPriceUserUuid(userUuid);
        return auctionModelMapper.updateById(auctionModel);
    }
    /**
     * 更改买拍信息当前最高价
     * @param auctionModel
     * @return
     */
    public int updateAuctionInfoByAuctionInfo(AuctionModel auctionModel) {
        auctionModel.setUpdateTime(new Date().getTime());
        return auctionModelMapper.updateByModel(auctionModel);
    }

    /**
     * 生成上架拍卖信息
     * @param auctionModel
     * @return
     */
    public Long insertSingleForAuctionId(AuctionModel auctionModel) {
        Long auctionId = IdGeneratorTools.nextId();
        auctionModel.setAuctionId(auctionId);
        auctionModel.setIsBlack(0);
        auctionModel.setVersion(1);
        int ret = auctionModelMapper.insert(auctionModel);
        return ret == 0 ? 0 : auctionId;
    }
}
