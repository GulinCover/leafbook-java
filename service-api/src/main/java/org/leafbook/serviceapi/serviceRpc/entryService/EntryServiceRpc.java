package org.leafbook.serviceapi.serviceRpc.entryService;

import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.entryInfo.EntryModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.serviceapi.openfeinFallback.entryService.EntryServiceRpcFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        value = "service-entry-api",
        fallbackFactory = EntryServiceRpcFallback.class
)
public interface EntryServiceRpc {
    /**
     * 用户申请词条
     * 审核通过后方可使用
     *
     * @param userId
     * @param entryName
     * @param entryDesc
     * @return
     */
    @PostMapping("/rpc/post/create/single/entryInfo")
    int postCreateSingleEntryInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("entryName") String entryName,
            @RequestParam("entryDesc") String entryDesc,
            @RequestParam("type") String type);

    /**
     * 单一查询词条信息
     *
     * @param entryId
     * @return
     */
    @GetMapping("/rpc/get/select/single/entryInfo/{entryId}")
    EntryShowModel getSelectSingleEntryInfoRpc(
            @PathVariable("entryId") Long entryId);

    /**
     * 组查询词条信息
     *
     * @param entryIds
     * @return
     */
    @GetMapping("/rpc/get/select/multi/entryInfo")
    List<EntryShowModel> getSelectMultiEntryInfoRpc(
            @RequestParam("entryIds") List<Long> entryIds);

    /**
     * 获取全部entryInfo通过页号
     * @param page
     * @return
     */
    @GetMapping("/rpc/get/select/all/entryInfo/page/{page}")
    List<EntryShowModel> getSelectAllEntryInfoRpc(@PathVariable("page")Long page);

    /**
     * entryInfo点赞量+1
     * @param entryId
     * @return
     */
    @PostMapping("/rpc/post/update/entryInfo/star/amount")
    int postUpdateEntryInfoStarAmountRpc(Long entryId);

    /**
     * entryInfo点踩量+1
     * @param entryId
     * @return
     */
    @PostMapping("/rpc/post/update/entryInfo/tread/amount")
    int postUpdateEntryInfoTreadAmountRpc(Long entryId);

    /**
     * 获取entryInfo点赞量
     * @param entryId
     * @return
     */
    @GetMapping("/rpc/get/select/entryInfo/star/amount/{entryId}")
    Long getSelectEntryInfoStarAmountRpc(@PathVariable("entryId")Long entryId);

    /**
     * 获取entryInfo点踩量
     * @param entryId
     * @return
     */
    @GetMapping("/rpc/get/select/entryInfo/tread/amount")
    Long getSelectEntryInfoTreadAmountRpc(Long entryId);
}
