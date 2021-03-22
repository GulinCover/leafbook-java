package org.leafbook.serviceapi.serviceRpc.commonService;

import org.leafbook.serviceapi.openfeinFallback.commonService.CommonServiceRpcFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        value = "service-common-api",
        fallbackFactory = CommonServiceRpcFallback.class
)
public interface CommonServiceRpc {
    //点踩
    @PostMapping("/rpc/post/insert/touch/tread")
    int postInsertTouchTreadRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("objectId") Long objectId,
            @RequestParam("type") String type);

    /**
     * 获取站点协议
     *
     * @return
     */
    @GetMapping("/rpc/get/select/policy")
    String getSelectPolicyRpc();

    /**
     * 点赞
     *
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    @PostMapping("/rpc/post/insert/touch/star")
    int postInsertTouchStarRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("objectId") Long objectId,
            @RequestParam("type") String type);

    /**
     * 查看是否点过赞
     *
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    @PostMapping("/rpc/post/select/touched/star")
    int postSelectTouchedStarRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("objectId") Long objectId,
            @RequestParam("type") String type);

    /**
     * 查看是否点过踩
     *
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    @PostMapping("/rpc/post/select/touched/tread")
    int postSelectTouchedTreadRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("objectId") Long objectId,
            @RequestParam("type") String type);


    /**
     * 发送验证码
     *
     * @param email
     * @return
     */
    @PostMapping("/rpc/post/send/code/by/email")
    int postSendCodeRpc(@RequestParam("email") String email);

    /**
     * 获取验证码
     *
     * @param email
     * @return
     */
    @PostMapping("/rpc/post/acquire/code/by/email")
    String postAcquireCodeRpc(@RequestParam("email") String email);
}
