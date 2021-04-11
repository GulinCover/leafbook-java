package org.leafbook.serviceapi.serviceApi.repository;

import org.leafbook.api.modelApi.billInfo.BidingModel;
import org.leafbook.api.respAbs.repository.failedPage.FailedAbs;
import org.leafbook.api.respAbs.repository.failedPage.FailedInfoResp;
import org.leafbook.serviceapi.serviceRpc.marketplaceService.MarketplaceServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class FailedPageServiceApi {
    @Autowired
    private MarketplaceServiceRpc marketplaceServiceRpc;
    /**
     * 获取用户竞拍失败的账单
     * @param userId
     * @param form:page
     * @return
     */
    public FailedInfoResp postSelectMultiUserBidingFailedInfo(Long userId, Map<String,Long> form) {
        Long page = form.get("page");
        if (Objects.isNull(page) || page <= 0) page = 1L;

        FailedInfoResp resp = new FailedInfoResp();
        List<FailedAbs> failedAbsList = new LinkedList<>();

        List<BidingModel> bidingModelList = marketplaceServiceRpc.postSelectMultiBidingFailedInfoRpc(userId,page);
        if (Objects.nonNull(bidingModelList) && !bidingModelList.isEmpty()) {
            for (BidingModel bidingModel:bidingModelList) {
                FailedAbs failedAbs = new FailedAbs();
                failedAbs.setAuctionId(bidingModel.getAuctionId());
                failedAbs.setBidTime(bidingModel.getPublicTime());
                failedAbs.setPrice(bidingModel.getPrice());

                failedAbsList.add(failedAbs);
            }
        }

        resp.setFailedAbsList(failedAbsList);

        Long maxPage = marketplaceServiceRpc.postSelectMultiBidingFailedInfoPageRpc(userId);
        resp.setPage((long)Math.ceil(maxPage / 20));
        return resp;
    }
}
