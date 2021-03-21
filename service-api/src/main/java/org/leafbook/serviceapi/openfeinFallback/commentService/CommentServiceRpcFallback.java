package org.leafbook.serviceapi.openfeinFallback.commentService;

import feign.hystrix.FallbackFactory;
import org.leafbook.serviceapi.serviceRpc.commentService.CommentServiceRpc;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceRpcFallback implements FallbackFactory<CommentServiceRpc> {
    @Override
    public CommentServiceRpc create(Throwable cause) {
        return null;
    }
}
