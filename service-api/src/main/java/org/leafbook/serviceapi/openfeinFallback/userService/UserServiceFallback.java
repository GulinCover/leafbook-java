package org.leafbook.serviceapi.openfeinFallback.userService;

import feign.hystrix.FallbackFactory;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.stereotype.Service;

@Service
public class UserServiceFallback implements FallbackFactory<UserServiceRpc> {
    @Override
    public UserServiceRpc create(Throwable cause) {
        return null;
    }
}
