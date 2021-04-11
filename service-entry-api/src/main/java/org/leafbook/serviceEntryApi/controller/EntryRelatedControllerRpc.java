package org.leafbook.serviceEntryApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.entryInfo.EntryModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.serviceEntryApi.service.EntryRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Api("EntryRelatedControllerRpc")
@RestController
public class EntryRelatedControllerRpc {
    @Autowired
    private EntryRelatedServiceRpc entryRelatedServiceRpc;

    /**
     * 用户申请词条
     * 审核通过后方可使用
     * @param userId
     * @param entryName
     * @param entryDesc
     * @return
     */
    @ApiOperation("/rpc/post/create/single/entryInfo")
    @PostMapping("/rpc/post/create/single/entryInfo")
    public int postCreateSingleEntryInfoRpc(@RequestParam("userId")Long userId,@RequestParam("entryName")String entryName,@RequestParam("entryDesc")String entryDesc,@RequestParam("type")String type) {
        return entryRelatedServiceRpc.postCreateSingleEntryInfo(userId,entryName,entryDesc,type);
    }

    /**
     * 单一查询词条信息
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/get/select/single/entryInfo/{entryId}")
    @GetMapping("/rpc/get/select/single/entryInfo/{entryId}")
    public EntryShowModel getSelectSingleEntryInfoRpc(@PathVariable("entryId")Long entryId) {
        return entryRelatedServiceRpc.getSelectSingleEntryInfo(entryId);
    }

    /**
     * 组查询词条信息
     * @param entryIds
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/entryInfo")
    @GetMapping("/rpc/get/select/multi/entryInfo")
    public List<EntryShowModel> getSelectMultiEntryInfoRpc(@RequestParam("entryIds") List<Long> entryIds) {
        return entryRelatedServiceRpc.getSelectMultiEntryInfo(entryIds);
    }

    /**
     * 获取全部entryInfo通过页号
     * @param page
     * @return
     */
    @ApiOperation("/rpc/get/select/all/entryInfo/page/{page}")
    @GetMapping("/rpc/get/select/all/entryInfo/page/{page}")
    public List<EntryShowModel> getSelectAllEntryInfoRpc(@PathVariable("page")Long page) {
        return entryRelatedServiceRpc.getSelectAllEntryInfo(page);
    }

    /**
     * 获取全部entryInfo
     * @return
     */
    @ApiOperation("/rpc/get/select/all/entryInfo")
    @GetMapping("/rpc/get/select/all/entryInfo")
    public List<EntryShowModel> getSelectAllEntryInfoRpc() {
        return entryRelatedServiceRpc.getSelectAllEntryInfo();
    }

    /**
     * entryInfo点赞量+1
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/post/update/entryInfo/star/amount")
    @PostMapping("/rpc/post/update/entryInfo/star/amount")
    public int postUpdateEntryInfoStarAmountRpc(Long entryId) {
        return entryRelatedServiceRpc.postUpdateEntryInfoStarAmount(entryId);
    }

    /**
     * entryInfo点踩量+1
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/post/update/entryInfo/tread/amount")
    @PostMapping("/rpc/post/update/entryInfo/tread/amount")
    public int postUpdateEntryInfoTreadAmountRpc(Long entryId) {
        return entryRelatedServiceRpc.postUpdateEntryInfoTreadAmount(entryId);
    }

    /**
     * 获取entryInfo点赞量
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/get/select/entryInfo/star/amount/{entryId}")
    @GetMapping("/rpc/get/select/entryInfo/star/amount/{entryId}")
    public Long getSelectEntryInfoStarAmountRpc(@PathVariable("entryId")Long entryId) {
        return entryRelatedServiceRpc.getSelectEntryInfoStarAmount(entryId);
    }

    /**
     * 获取entryInfo点踩量
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/get/select/entryInfo/tread/amount")
    @GetMapping("/rpc/get/select/entryInfo/tread/amount")
    public Long getSelectEntryInfoTreadAmountRpc(Long entryId) {
        return entryRelatedServiceRpc.getSelectEntryInfoTreadAmount(entryId);
    }

    /**
     * 获取所有热论词条
     * @return
     */
    @ApiOperation("/rpc/get/select/all/hot/entryInfo")
    @GetMapping("/rpc/get/select/all/hot/entryInfo")
    public List<EntryShowModel> getSelectAllHotEntryInfoRpc() {
        return entryRelatedServiceRpc.getSelectAllHotEntryInfo();
    }


