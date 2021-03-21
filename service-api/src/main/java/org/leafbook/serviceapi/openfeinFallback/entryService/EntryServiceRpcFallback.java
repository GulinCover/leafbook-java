package org.leafbook.serviceapi.openfeinFallback.entryService;

import feign.hystrix.FallbackFactory;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.springframework.stereotype.Service;

@Service
public class EntryServiceRpcFallback implements FallbackFactory<EntryServiceRpc> {
    @Override
    public EntryServiceRpc create(Throwable cause) {
        return null;
    }
}
