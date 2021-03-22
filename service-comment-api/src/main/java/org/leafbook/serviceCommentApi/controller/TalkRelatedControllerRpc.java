package org.leafbook.serviceCommentApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.talkInfo.TalkModel;
import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment1Model;
import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment2Model;
import org.leafbook.serviceCommentApi.service.TalkRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Api("TalkRelatedControllerRpc")
@RestController
public class TalkRelatedControllerRpc {
    @Autowired
    private TalkRelatedServiceRpc talkRelatedServiceRpc;

    /**
     * 获取议论信息
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/select/multi/talkInfo/by/topicId/{topicId}")
    @GetMapping("/rpc/select/multi/talkInfo/by/topicId/{topicId}")
    public List<TalkModel> getSelectMultiTalkInfoRpc(
            @PathVariable("topicId")Long topicId) {
        return talkRelatedServiceRpc.getSelectMultiTalkInfo(topicId);
    }

    /**
     * 获取议论一级评论
     * @param talkId
     * @return
     */
    @ApiOperation("/rpc/select/multi/talkInfo/comment1Info/by/{talkId}")
    @GetMapping("/rpc/select/multi/talkInfo/comment1Info/by/{talkId}")
    public List<TalkComment1Model> getSelectMultiTalkComment1InfoRpc(
            @PathVariable("talkId")Long talkId) {
        return talkRelatedServiceRpc.getSelectMultiTalkComment1Info(talkId);
    }

    /**
     * 获取议论二级评论
     * @param talkComment1Id
     * @return
     */
    @ApiOperation("/rpc/select/multi/talkInfo/comment2Info/by/{talkComment1Id}")
    @GetMapping("/rpc/select/multi/talkInfo/comment2Info/by/{talkComment1Id}")
    public List<TalkComment2Model> getSelectMultiTalkComment2InfoRpc(
            @PathVariable("talkComment1Id")Long talkComment1Id) {
        return talkRelatedServiceRpc.getSelectMultiTalkComment2Info(talkComment1Id);
    }

    /**
     * 发布talkInfo
     * @param topicId
     * @param userId
     * @param talkTitle
     * @param talkDesc
     * @return
     */
    @ApiOperation("/rpc/post/public/talkInfo")
    @PostMapping("/rpc/post/public/talkInfo")
    public int postPublicTalkInfoRpc(
            @RequestParam("topicId")Long topicId,
            @RequestParam("userId")Long userId,
            @RequestParam("talkTitle")String talkTitle,
            @RequestParam("talkDesc")String talkDesc) {
        return talkRelatedServiceRpc.postPublicTalkInfo(topicId,userId,talkTitle,talkDesc);
    }

    /**
     * 发布talkComment1Info
     * @param talkId
     * @param userId
     * @param talkCommentContent
     * @return
     */
    @ApiOperation("/rpc/post/public/talkComment1Info")
    @PostMapping("/rpc/post/public/talkComment1Info")
    public int postPublicTalkComment1InfoRpc(
            @RequestParam("topicId")Long talkId,
            @RequestParam("userId")Long userId,
            @RequestParam("talkCommentContent")String talkCommentContent) {
        return talkRelatedServiceRpc.postPublicTalkComment1Info(talkId,userId,talkCommentContent);
    }

    /**
     * 发布talkComment2Info
     * @param talkComment1Id
     * @param userId
     * @param talkComment2Content
     * @return
     */
    @ApiOperation("/rpc/post/public/talkComment2Info")
    @PostMapping("/rpc/post/public/talkComment2Info")
    public int postPublicTalkComment2InfoRpc(
            @RequestParam("talkComment1Id")Long talkComment1Id,
            @RequestParam("userId")Long userId,
            @RequestParam("talkComment2Content")String talkComment2Content) {
        return talkRelatedServiceRpc.postPublicTalkComment2Info(talkComment1Id,userId,talkComment2Content);
    }

    /**
     * talkInfo点赞
     * @param talkId
     * @return
     */
    @ApiOperation("/rpc/post/touch/star/for/talkInfo")
    @PostMapping("/rpc/post/touch/star/for/talkInfo")
    public int postTouchStarTalkInfoRpc(@RequestParam("talkId")Long talkId) {
        return talkRelatedServiceRpc.postTouchStarTalkInfoRpc(talkId);
    }