    /**
     * 随机获取3~8条词条信息
     * @return
     */
    @ApiOperation("/rpc/get/select/random/multi/entryInfo")
    @GetMapping("/rpc/get/select/random/multi/entryInfo")
    public List<EntryShowModel> getSelectRandomMultiEntryInfoRpc() {
        return entryRelatedServiceRpc.getSelectRandomMultiEntryInfo();
    }

    /**
     * 判断词条用户是否点过赞
     * @param userId
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/post/select/isLiked/with/userId")
    @PostMapping("/rpc/post/select/isLiked/with/userId")
    public Integer postSelectIsLikedWithUserIdRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("entryId")Long entryId) {
        return entryRelatedServiceRpc.postSelectIsLikedWithUserId(userId,entryId);
    }

    /**
     * 随机获取2~4条热门词条
     * @return
     */
    @ApiOperation("/rpc/get/select/random/hot/multi/entryInfo")
    @GetMapping("/rpc/get/select/random/hot/multi/entryInfo")
    public List<EntryShowModel> getSelectRandomHotMultiEntryInfoRpc() {
        return entryRelatedServiceRpc.getSelectRandomHotMultiEntryInfo();
    }

    /**
     * 获取官方词条
     * @param type: hot,official,nonofficial
     * @return
     */
    @ApiOperation("/rpc/get/select/all/entryInfo/with/type/{type}")
    @GetMapping("/rpc/get/select/all/entryInfo/with/type/{type}")
    public List<EntryShowModel> getSelectAllEntryInfoWithTypeRpc(@PathVariable("type")String type) {
        return entryRelatedServiceRpc.getSelectAllEntryInfoWithType(type);
    }

    /**
     * 单检测词条合法性
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/select/detect/legality/with/entryId")
    @PostMapping("/rpc/select/detect/legality/with/entryId")
    public int postSelectDetectLegalityWithEntryIdRpc(@RequestParam("entryId")Long entryId) {
        return entryRelatedServiceRpc.postSelectDetectLegalityWithEntryId(entryId);
    }

    /**
     * 组检测词条合法性
     * @param entryIds
     * @return
     */
    @ApiOperation("/rpc/select/detect/legality/with/entryIds")
    @PostMapping("/rpc/select/detect/legality/with/entryIds")
    public int postSelectDetectLegalityWithEntryIdsRpc(@RequestParam("entryIds")List<Long> entryIds) {
        return entryRelatedServiceRpc.postSelectDetectLegalityWithEntryIds(entryIds);
    }

    /**
     * 随机获取number条词条
     * @param type
     * @param number
     * @return
     */
    @ApiOperation("/rpc/get/select/random/multi/entryInfo/by/type")
    @GetMapping("/rpc/get/select/random/multi/entryInfo/by/type")
    public List<EntryShowModel> getSelectRandomMultiEntryInfoByTypeRpc(
            @RequestParam("type")String type,
            @RequestParam("number")Integer number) {
        return entryRelatedServiceRpc.getSelectRandomMultiEntryInfoByType(type,number);
    }

    /**
     * 搜索符合条件的词条
     * @param entryName:词条名
     * @param entryType:词条类型hot,official,nonofficial
     * @return
     */
    @ApiOperation("/rpc/get/select/search/multi/entryInfo")
    @GetMapping("/rpc/get/select/search/multi/entryInfo")
    public List<EntryShowModel> getSelectSearchMultiEntryInfoRpc(
            @RequestParam("entryName")String entryName,
            @RequestParam("entryType")String entryType
    ) {
        return entryRelatedServiceRpc.getSelectSearchMultiEntryInfoRpc(entryName,entryType);
    }

//    /**
//     * 单删词条
//     * @param entryId
//     * @return
//     */
//    @ApiOperation("/rpc/post/delete/single/entryInfo")
//    @GetMapping("/rpc/post/delete/single/entryInfo")
//    public int postDeleteSingleEntryInfoRpc(@RequestParam("entryId") Long entryId) {
//        return entryRelatedServiceRpc.postDeleteSingleEntryInfo(entryId);
//    }
//    /**
//     * 组删词条
//     * @param entryIds
//     * @return
//     */
//    @ApiOperation("/rpc/post/delete/multi/entryInfo")
//    @GetMapping("/rpc/post/delete/multi/entryInfo")
//    public int postDeleteMultiEntryInfoRpc(@RequestParam("entryIds") List<Long> entryIds) {
//        return entryRelatedServiceRpc.postDeleteMultiEntryInfo(entryIds);
//    }
}





