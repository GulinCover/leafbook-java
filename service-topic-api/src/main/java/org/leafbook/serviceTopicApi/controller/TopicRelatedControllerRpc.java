package org.leafbook.serviceTopicApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
     * topicId查询
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/single/topicInfo/{id}")
    @GetMapping("/rpc/get/select/single/topicInfo/{id}")
    public TopicModel getSelectSingleTopicInfoRpc(@PathVariable("id")Long topicId) {
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
     * 更改著述描述
     * @param userId
     * @param topicId
     * @param topicDesc
     * @return code
     */
    @ApiOperation("/rpc/post/update/single/topicInfo/desc")
    @PostMapping("/rpc/post/update/single/topicInfo/desc")
    public int postUpdateTopicInfoDescRpc(@RequestParam("userId") Long userId,@RequestParam("topicId")Long topicId,@RequestParam("topicDesc")String topicDesc) {
        return topicRelatedServiceRpc.postUpdateTopicInfoDesc(userId, topicId, topicDesc);
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
}





