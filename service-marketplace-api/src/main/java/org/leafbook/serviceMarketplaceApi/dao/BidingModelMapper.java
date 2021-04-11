package org.leafbook.serviceMarketplaceApi.dao;

import org.leafbook.api.modelApi.billInfo.BidingModel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class BidingModelMapper {
    /**
     * 获取用户竞拍失败的信息
     * @param userId
     * @param page
     * @return
     */
    public List<BidingModel> selectMultiUserBidingFailedInfo(Long userId,Long page) {
        List<BidingModel> bidingModelList = new LinkedList<>();

        for (int i = 0; i < new Random().nextInt(5) + 5; i++) {
            BidingModel bidingModel = new BidingModel();
            bidingModel.setUserId(userId);
            bidingModel.setAuctionId(312L);
            bidingModel.setPrice(312L);
            bidingModel.setBidingId(4321L);
            bidingModel.setIsBlack(1);
            bidingModel.setPublicTime(new Date().getTime());

            bidingModelList.add(bidingModel);
        }

        return bidingModelList;
    }
    /**
     * 获取用户竞拍失败的信息数量
     * @param userId
     * @return
     */
    public Long selectMultiUserBidingFailedInfoAmount(Long userId) {
        return (long)new Random().nextInt(500);
    }

    public List<BidingModel> selectMultiUserBidingInfo(Long userId,Long page) {
        List<BidingModel> bidingModelList = new LinkedList<>();

        for (int i = 0; i < new Random().nextInt(5) + 5; i++) {
            BidingModel bidingModel = new BidingModel();
            bidingModel.setUserId(userId);
            bidingModel.setAuctionId(312L);
            bidingModel.setPrice(312L);
            bidingModel.setBidingId(4321L);
            bidingModel.setIsBlack(0);
            bidingModel.setPublicTime(new Date().getTime());

            bidingModelList.add(bidingModel);
        }

        return bidingModelList;
    }

    public Long selectMultiUserBidingInfoAmount(Long userId) {
        return (long)new Random().nextInt(500);
    }

    public int insertSingleModel(BidingModel bidingModel) {
        return 1;
    }
}
