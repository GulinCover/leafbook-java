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
    public EntryModel getSelectSingleEntryInfoRpc(@PathVariable("entryId")Long entryId) {
        return entryRelatedServiceRpc.getSelectSingleEntryInfo(entryId);
    }

    /**
     * 组查询词条信息
     * @param entryIds
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/entryInfo")
    @GetMapping("/rpc/get/select/multi/entryInfo")
    public List<EntryModel> getSelectMultiEntryInfoRpc(@RequestParam("entryIds") List<Long> entryIds) {
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





