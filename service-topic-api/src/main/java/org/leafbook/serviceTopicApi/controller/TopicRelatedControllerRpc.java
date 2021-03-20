package org.leafbook.serviceTopicApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.topicInfo.DirectoryModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.serviceTopicApi.service.TopicRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Api("TopicRelatedControllerRpc")
@RestController
public class TopicRelatedControllerRpc {
    @Autowired
    private TopicRelatedServiceRpc topicRelatedServiceRpc;

    /**
     * 获取著述拥有者id
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/topic/ownerId/{topicId}")
    @GetMapping("/rpc/get/select/topic/ownerId/{topicId}")
    public Long getSelectTopicOwnerRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectTopicOwner(topicId);
    }
    /**
     * 获取所有著述管理者id
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/topic/manager/{topicId}")
    @GetMapping("/rpc/get/select/topic/manager/{topicId}")
    public List<Long> getSelectTopicManagerRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectTopicManager(topicId);
    }
    /**
     * 添加管理者
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/add/topic/manager")
    @PostMapping("/rpc/post/add/topic/manager")
    public int postAddTopicManagerRpc(@RequestParam("userId") Long userId,@RequestParam("topicId")Long topicId,@RequestParam("managerId") Long managerId) {
        return topicRelatedServiceRpc.postAddTopicManager(userId,topicId,managerId);
    }

    /**
     * 删除管理者
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/delete/topic/manager")
    @PostMapping("/rpc/post/delete/topic/manager")
    public int postDeleteTopicManagerRpc(@RequestParam("userId") Long userId,@RequestParam("topicId")Long topicId,@RequestParam("managerId") Long managerId) {
        return topicRelatedServiceRpc.postDeleteTopicManager(userId,topicId,managerId);
    }

    /**
     * 著述拥有者权限检测
     * @param userId
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/topic/owner/authority/decide")
    @PostMapping("/rpc/post/topic/owner/authority/decide")
    public int postTopicOwnerAuthorityDecideRpc(@RequestParam("userId") Long userId, @RequestParam("topicId") Long topicId) {
        return topicRelatedServiceRpc.postTopicOwnerAuthorityDecide(userId,topicId);
    }

    /**
     * 著述管理者权限检测
     * @param userId
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/topic/manager/authority/decide")
    @PostMapping("/rpc/post/topic/manager/authority/decide")
    public int postTopicManagerAuthorityDecideRpc(@RequestParam("userId") Long userId, @RequestParam("topicId") Long topicId) {
        return topicRelatedServiceRpc.postTopicManagerAuthorityDecide(userId,topicId);
    }

    /**
     * topicId查询
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/single/topicInfo/{topicId}")
    @GetMapping("/rpc/get/select/single/topicInfo/{topicId}")
    public TopicModel getSelectSingleTopicInfoRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectSingleTopicInfo(topicId);
    }

    /**
     * topicIds组查询
     * @param topicIds
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/topicInfo")
    @GetMapping("/rpc/get/select/multi/topicInfo")
    public List<TopicModel> getSelectMultiTopicInfoRpc(@RequestParam("topicIds")List<Long> topicIds) {
        return topicRelatedServiceRpc.getSelectMultiTopicInfo(topicIds);
    }

    /**
     * 创建著述
     * @param userId
     * @param topicTitle
     * @param topicDesc
     * @param entryIds
     * @return topicId
     */
    @ApiOperation("/rpc/post/create/single/topicInfo")
    @PostMapping("/rpc/post/create/single/topicInfo")
    public Long postCreateTopicInfoRpc(@RequestParam("userId") Long userId,@RequestParam("topicTitle")String topicTitle,@RequestParam("topicDesc")String topicDesc,@RequestParam("entryIds")List<Long> entryIds) {
        return topicRelatedServiceRpc.postCreateTopicInfo(userId, topicTitle, topicDesc, entryIds);
    }

