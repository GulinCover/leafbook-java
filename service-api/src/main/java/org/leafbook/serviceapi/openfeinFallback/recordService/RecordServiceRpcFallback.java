package org.leafbook.serviceapi.openfeinFallback.recordService;

import feign.hystrix.FallbackFactory;
import org.leafbook.serviceapi.serviceRpc.recordService.RecordServiceRpc;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceRpcFallback implements FallbackFactory<RecordServiceRpc> {
    @Override
    public RecordServiceRpc create(Throwable cause) {
        return null;
    }
}
