package org.leafbook.serviceapi.openfeinFallback.topicService;

import feign.hystrix.FallbackFactory;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceRpcFallback implements FallbackFactory<TopicServiceRpc> {

    @Override
    public TopicServiceRpc create(Throwable cause) {
        return null;
    }
}
