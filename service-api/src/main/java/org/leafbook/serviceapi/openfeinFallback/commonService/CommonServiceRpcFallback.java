package org.leafbook.serviceapi.openfeinFallback.commonService;

import feign.hystrix.FallbackFactory;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceRpcFallback implements FallbackFactory<CommonServiceRpc> {
    @Override
    public CommonServiceRpc create(Throwable cause) {
        return null;
    }
}
