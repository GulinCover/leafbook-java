package org.leafbook.serviceapi.serviceApi.repository;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.billInfo.BidingModel;
import org.leafbook.api.respAbs.repository.bidingPage.BidingAbs;
import org.leafbook.api.respAbs.repository.bidingPage.BidingInfoResp;
import org.leafbook.serviceapi.serviceRpc.marketplaceService.MarketplaceServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@GlobalTransactional
@Service
public class BidingPageServiceApi {
    @Autowired
    private MarketplaceServiceRpc marketplaceServiceRpc;
    /**
     * 获取用户正在竞拍的信息
     * @param userId
     * @param form:page
     * @return
     */
    public BidingInfoResp postSelectMultiUserBidingInfo(Long userId, Map<String,Long> form) {
        BidingInfoResp resp = new BidingInfoResp();
        List<BidingAbs> bidingAbsList = new LinkedList<>();

        Long page = form.get("page");
        if (Objects.isNull(page) || page <= 0) page = 1L;

        List<BidingModel> bidingModelList = marketplaceServiceRpc.postSelectMultiBidingInfoRpc(userId,page);
        if (Objects.nonNull(bidingModelList) && !bidingModelList.isEmpty()) {
            for (BidingModel bidingModel:bidingModelList) {
                BidingAbs bidingAbs = new BidingAbs();
                bidingAbs.setAuctionId(bidingAbs.getAuctionId());
                bidingAbs.setBidTime(bidingModel.getCreateTime());
                bidingAbs.setPrice(bidingModel.getPrice());

                bidingAbsList.add(bidingAbs);
            }
        }

        resp.setBidingAbsList(bidingAbsList);

        Long maxPage = marketplaceServiceRpc.postSelectMultiBidingInfoPageRpc(userId);
        resp.setPage((long)Math.ceil(maxPage / 20));
        return resp;
    }
}
