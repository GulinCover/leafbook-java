package org.leafbook.serviceMarketplaceApi.daoImpl;

import org.leafbook.api.modelApi.billInfo.BidingModel;
import org.leafbook.serviceMarketplaceApi.dao.BidingModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class BidingModelMapperImpl {
    @Autowired
    private BidingModelMapper bidingModelMapper;
    /**
     * 获取用户竞拍失败的信息
     * @param userId
     * @param page
     * @return
     */
    public List<BidingModel> selectMultiUserBidingFailedInfo(Long userId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return bidingModelMapper.selectMultiBidingFailedByUserId(userId,start,end);
    }
    /**
     * 获取用户竞拍失败的信息数量
     * @param userId
     * @return
     */
    public Long selectMultiUserBidingFailedInfoAmount(Long userId) {
        return bidingModelMapper.selectMultiBidingFailedAmountByUserId(userId);
    }
    /**
     * 获取用户正在竞拍的信息
     * @param userId
     * @param page
     * @return
     */
    public List<BidingModel> selectMultiUserBidingInfo(Long userId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return bidingModelMapper.selectMultiBidingByUserId(userId,start,end);
    }
    /**
     * 获取用户正在竞拍的信息数量
     * @param userId
     * @return
     */
    public Long selectMultiUserBidingInfoAmount(Long userId) {
        return bidingModelMapper.selectMultiBidingAmountByUserId(userId);
    }

    /**
     * 获取用户竞拍成功的信息
     * @param userId
     * @param page
     * @return
     */
    public List<BidingModel> selectMultiUserBidingSuccessInfo(Long userId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return bidingModelMapper.selectMultiBidingSuccessByUserId(userId,start,end);
    }
    /**
     * 获取用户竞拍成功的信息数量
     * @param userId
     * @return
     */
    public Long selectMultiUserBidingSuccessInfoAmount(Long userId) {
        return bidingModelMapper.selectMultiBidingSuccessAmountByUserId(userId);
    }

    public int insertSingleModel(BidingModel bidingModel) {
        bidingModel.setVersion(1);
        bidingModel.setStatus(0);
        bidingModel.setIsBlack(0);
        bidingModel.setBidingId(IdGeneratorTools.nextId());
        Long time = new Date().getTime();
        bidingModel.setCreateTime(time);
        bidingModel.setUpdateTime(time);
        return bidingModelMapper.insert(bidingModel);
    }
}
