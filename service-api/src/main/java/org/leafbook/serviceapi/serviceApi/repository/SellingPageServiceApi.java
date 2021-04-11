package org.leafbook.serviceapi.serviceApi.repository;

import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.respAbs.repository.sellingPage.SellingAbs;
import org.leafbook.api.respAbs.repository.sellingPage.SellingInfoResp;
import org.leafbook.serviceapi.serviceRpc.marketplaceService.MarketplaceServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SellingPageServiceApi {
    @Autowired
    private MarketplaceServiceRpc marketplaceServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    /**
     * 获取用户正在售卖的物品
     * @param userId
     * @param form:page
     * @return
     */
    public SellingInfoResp postSelectMultiUserSellingInfo(Long userId, Map<String,Long> form) {
        SellingInfoResp resp = new SellingInfoResp();
        Long page = form.get("page");
        if (page <= 0) page = 1L;

        List<SellingAbs> sellingAbsList = new LinkedList<>();

        List<AuctionModel> auctionModelList = marketplaceServiceRpc.postSelectMultiAuctionInfoByUserIdRpc(userId,page);
        if (Objects.nonNull(auctionModelList) && !auctionModelList.isEmpty()) {
            for (AuctionModel auctionModel:auctionModelList) {
                SellingAbs sellingAbs = new SellingAbs();
                sellingAbs.setAuctionId(auctionModel.getAuctionId());
                sellingAbs.setCurrentPrice(auctionModel.getCurrentPrice());
                sellingAbs.setStartPrice(auctionModel.getStartPrice());
                sellingAbs.setType(auctionModel.getType());
                sellingAbs.setPublicTime(auctionModel.getPublicTimestamp());
                sellingAbs.setExpiredTime(auctionModel.getExpireTimestamp());

                switch (auctionModel.getType()) {
                    case 0:
                        sellingAbs.setTopicId(auctionModel.getTopicId());
                        TopicModel topicInfo = topicServiceRpc.getSelectSingleTopicInfoRpc(auctionModel.getTopicId());
                        if (Objects.nonNull(topicInfo)) {
                            sellingAbs.setTopicTitle(topicInfo.getTopicTitle());
                            sellingAbs.setTopicDesc(topicInfo.getTopicDesc());
                        }
                        break;
                    case 1:
                        sellingAbs.setNickname(auctionModel.getNickname());
                        break;
                    default:
                        break;
                }

                sellingAbsList.add(sellingAbs);
            }
        }

        Long maxPage = marketplaceServiceRpc.postSelectMultiAuctionInfoByUserIdPageRpc(userId);
        resp.setPage((long)Math.ceil(maxPage / 20));

        resp.setSellingAbsList(sellingAbsList);
        return resp;
    }
}
