package org.leafbook.serviceapi.serviceApi;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@GlobalTransactional
@Service
public class PolicyPageServiceApi {
    @Autowired
    private CommonServiceRpc commonServiceRpc;
    /**
     * 获取站点协议
     * @return
     */
    public String getSelectPolicyInfo() {
        return commonServiceRpc.getSelectPolicyRpc();
    }
}
