package org.leafbook.serviceapi.serviceRpc.entryService;

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
     *
     * @param page
     * @return
     */
    @GetMapping("/rpc/get/select/all/entryInfo/page/{page}")
    List<EntryShowModel> getSelectAllEntryInfoRpc(@PathVariable("page") Long page);

    /**
     * 获取全部entryInfo
     *
     * @return
     */
    @GetMapping("/rpc/get/select/all/entryInfo")
    List<EntryShowModel> getSelectAllEntryInfoRpc();

    /**
     * entryInfo点赞量+1
     *
     * @param entryId
     * @return
     */
    @PostMapping("/rpc/post/update/entryInfo/star/amount")
    int postUpdateEntryInfoStarAmountRpc(Long entryId);

    /**
     * entryInfo点踩量+1
     *
     * @param entryId
     * @return
     */
    @PostMapping("/rpc/post/update/entryInfo/tread/amount")
    int postUpdateEntryInfoTreadAmountRpc(Long entryId);

    /**
     * 获取entryInfo点赞量
     *
     * @param entryId
     * @return
     */
    @GetMapping("/rpc/get/select/entryInfo/star/amount/{entryId}")
    Long getSelectEntryInfoStarAmountRpc(@PathVariable("entryId") Long entryId);

    /**
     * 获取entryInfo点踩量
     *
     * @param entryId
     * @return
     */
    @GetMapping("/rpc/get/select/entryInfo/tread/amount")
    Long getSelectEntryInfoTreadAmountRpc(Long entryId);

    /**
     * 获取所有热论词条
     *
     * @return
     */
    @GetMapping("/rpc/get/select/all/hot/entryInfo")
    List<EntryShowModel> getSelectAllHotEntryInfoRpc();

    /**
     * 随机获取3~8条词条信息
     *
     * @return
     */
    @GetMapping("/rpc/get/select/random/multi/entryInfo")
    List<EntryShowModel> getSelectRandomMultiEntryInfoRpc();

    /**
     * 判断词条用户是否点过赞
     *
     * @param userId
     * @param entryId
     * @return
     */
    @PostMapping("/rpc/post/select/isLiked/with/userId")
    Integer postSelectIsLikedWithUserIdRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("entryId") Long entryId);

    /**
     * 随机获取2~4条热门词条
     *
     * @return
     */
    @GetMapping("/rpc/get/select/random/hot/multi/entryInfo")
    List<EntryShowModel> getSelectRandomHotMultiEntryInfoRpc();

    /**
     * 获取官方词条
     *
     * @param type: hot,official,nonofficial
     * @return
     */
    @GetMapping("/rpc/get/select/all/entryInfo/with/type/{type}")
    List<EntryShowModel> getSelectAllEntryInfoWithTypeRpc(@PathVariable("type") String type);

    /**
     * 单检测词条合法性
     *
     * @param entryId
     * @return
     */
    @PostMapping("/rpc/select/detect/legality/with/entryId")
    int postSelectDetectLegalityWithEntryIdRpc(@RequestParam("entryId") Long entryId);

    /**
     * 组检测词条合法性
     *
     * @param entryIds
     * @return
     */
    @PostMapping("/rpc/select/detect/legality/with/entryIds")
    int postSelectDetectLegalityWithEntryIdsRpc(@RequestParam("entryIds") List<Long> entryIds);

    /**
     * 记录点赞信息
     *
     * @param entryId
     * @return
     */
    @PostMapping("/rpc/post/insert/touch/star")
    int postInsertTouchStarRpc(@RequestParam("entryId") Long entryId);

    /**
     * 随机获取number条词条
     *
     * @param type
     * @param number
     * @return
     */
    @GetMapping("/rpc/get/select/random/multi/entryInfo/by/type")
    List<EntryShowModel> getSelectRandomMultiEntryInfoByTypeRpc(
            @RequestParam("type") String type,
            @RequestParam("number") Integer number);

    /**
     * 搜索符合条件的词条
     *
     * @param entryName:词条名
     * @param entryType:词条类型hot,official,nonofficial
     * @return
     */
    @GetMapping("/rpc/get/select/search/multi/entryInfo")
    List<EntryShowModel> getSelectSearchMultiEntryInfoRpc(
            @RequestParam("entryName") String entryName,
            @RequestParam("entryType") String entryType
    );
}
