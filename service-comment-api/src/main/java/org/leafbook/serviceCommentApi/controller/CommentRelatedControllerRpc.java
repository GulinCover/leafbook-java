package org.leafbook.serviceCommentApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.commentInfo.Comment1Model;
import org.leafbook.api.modelApi.commentInfo.Comment2Model;
import org.leafbook.serviceCommentApi.service.CommentRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Api("CommentRelatedControllerRpc")
@RestController
public class CommentRelatedControllerRpc {
    @Autowired
    private CommentRelatedServiceRpc commentRelatedServiceRpc;

    /**
     * 获取所有一级评论
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/comment1Info/by/topicId/{topicId}")
    @GetMapping("/rpc/get/select/multi/comment1Info/by/topicId/{topicId}")
    public List<Comment1Model> getSelectMultiComment1InfoRpc(
            @PathVariable("topicId")Long topicId) {
        return commentRelatedServiceRpc.getSelectMultiComment1Info(topicId);
    }

    /**
     * 发布一级评论
     * @param userId
     * @param topicId
     * @param comment1Content
     * @return
     */
    @ApiOperation("/rpc/post/public/comment1Info")
    @PostMapping("/rpc/post/public/comment1Info")
    public int postPublicComment1InfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("topicId")Long topicId,
            @RequestParam("comment1Content")String comment1Content) {
        return commentRelatedServiceRpc.postPublicComment1Info(userId,topicId,comment1Content);
    }


    /**
     * 添加评论词条（定时任务获取前10进行展示）
     * @param userId
     * @param comment1Id
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/post/add/entryInfo/for/comment1Info")
    @PostMapping("/rpc/post/add/entryInfo/for/comment1Info")
    public int postAddEntryInfoForComment1InfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("comment1Id")Long comment1Id,
            @RequestParam("entryId")Long entryId) {
        return commentRelatedServiceRpc.postAddEntryInfoForComment1Info(userId,comment1Id,entryId);
    }

    /**
     * 获取评论词条
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/get/select/entryInfo/for/comment1Info/{comment1Id}")
    @GetMapping("/rpc/get/select/entryInfo/for/comment1Info/{comment1Id}")
    public List<Long> getSelectEntryInfoForComment1InfoRpc(
            @PathVariable("comment1Id")Long comment1Id) {
        return commentRelatedServiceRpc.getSelectEntryInfoForComment1Info(comment1Id);
    }

    /**
     * 根据一级评论id获取所有二级评论
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/comment2Info/by/topicId/{comment1Id}")
    @GetMapping("/rpc/get/select/multi/comment2Info/by/topicId/{comment1Id}")
    public List<Comment2Model> getSelectMultiComment2InfoRpc(
            @PathVariable("comment1Id")Long comment1Id) {
        return commentRelatedServiceRpc.getSelectMultiComment2Info(comment1Id);
    }

    /**
     * 发布二级评论
     * @param userId
     * @param comment1Id
     * @param comment2Content
     * @return
     */
    @ApiOperation("/rpc/post/public/comment2Info")
    @PostMapping("/rpc/post/public/comment2Info")
    public int postPublicComment2InfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("comment1Id")Long comment1Id,
            @RequestParam("comment2Content")String comment2Content) {
        return commentRelatedServiceRpc.postPublicComment2Info(userId,comment1Id,comment2Content);
    }

    /**
     * 一级评论点赞
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/post/touch/star/comment1Info")
    @PostMapping("/rpc/post/touch/star/comment1Info")
    public int postTouchStarComment1InfoRpc(
            @RequestParam("comment1Id")Long comment1Id) {
        return commentRelatedServiceRpc. postTouchStarComment1Info(comment1Id);
    }

    /**
     * 一级评论点踩
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/post/touch/tread/comment1Info")
    @PostMapping("/rpc/post/touch/tread/comment1Info")
    public int postTouchTreadComment1InfoRpc(
            @RequestParam("comment1Id")Long comment1Id) {
        return commentRelatedServiceRpc. postTouchTreadComment1Info(comment1Id);
    }

    /**
     * 随机获取多篇评论
     *
     * @param topicId
     * @param randomNumber
     * @return
     */
    @ApiOperation("/rpc/get/select/random/comment1Info/by/topicId/{topicId}")
    @GetMapping("/rpc/get/select/random/comment1Info/by/topicId/{topicId}")
    List<Comment1Model> getSelectRandomComment1InfoRpc(
            @PathVariable("topicId") Long topicId,
            Integer randomNumber) {
        return commentRelatedServiceRpc.getSelectRandomComment1Info(topicId,randomNumber);
    }


    /**
     * 获取评论词条
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/entryId/by/comment1Id/{comment1Id}")
    @GetMapping("/rpc/get/select/multi/entryId/by/comment1Id/{comment1Id}")
    List<Long> getSelectMultiEntryIdsByComment1IdRpc(
            @PathVariable("comment1Id")Long comment1Id) {
        return commentRelatedServiceRpc.getSelectMultiEntryIdsByComment1Id(comment1Id);
    }


    /**
     * 获取一级普通评论
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/post/select/single/comment1Info")
    @PostMapping("/rpc/post/select/single/comment1Info")
    public Comment1Model postSelectSingleComment1InfoRpc(@RequestParam("comment1Id") Long comment1Id) {
        return commentRelatedServiceRpc.postSelectSingleComment1Info(comment1Id);
    }

}
