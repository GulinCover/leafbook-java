package org.leafbook.serviceapi.openfeinFallback.marketplaceService;

import feign.hystrix.FallbackFactory;
import io.seata.common.util.StringUtils;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.api.modelApi.billInfo.BidingModel;
import org.leafbook.api.modelApi.billInfo.BillModel;
import org.leafbook.serviceapi.serviceRpc.marketplaceService.MarketplaceServiceRpc;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketplaceServiceRpcFallback implements FallbackFactory<MarketplaceServiceRpc> {
    @Override
    public MarketplaceServiceRpc create(Throwable cause) {
        System.out.println(cause);
        System.out.println("========================================");
        return new MarketplaceServiceRpc() {
            @Override
            public int postBuySingleRenameCardRpc(Long userId, String uuid, Long resId) {
                return 0;
            }

            @Override
            public int postAuctionSingleResInfoRpc(Long userId, Long auctionId, Long price, Long expireTimestamp) {
                return 0;
            }

            @Override
            public int postUseSingleRenameCardRpc(Long userId, Long auctionId, String newNickname) {
                return 0;
            }

            @Override
            public List<AuctionModel> postSelectMultiAuctionInfoRpc(Long userId, Long page) {
                return null;
            }

            @Override
            public AuctionModel postSelectSingleResInfoRpc(Long userId, Long auctionId) {
                return null;
            }

            @Override
            public int postBidingSingleAuctionInfoRpc(Long userId, Long auctionId, Long price) {
                return 0;
            }

            @Override
            public List<AuctionModel> getSelectRandomMultiAuctionInfoRpc(Integer number) {
                return null;
            }

            @Override
            public List<AuctionModel> getSelectRandomMultiLatestAuctionRpc(Integer number) {
                return null;
            }

            @Override
            public AuctionModel getSelectSingleAuctionInfoRpc(Long auctionId) {
                return null;
            }

            @Override
            public int postSelectDetectAuctionIdLegalityRpc(Long auctionId) {
                return 0;
            }

            @Override
            public int postUpdateAuctionInfoByAuctionInfoRpc(AuctionModel auctionModel) {
                if (!StringUtils.isBlank(RootContext.getXID())) {
                    try {
                        GlobalTransactionContext.reload(RootContext.getXID()).rollback();
                    } catch (TransactionException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("=================================");
                return 0;
            }

            @Override
            public List<AuctionModel> getSelectSearchMultiNicknameAuctionInfoRpc(Integer type, String content, Long startTime, Long endTime, Long page) {
                return null;
            }

            @Override
            public Long getSelectSearchMultiNicknameAuctionInfoPageRpc(Integer type, String content, Long startTime, Long endTime, Long page) {
                return null;
            }

            @Override
            public List<AuctionModel> getSelectMultiAuctionInfoByTopicIdsRpc(List<Long> topicIds) {
                return null;
            }

            @Override
            public List<BillModel> postSelectMultiBillInfoByUserIdRpc(Long userId, Long page) {
                return null;
            }

            @Override
            public Long postSelectMultiBillInfoByUserIdPageRpc(Long userId) {
                return null;
            }

            @Override
            public List<AuctionModel> postSelectMultiAuctionInfoByUserIdRpc(Long userId, Long page) {
                return null;
            }

            @Override
            public Long postSelectMultiAuctionInfoByUserIdPageRpc(Long userId) {
                return null;
            }

            @Override
            public List<BillModel> postSelectMultiPhotographedBillInfoByUserIdRpc(Long userId, Long page) {
                return null;
            }

            @Override
            public Long postSelectMultiPhotographedBillInfoByUserIdPageRpc(Long userId) {
                return null;
            }

            @Override
            public List<BidingModel> postSelectMultiBidingFailedInfoRpc(Long userId, Long page) {
                return null;
            }

            @Override
            public Long postSelectMultiBidingFailedInfoPageRpc(Long userId) {
                return null;
            }

            @Override
            public List<BidingModel> postSelectMultiBidingInfoRpc(Long userId, Long page) {
                return null;
            }

            @Override
            public Long postSelectMultiBidingInfoPageRpc(Long userId) {
                return null;
            }
        };
    }
}
