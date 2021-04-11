package org.leafbook.serviceCommentApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.commentInfo.CommentModel;
import org.leafbook.serviceCommentApi.service.CommentServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Api("CommentControllerRpc")
@RestController
public class CommentControllerRpc {
    @Autowired
    private CommentServiceRpc commentServiceRpc;

    /**
     * 获取所有一级评论
     *
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/comment1Info/by/topicId/{topicId}/page/{page}")
    @GetMapping("/rpc/get/select/multi/comment1Info/by/topicId/{topicId}/page/{page}")
    public List<CommentModel> getSelectMultiComment1InfoRpc(
            @PathVariable("topicId") Long topicId,
            @PathVariable("page") Long page) {
        return commentServiceRpc.getSelectMultiComment1Info(topicId,page);
    }

    /**
     * 发布一级评论
     *
     * @param userId
     * @param topicId
     * @param comment1Content
     * @return
     */
    @ApiOperation("/rpc/post/public/comment1Info")
    @PostMapping("/rpc/post/public/comment1Info")
    public int postPublicComment1InfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId") Long topicId,
            @RequestParam("comment1Content") String comment1Content) {
        return commentServiceRpc.postPublicComment1Info(userId, topicId, comment1Content);
    }


    /**
     * 添加评论词条（定时任务获取前10进行展示）
     *
     * @param userId
     * @param comment1Id
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/post/add/entryInfo/for/comment1Info")
    @PostMapping("/rpc/post/add/entryInfo/for/comment1Info")
    public int postAddEntryInfoForComment1InfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("comment1Id") Long comment1Id,
            @RequestParam("entryId") Long entryId) {
        return commentServiceRpc.postAddEntryInfoForComment1Info(userId, comment1Id, entryId);
    }

    /**
     * 获取评论词条
     *
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/get/select/entryInfo/for/comment1Info/{comment1Id}")
    @GetMapping("/rpc/get/select/entryInfo/for/comment1Info/{comment1Id}")
    public List<Long> getSelectEntryInfoForComment1InfoRpc(
            @PathVariable("comment1Id") Long comment1Id) {
        return commentServiceRpc.getSelectEntryInfoForComment1Info(comment1Id);
    }

    /**
     * 根据一级评论id获取二级评论
     *
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/comment2Info/by/topicId/{comment1Id}/page/{page}")
    @GetMapping("/rpc/get/select/multi/comment2Info/by/topicId/{comment1Id}/page/{page}")
    public List<CommentModel> getSelectMultiComment2InfoRpc(
            @PathVariable("comment1Id") Long comment1Id,
            @PathVariable("page") Long page) {
        return commentServiceRpc.getSelectMultiComment2Info(comment1Id,page);
    }

    /**
     * 发布二级评论
     *
     * @param userId
     * @param comment1Id
     * @param comment2Content
     * @return
     */
    @ApiOperation("/rpc/post/public/comment2Info")
    @PostMapping("/rpc/post/public/comment2Info")
    public int postPublicComment2InfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("comment1Id") Long comment1Id,
            @RequestParam("comment2Content") String comment2Content) {
        return commentServiceRpc.postPublicComment2Info(userId, comment1Id, comment2Content);
    }

    /**
     * 一级评论点赞
     *
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/post/touch/star/comment1Info")
    @PostMapping("/rpc/post/touch/star/comment1Info")
    public int postTouchStarComment1InfoRpc(
            @RequestParam("comment1Id") Long comment1Id) {
        return commentServiceRpc.postTouchStarComment1Info(comment1Id);
    }

    /**
     * 一级评论点踩
     *
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/post/touch/tread/comment1Info")
    @PostMapping("/rpc/post/touch/tread/comment1Info")
    public int postTouchTreadComment1InfoRpc(
            @RequestParam("comment1Id") Long comment1Id) {
        return commentServiceRpc.postTouchTreadComment1Info(comment1Id);
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
    List<CommentModel> getSelectRandomComment1InfoRpc(
            @PathVariable("topicId") Long topicId,
            Integer randomNumber) {
        return commentServiceRpc.getSelectRandomComment1Info(topicId, randomNumber);
    }


    /**
     * 获取评论词条
     *
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/entryId/by/comment1Id/{comment1Id}")
    @GetMapping("/rpc/get/select/multi/entryId/by/comment1Id/{comment1Id}")
    List<Long> getSelectMultiEntryIdsByComment1IdRpc(
            @PathVariable("comment1Id") Long comment1Id) {
        return commentServiceRpc.getSelectMultiEntryIdsByComment1Id(comment1Id);
    }


    /**
     * 获取一级普通评论
     *
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/post/select/single/comment1Info")
    @PostMapping("/rpc/post/select/single/comment1Info")
    public CommentModel postSelectSingleComment1InfoRpc(@RequestParam("comment1Id") Long comment1Id) {
        return commentServiceRpc.postSelectSingleComment1Info(comment1Id);
    }

    /**
     * 更新普通评论的点赞数
     *
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/post/update/comment1Info/touch/star/amount")
    @PostMapping("/rpc/post/update/comment1Info/touch/star/amount")
    public int postUpdateComment1InfoTouchStarAmountRpc(@RequestParam("comment1Id") Long comment1Id) {
        return commentServiceRpc.postUpdateComment1InfoTouchStarAmount(comment1Id);
    }

    /**
     * 更新普通评论的点踩数
     *
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/post/update/comment1Info/touch/tread/amount")
    @PostMapping("/rpc/post/update/comment1Info/touch/tread/amount")
    public int postUpdateComment1InfoTouchTreadAmountRpc(@RequestParam("comment1Id") Long comment1Id) {
        return commentServiceRpc.postUpdateComment1InfoTouchTreadAmount(comment1Id);
    }


    /**
     * 获取议论信息
     *
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/select/multi/talkInfo/by/topicId/{topicId}")
    @GetMapping("/rpc/select/multi/talkInfo/by/topicId/{topicId}")
    public List<CommentModel> getSelectMultiTalkInfoRpc(
            @PathVariable("topicId") Long topicId) {
        return commentServiceRpc.getSelectMultiTalkInfo(topicId);
    }

    /**
     * 获取议论一级评论
     *
     * @param talkId
     * @return
     */
    @ApiOperation("/rpc/select/multi/talkInfo/comment1Info/by/{talkId}")
    @GetMapping("/rpc/select/multi/talkInfo/comment1Info/by/{talkId}")
    public List<CommentModel> getSelectMultiTalkComment1InfoRpc(
            @PathVariable("talkId") Long talkId) {
        return commentServiceRpc.getSelectMultiTalkComment1Info(talkId);
    }

    /**
     * 获取议论二级评论
     *
     * @param talkComment1Id
     * @return
     */
    @ApiOperation("/rpc/select/multi/talkInfo/comment2Info/by/{talkComment1Id}")
    @GetMapping("/rpc/select/multi/talkInfo/comment2Info/by/{talkComment1Id}")
    public List<CommentModel> getSelectMultiTalkComment2InfoRpc(
            @PathVariable("talkComment1Id") Long talkComment1Id) {
        return commentServiceRpc.getSelectMultiTalkComment2Info(talkComment1Id);
    }

    /**
     * 发布talkInfo
     *
     * @param topicId
     * @param userId
     * @param talkTitle
     * @param talkDesc
     * @return
     */
    @ApiOperation("/rpc/post/public/talkInfo")
    @PostMapping("/rpc/post/public/talkInfo")
    public int postPublicTalkInfoRpc(
            @RequestParam("topicId") Long topicId,
            @RequestParam("userId") Long userId,
            @RequestParam("talkTitle") String talkTitle,
            @RequestParam("talkDesc") String talkDesc) {
        return commentServiceRpc.postPublicTalkInfo(topicId, userId, talkTitle, talkDesc);
    }

    /**
     * 发布talkComment1Info
     *
     * @param talkId
     * @param userId
     * @param talkCommentContent
     * @return
     */
    @ApiOperation("/rpc/post/public/talkComment1Info")
    @PostMapping("/rpc/post/public/talkComment1Info")
    public int postPublicTalkComment1InfoRpc(
            @RequestParam("topicId") Long talkId,
            @RequestParam("userId") Long userId,
            @RequestParam("talkCommentContent") String talkCommentContent) {
        return commentServiceRpc.postPublicTalkComment1Info(talkId, userId, talkCommentContent);
    }

    /**
     * 发布talkComment2Info
     *
     * @param talkComment1Id
     * @param userId
     * @param talkComment2Content
     * @return
     */
    @ApiOperation("/rpc/post/public/talkComment2Info")
    @PostMapping("/rpc/post/public/talkComment2Info")
    public int postPublicTalkComment2InfoRpc(
            @RequestParam("talkComment1Id") Long talkComment1Id,
            @RequestParam("userId") Long userId,
            @RequestParam("talkComment2Content") String talkComment2Content) {
        return commentServiceRpc.postPublicTalkComment2Info(talkComment1Id, userId, talkComment2Content);
    }

    /**
     * talkInfo点赞
     *
     * @param talkId
     * @return
     */
    @ApiOperation("/rpc/post/touch/star/for/talkInfo")
    @PostMapping("/rpc/post/touch/star/for/talkInfo")
    public int postTouchStarTalkInfoRpc(@RequestParam("talkId") Long talkId) {
        return commentServiceRpc.postTouchStarTalkInfoRpc(talkId);
    }

    /**
     * talkInfo点踩
     *
     * @param talkId
     * @return
     */
    @ApiOperation("/rpc/post/touch/tread/for/talkInfo")
    @PostMapping("/rpc/post/touch/tread/for/talkInfo")
    public int postTouchTreadTalkInfoRpc(@RequestParam("talkId") Long talkId) {
        return commentServiceRpc.postTouchTreadTalkInfoRpc(talkId);
    }

    /**
     * 给talkInfo添加词条
     *
     * @param userId
     * @param talkId
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/post/add/talkInfo/for/entryInfo")
    @PostMapping("/rpc/post/add/talkInfo/for/entryInfo")
    public int postAddTalkInfoForEntryInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("talkId") Long talkId,
            @RequestParam("entryId") Long entryId) {
        return commentServiceRpc.postAddTalkInfoForEntryInfo(userId, talkId, entryId);
    }

    /**
     * 给talkInfo的一级评论添加词条
     *
     * @param userId
     * @param talkComment1Id
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/post/add/talkComment1Info/for/entryInfo")
    @PostMapping("/rpc/post/add/talkComment1Info/for/entryInfo")
    public int postAddTalkComment1InfoForEntryInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("talkComment1Id") Long talkComment1Id,
            @RequestParam("entryId") Long entryId) {
        return commentServiceRpc.postAddTalkComment1InfoForEntryInfo(userId, talkComment1Id, entryId);
    }

    /**
     * 获取talkInfo的词条
     *
     * @param talkId
     * @return
     */
    @ApiOperation("/rpc/get/select/entryInfo/by/talkInfo/{talkId}")
    @GetMapping("/rpc/get/select/entryInfo/by/talkInfo/{talkId}")
    public List<Long> getSelectTalkInfoForEntryInfoRpc(
            @PathVariable("talkId") Long talkId) {
        return commentServiceRpc.getSelectTalkInfoForEntryInfo(talkId);
    }

    /**
     * 获取talkInfo的一级评论的词条id
     *
     * @param talkComment1Id
     * @return
     */
    @ApiOperation("/rpc/get/select/entryInfo/by/talkComment1Info/{talkComment1Id}")
    @GetMapping("/rpc/get/select/entryInfo/by/talkComment1Info/{talkComment1Id}")
    public List<Long> getSelectTalkComment1InfoForEntryInfoRpc(
            @PathVariable("talkComment1Id") Long talkComment1Id) {
        return commentServiceRpc.getSelectTalkComment1InfoForEntryInfo(talkComment1Id);
    }

    /**
     * 判断talk是否存在
     *
     * @param talkId
     * @return
     */
    @ApiOperation("/rpc/post/is/exist/for/talkInfo")
    @PostMapping("/rpc/post/is/exist/for/talkInfo")
    public int postIsExistForTalkInfoRpc(@RequestParam("talkId") Long talkId) {
        return commentServiceRpc.postIsExistForTalkInfo(talkId);
    }

    /**
     * 判断talkComment1是否存在
     *
     * @param talkComment1Id
     * @return
     */
    @ApiOperation("/rpc/post/is/exist/for/talkComment1Info")
    @PostMapping("/rpc/post/is/exist/for/talkComment1Info")
    public int postIsExistForTalkComment1InfoRpc(@RequestParam("talkComment1Id") Long talkComment1Id) {
        return commentServiceRpc.postIsExistForTalkComment1Info(talkComment1Id);
    }

    /**
     * 随机获取一篇议论
     *
     * @param topicId
     * @param randomNumber
     * @return
     */
    @GetMapping("/rpc/get/select/random/talkInfo/by/topicId/{topicId}")
    public List<CommentModel> getSelectRandomTalkInfoRpc(
            @PathVariable("topicId") Long topicId,
            @RequestParam("randomNumber") Integer randomNumber) {
        return commentServiceRpc.getSelectRandomTalkInfo(topicId, randomNumber);
    }

    /**
     * 随机获取一篇议论的评论
     *
     * @param talkId
     * @param randomNumber
     * @return
     */
    @GetMapping("/rpc/get/select/random/talkComment1Info/by/talkId/{talkId}")
    public List<CommentModel> getSelectRandomTalkComment1InfoRpc(
            @PathVariable("talkId") Long talkId,
            @RequestParam("randomNumber") Integer randomNumber) {
        return commentServiceRpc.getSelectRandomTalkComment1Info(talkId, randomNumber);
    }

    /**
     * 获取议论
     *
     * @param talkId
     * @return
     */
    @ApiOperation("/rpc/post/select/single/talkModel")
    @PostMapping("/rpc/post/select/single/talkModel")
    public CommentModel postSelectSingleTalkInfoRpc(@RequestParam("talkId") Long talkId) {
        return commentServiceRpc.postSelectSingleTalkInfoRpc(talkId);
    }

    /**
     * 获取一级议论评论
     *
     * @param talkComment1Id
     * @return
     */
    @ApiOperation("/rpc/post/select/single/talkComment1Model")
    @PostMapping("/rpc/post/select/single/talkComment1Model")
    public CommentModel postSelectSingleTalkComment1InfoRpc(@RequestParam("talkComment1Id") Long talkComment1Id) {
        return commentServiceRpc.postSelectSingleTalkComment1Info(talkComment1Id);
    }

    /**
     * 更新talk点赞数量
     *
     * @param talkId
     * @return
     */
    @ApiOperation("/rpc/post/update/talkInfo/touch/star/amount")
    @PostMapping("/rpc/post/update/talkInfo/touch/star/amount")
    public int postUpdateTalkInfoTouchStarAmountRpc(@RequestParam("talkId") Long talkId) {
        return commentServiceRpc.postUpdateTalkInfoTouchStarAmount(talkId);
    }

    /**
     * 更新talk点踩数量
     *
     * @param talkId
     * @return
     */
    @ApiOperation("/rpc/post/update/talkInfo/touch/tread/amount")
    @PostMapping("/rpc/post/update/talkInfo/touch/tread/amount")
    public int postUpdateTalkInfoTouchTreadAmountRpc(@RequestParam("talkId") Long talkId) {
        return commentServiceRpc.postUpdateTalkInfoTouchTreadAmount(talkId);

    }

    /**
     * 更新talk评论点赞数量
     *
     * @param talkComment1Id
     * @return
     */
    @ApiOperation("/rpc/post/update/talkComment1Info/touch/star/amount")
    @PostMapping("/rpc/post/update/talkComment1Info/touch/star/amount")
    public int postUpdateTalkComment1InfoTouchStarAmountRpc(@RequestParam("talkComment1Id") Long talkComment1Id) {
        return commentServiceRpc.postUpdateTalkComment1InfoTouchStarAmount(talkComment1Id);

    }

    /**
     * 更新talk评论点踩数量
     *
     * @param talkComment1Id
     * @return
     */
    @ApiOperation("/rpc/post/update/talkComment1Info/touch/tread/amount")
    @PostMapping("/rpc/post/update/talkComment1Info/touch/tread/amount")
    public int postUpdateTalkComment1InfoTouchTreadAmountRpc(@RequestParam("talkComment1Id") Long talkComment1Id) {
        return commentServiceRpc.postUpdateTalkComment1InfoTouchTreadAmount(talkComment1Id);

    }

    /**
     * 查询用户所有评论
     * @param userId
     * @param page
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/user/publiced/commentInfo")
    @GetMapping("/rpc/get/select/multi/user/publiced/commentInfo")
    public List<CommentModel> getSelectMultiUserPublicedCommentInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page) {
        return commentServiceRpc.getSelectMultiUserPublicedCommentInfo(userId,page);
    }

    /**
     * 获取用户发布所有评论数量
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/user/publiced/commentInfo/page")
    @GetMapping("/rpc/get/select/multi/user/publiced/commentInfo/page")
    public Long getSelectMultiUserPublicedCommentInfoPageRpc(
            @RequestParam("userId")Long userId) {
        return commentServiceRpc.getSelectMultiUserPublicedCommentInfoPage(userId);
    }

    /**
     * 获取一级评论点赞量
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/get/select/comment1Info/star/amount")
    @GetMapping("/rpc/get/select/comment1Info/star/amount")
    public Long getSelectComment1InfoStarAmountRpc(
            @RequestParam("comment1Id")Long comment1Id) {
        return commentServiceRpc.getSelectComment1InfoStarAmount(comment1Id);
    }
    /**
     * 获取一级议论评论点赞量
     * @param talkComment1Id
     * @return
     */
    @ApiOperation("/rpc/get/select/talkComment1Info/star/amount")
    @GetMapping("/rpc/get/select/talkComment1Info/star/amount")
    public Long getSelectTalkComment1InfoStarAmountRpc(
            @RequestParam("talkComment1Id")Long talkComment1Id) {
        return commentServiceRpc.getSelectTalkComment1InfoStarAmount(talkComment1Id);
    }

    /**
     * 获取著述最新的几条评论,不分类型
     * @param topicId
     * @param number
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/lastAll/commentInfo")
    @PostMapping("/rpc/post/select/multi/lastAll/commentInfo")
    public List<CommentModel> postSelectMultiLastAllCommentInfoRpc(
            @RequestParam("topicId")Long topicId,
            @RequestParam("number")Integer number
    ) {
        return commentServiceRpc.postSelectMultiAllCommentInfo(topicId,number);
    }

    /**
     * 获取一级评判下的二级评论数量
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/post/select/comment2Info/amount/by/comment1Id")
    @PostMapping("/rpc/post/select/comment2Info/amount/by/comment1Id")
    public Long postSelectComment2InfoAmountByComment1IdRpc(@RequestParam("comment1Id")Long comment1Id) {
        return commentServiceRpc.postSelectComment2InfoAmountByComment1Id(comment1Id);
    }

    /**
     * 判断一级评论是否点过赞
     * @param userId
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/post/select/is/star/for/comment1Info")
    @PostMapping("/rpc/post/select/is/star/for/comment1Info")
    public int postSelectIsStarForComment1InfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("comment1Id")Long comment1Id
    ) {
        return commentServiceRpc.postSelectIsStarForComment1Info(userId,comment1Id);
    }

    /**
     * 判断一级评论是否点过踩
     * @param userId
     * @param comment1Id
     * @return
     */
    @ApiOperation("/rpc/post/select/is/tread/for/comment1Info")
    @PostMapping("/rpc/post/select/is/tread/for/comment1Info")
    public int postSelectIsTreadForComment1InfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("comment1Id")Long comment1Id
    ) {
        return commentServiceRpc.postSelectIsTreadForComment1Info(userId,comment1Id);
    }

    /**
     * 获取著述下所有普通评论的总数量
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/commentInfo/amount/by/topicId")
    @PostMapping("/rpc/post/commentInfo/amount/by/topicId")
    public Long postSelectCommentInfoAmountByTopicIdRpc(
            @RequestParam("topicId") Long topicId
    ) {
        return commentServiceRpc.postSelectCommentInfoAmountByTopicId(topicId);
    }
    /**
     * 获取著述下所有普通1级评论的总数量
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/comment1Info/amount/by/topicId")
    @PostMapping("/rpc/post/comment1Info/amount/by/topicId")
    public Long postSelectComment1InfoAmountByTopicIdRpc(@RequestParam("topicId") Long topicId) {
        return commentServiceRpc.postSelectComment1InfoAmountByTopicId(topicId);
    }
}