    /**
     * talkInfo点踩
     * @param talkId
     * @return
     */
    @ApiOperation("/rpc/post/touch/tread/for/talkInfo")
    @PostMapping("/rpc/post/touch/tread/for/talkInfo")
    public int postTouchTreadTalkInfoRpc(@RequestParam("talkId")Long talkId) {
        return talkRelatedServiceRpc.postTouchTreadTalkInfoRpc(talkId);
    }

    /**
     * 给talkInfo添加词条
     * @param userId
     * @param talkId
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/post/add/talkInfo/for/entryInfo")
    @PostMapping("/rpc/post/add/talkInfo/for/entryInfo")
    public int postAddTalkInfoForEntryInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("talkId")Long talkId,
            @RequestParam("entryId")Long entryId) {
        return talkRelatedServiceRpc.postAddTalkInfoForEntryInfo(userId,talkId,entryId);
    }

    /**
     * 给talkInfo的一级评论添加词条
     * @param userId
     * @param talkComment1Id
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/post/add/talkComment1Info/for/entryInfo")
    @PostMapping("/rpc/post/add/talkComment1Info/for/entryInfo")
    public int postAddTalkComment1InfoForEntryInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("talkComment1Id")Long talkComment1Id,
            @RequestParam("entryId")Long entryId) {
        return talkRelatedServiceRpc.postAddTalkComment1InfoForEntryInfo(userId,talkComment1Id,entryId);
    }

    /**
     * 获取talkInfo的词条
     * @param talkId
     * @return
     */
    @ApiOperation("/rpc/get/select/entryInfo/by/talkInfo/{talkId}")
    @GetMapping("/rpc/get/select/entryInfo/by/talkInfo/{talkId}")
    public List<Long> getSelectTalkInfoForEntryInfoRpc(
            @PathVariable("talkId")Long talkId) {
        return talkRelatedServiceRpc.getSelectTalkInfoForEntryInfo(talkId);
    }

    /**
     * 获取talkInfo的一级评论的词条id
     * @param talkComment1Id
     * @return
     */
    @ApiOperation("/rpc/get/select/entryInfo/by/talkComment1Info/{talkComment1Id}")
    @GetMapping("/rpc/get/select/entryInfo/by/talkComment1Info/{talkComment1Id}")
    public List<Long> getSelectTalkComment1InfoForEntryInfoRpc(
            @PathVariable("talkComment1Id")Long talkComment1Id) {
        return talkRelatedServiceRpc.getSelectTalkComment1InfoForEntryInfo(talkComment1Id);
    }

    /**
     * 判断talk是否存在
     * @param talkId
     * @return
     */
    @ApiOperation("/rpc/post/is/exist/for/talkInfo")
    @PostMapping("/rpc/post/is/exist/for/talkInfo")
    public int postIsExistForTalkInfoRpc(@RequestParam("talkId")Long talkId) {
        return talkRelatedServiceRpc.postIsExistForTalkInfo(talkId);
    }
    /**
     * 判断talkComment1是否存在
     * @param talkComment1Id
     * @return
     */
    @ApiOperation("/rpc/post/is/exist/for/talkComment1Info")
    @PostMapping("/rpc/post/is/exist/for/talkComment1Info")
    public int postIsExistForTalkComment1InfoRpc(@RequestParam("talkComment1Id")Long talkComment1Id) {
        return talkRelatedServiceRpc.postIsExistForTalkComment1Info(talkComment1Id);
    }

    /**
     * 随机获取一篇议论
     * @param topicId
     * @param randomNumber
     * @return
     */
    @GetMapping("/rpc/get/select/random/talkInfo/by/topicId/{topicId}")
    public List<TalkModel> getSelectRandomTalkInfoRpc(
            @PathVariable("topicId")Long topicId,
            @RequestParam("randomNumber")Integer randomNumber) {
        return talkRelatedServiceRpc.getSelectRandomTalkInfo(topicId,randomNumber);
    }

    /**
     * 随机获取一篇议论的评论
     * @param talkId
     * @param randomNumber
     * @return
     */
    @GetMapping("/rpc/get/select/random/talkComment1Info/by/talkId/{talkId}")
    public List<TalkComment1Model> getSelectRandomTalkComment1InfoRpc(
            @PathVariable("talkId")Long talkId,
            @RequestParam("randomNumber")Integer randomNumber) {
        return talkRelatedServiceRpc.getSelectRandomTalkComment1Info(talkId,randomNumber);
    }

}