    /**
     * 更换著述封面
     * @param userId
     * @param topicId
     * @param cover
     * @return
     */
    @ApiOperation("/rpc/post/update/single/topicInfo/cover")
    @PostMapping("/rpc/post/update/single/topicInfo/cover")
    public int postUpdateSingleTopicInfoForCoverRpc(@RequestParam("userId") Long userId,@RequestParam("topicId")Long topicId,@RequestParam("cover")String  cover) {
        return topicRelatedServiceRpc.postUpdateSingleTopicInfoForCover(userId, topicId, cover);
    }

    /**
     * 更改著述描述
     * @param userId
     * @param topicId
     * @param topicDesc
     * @return code
     */
    @ApiOperation("/rpc/post/update/single/topicInfo/desc")
    @PostMapping("/rpc/post/update/single/topicInfo/desc")
    public int postUpdateTopicInfoDescRpc(@RequestParam("userId")Long userId,@RequestParam("topicId")Long topicId,@RequestParam("topicDesc")String topicDesc) {
        return topicRelatedServiceRpc.postUpdateTopicInfoDesc(userId, topicId, topicDesc);
    }

    /**
     * 查询著述下默认文章目录顺序
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/directoryInfo/by/topicId/{topicId}")
    @GetMapping("/rpc/get/select/directoryInfo/by/topicId/{topicId}")
    public List<DirectoryModel> getSelectDirectoryInfoRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectDirectoryInfo(topicId);
    }

    /**
     * 修改著述默认文章顺序
     * @param userId
     * @param topicId
     * @param pageId
     * @param articleId
     * @return code
     */
    @ApiOperation("/rpc/post/update/directoryInfo/by/topicId/{topicId}")
    @GetMapping("/rpc/post/update/directoryInfo/by/topicId/{topicId}")
    public int postUpdateDirectoryInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId")Long topicId,
            @RequestParam("pageId")Long pageId,
            @RequestParam("articleId")Long articleId) {
        return topicRelatedServiceRpc.postUpdateDirectoryInfo(userId,topicId,pageId,articleId);
    }

    /**
     * 添加词条
     * @param userId
     * @param topicId
     * @param entryId
     * @return code
     */
    @ApiOperation("/rpc/post/add/single/topicInfo/entryId")
    @PostMapping("/rpc/post/add/single/topicInfo/entryId")
    public int postAddTopicInfoEntryRpc(@RequestParam("userId") Long userId,@RequestParam("topicId")Long topicId,@RequestParam("entryId")Long entryId) {
        return topicRelatedServiceRpc.postAddTopicInfoEntry(userId, topicId, entryId);
    }

    /**
     * 单查询著述下的词条，词条提交数大于50的进行展示
     * 定时任务每一小时统计一次
     * @param topicId
     * @return entryIds
     */
    @ApiOperation("/rpc/get/select/single/topicInfo/entryInfo/by/{topicId}")
    @GetMapping("/rpc/get/select/single/topicInfo/entryInfo/by/{topicId}")
    public List<Long> getSelectSingleTopicInfoForEntryIdsRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectSingleTopicInfoForEntryIds(topicId);
    }

    /**
     * 给著述点赞，著述赞数加1
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/insert/touch/topic/star")
    @PostMapping("/rpc/post/insert/touch/topic/star")
    public int postInsertTouchTopicStarRpc(@RequestParam("topicId")Long topicId) {
        return topicRelatedServiceRpc.postInsertTouchTopicStar(topicId);
    }
    /**
     * 给著述点踩，著述踩数加1
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/insert/touch/topic/tread")
    @PostMapping("/rpc/post/insert/touch/topic/tread")
    public int postInsertTouchTopicTreadRpc(@RequestParam("topicId")Long topicId) {
        return topicRelatedServiceRpc.postInsertTouchTopicTread(topicId);
    }
    /**
     * 浏览著述，浏览量加1
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/insert/touch/topic/browse")
    @PostMapping("/rpc/post/insert/touch/topic/browse")
    public int postInsertTouchTopicBrowseRpc(@RequestParam("topicId")Long topicId) {
        return topicRelatedServiceRpc.postInsertTouchTopicBrowse(topicId);
    }
}





