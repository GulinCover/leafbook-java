package org.leafbook.serviceapi.openfeinFallback.marketplaceService;

import feign.hystrix.FallbackFactory;
import org.leafbook.serviceapi.serviceRpc.marketplaceService.MarketplaceServiceRpc;
import org.springframework.stereotype.Service;

@Service
public class MarketplaceServiceRpcFallback implements FallbackFactory<MarketplaceServiceRpc> {
    @Override
    public MarketplaceServiceRpc create(Throwable cause) {
        return null;
    }
}
