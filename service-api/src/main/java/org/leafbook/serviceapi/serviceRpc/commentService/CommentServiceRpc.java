package org.leafbook.serviceapi.serviceRpc.commentService;

import org.leafbook.api.modelApi.commentInfo.CommentModel;
import org.leafbook.serviceapi.openfeinFallback.commentService.CommentServiceRpcFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(
        value = "service-comment-api",
        fallbackFactory = CommentServiceRpcFallback.class
)
public interface CommentServiceRpc {
    /**
     * 获取所有一级评论
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/get/select/multi/comment1Info/by/topicId/{topicId}/page/{page}")
    List<CommentModel> getSelectMultiComment1InfoRpc(
            @PathVariable("topicId") Long topicId,
            @PathVariable("page") Long page);

    /**
     * 发布一级评论
     *
     * @param userId
     * @param topicId
     * @param comment1Content
     * @return
     */
    @PostMapping("/rpc/post/public/comment1Info")
    int postPublicComment1InfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId") Long topicId,
            @RequestParam("comment1Content") String comment1Content);


    /**
     * 添加评论词条（定时任务获取前10进行展示）
     *
     * @param userId
     * @param comment1Id
     * @param entryId
     * @return
     */
    @PostMapping("/rpc/post/add/entryInfo/for/comment1Info")
    int postAddEntryInfoForComment1InfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("comment1Id") Long comment1Id,
            @RequestParam("entryId") Long entryId);

    /**
     * 获取评论词条
     *
     * @param comment1Id
     * @return
     */
    @GetMapping("/rpc/get/select/entryInfo/for/comment1Info/{comment1Id}")
    List<Long> getSelectEntryInfoForComment1InfoRpc(
            @PathVariable("comment1Id") Long comment1Id);

    /**
     * 根据一级评论id获取二级评论
     *
     * @param comment1Id
     * @return
     */
    @GetMapping("/rpc/get/select/multi/comment2Info/by/topicId/{comment1Id}/page/{page}")
    List<CommentModel> getSelectMultiComment2InfoRpc(
            @PathVariable("comment1Id") Long comment1Id,
            @PathVariable("page") Long page);

    /**
     * 发布二级评论
     *
     * @param userId
     * @param comment1Id
     * @param comment2Content
     * @return
     */
    @PostMapping("/rpc/post/public/comment2Info")
    int postPublicComment2InfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("comment1Id") Long comment1Id,
            @RequestParam("comment2Content") String comment2Content);

    /**
     * 一级评论点赞
     *
     * @param comment1Id
     * @return
     */
    @PostMapping("/rpc/post/touch/star/comment1Info")
    int postTouchStarComment1InfoRpc(
            @RequestParam("comment1Id") Long comment1Id);

    /**
     * 一级评论点踩
     *
     * @param comment1Id
     * @return
     */
    @PostMapping("/rpc/post/touch/tread/comment1Info")
    int postTouchTreadComment1InfoRpc(
            @RequestParam("comment1Id") Long comment1Id);

    /**
     * 随机获取多篇评论
     *
     * @param topicId
     * @param randomNumber
     * @return
     */
    @GetMapping("/rpc/get/select/random/comment1Info/by/topicId/{topicId}")
    List<CommentModel> getSelectRandomComment1InfoRpc(
            @PathVariable("topicId") Long topicId,
            @RequestParam("randomNumber") Integer randomNumber);


    /**
     * 获取评论词条
     *
     * @param comment1Id
     * @return
     */
    @GetMapping("/rpc/get/select/multi/entryId/by/comment1Id/{comment1Id}")
    List<Long> getSelectMultiEntryIdsByComment1IdRpc(@PathVariable("comment1Id") Long comment1Id);

    /**
     * 更新普通评论的点赞数
     *
     * @param comment1Id
     * @return
     */
    @PostMapping("/rpc/post/update/comment1Info/touch/star/amount")
    int postUpdateComment1InfoTouchStarAmountRpc(@RequestParam("comment1Id") Long comment1Id);

    /**
     * 更新普通评论的点踩数
     *
     * @param comment1Id
     * @return
     */
    @PostMapping("/rpc/post/update/comment1Info/touch/tread/amount")
    int postUpdateComment1InfoTouchTreadAmountRpc(@RequestParam("comment1Id") Long comment1Id);

    /**
     * 获取议论信息
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/select/multi/talkInfo/by/topicId/{topicId}")
    List<CommentModel> getSelectMultiTalkInfoRpc(
            @PathVariable("topicId") Long topicId);

    /**
     * 获取议论一级评论
     *
     * @param talkId
     * @return
     */
    @GetMapping("/rpc/select/multi/talkInfo/comment1Info/by/{talkId}")
    List<CommentModel> getSelectMultiTalkComment1InfoRpc(
            @PathVariable("talkId") Long talkId);

    /**
     * 获取议论二级评论
     *
     * @param talkComment1Id
     * @return
     */
    @GetMapping("/rpc/select/multi/talkInfo/comment2Info/by/{talkComment1Id}")
    List<CommentModel> getSelectMultiTalkComment2InfoRpc(
            @PathVariable("talkComment1Id") Long talkComment1Id);

    /**
     * 发布talkInfo
     *
     * @param topicId
     * @param userId
     * @param talkTitle
     * @param talkDesc
     * @return
     */
    @PostMapping("/rpc/post/public/talkInfo")
    int postPublicTalkInfoRpc(
            @RequestParam("topicId") Long topicId,
            @RequestParam("userId") Long userId,
            @RequestParam("talkTitle") String talkTitle,
            @RequestParam("talkDesc") String talkDesc);

    /**
     * 发布talkComment1Info
     *
     * @param talkId
     * @param userId
     * @param talkCommentContent
     * @return
     */
    @PostMapping("/rpc/post/public/talkComment1Info")
    int postPublicTalkComment1InfoRpc(
            @RequestParam("topicId") Long talkId,
            @RequestParam("userId") Long userId,
            @RequestParam("talkCommentContent") String talkCommentContent);

    /**
     * 发布talkComment2Info
     *
     * @param talkComment1Id
     * @param userId
     * @param talkComment2Content
     * @return
     */
    @PostMapping("/rpc/post/public/talkComment2Info")
    int postPublicTalkComment2InfoRpc(
            @RequestParam("talkComment1Id") Long talkComment1Id,
            @RequestParam("userId") Long userId,
            @RequestParam("talkComment2Content") String talkComment2Content);

    /**
     * talkInfo点赞
     *
     * @param talkId
     * @return
     */
    @PostMapping("/rpc/post/touch/star/for/talkInfo")
    int postTouchStarTalkInfoRpc(@RequestParam("talkId") Long talkId);

    /**
     * talkInfo点踩
     *
     * @param talkId
     * @return
     */
    @PostMapping("/rpc/post/touch/tread/for/talkInfo")
    int postTouchTreadTalkInfoRpc(@RequestParam("talkId") Long talkId);

    /**
     * 给talkInfo添加词条
     *
     * @param userId
     * @param talkId
     * @param entryId
     * @return
     */
    @PostMapping("/rpc/post/add/talkInfo/for/entryInfo")
    int postAddTalkInfoForEntryInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("talkId") Long talkId,
            @RequestParam("entryId") Long entryId);

    /**
     * 给talkInfo的一级评论添加词条
     *
     * @param userId
     * @param talkComment1Id
     * @param entryId
     * @return
     */
    @PostMapping("/rpc/post/add/talkComment1Info/for/entryInfo")
    int postAddTalkComment1InfoForEntryInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("talkComment1Id") Long talkComment1Id,
            @RequestParam("entryId") Long entryId);

    /**
     * 获取talkInfo的词条
     *
     * @param talkId
     * @return
     */
    @GetMapping("/rpc/get/select/entryInfo/by/talkInfo/{talkId}")
    List<Long> getSelectTalkInfoForEntryInfoRpc(
            @PathVariable("talkId") Long talkId);

    /**
     * 获取talkInfo的一级评论的词条id
     *
     * @param talkComment1Id
     * @return
     */
    @GetMapping("/rpc/get/select/entryInfo/by/talkComment1Info/{talkComment1Id}")
    List<Long> getSelectTalkComment1InfoForEntryInfoRpc(
            @PathVariable("talkComment1Id") Long talkComment1Id);

    /**
     * 判断talk是否存在
     *
     * @param talkId
     * @return
     */
    @PostMapping("/rpc/post/is/exist/for/talkInfo")
    int postIsExistForTalkInfoRpc(@RequestParam("talkId") Long talkId);

    /**
     * 判断talkComment1是否存在
     *
     * @param talkComment1Id
     * @return
     */
    @PostMapping("/rpc/post/is/exist/for/talkComment1Info")
    int postIsExistForTalkComment1InfoRpc(
            @RequestParam("talkComment1Id") Long talkComment1Id);

    /**
     * 随机获取一篇议论
     *
     * @param topicId
     * @param randomNumber
     * @return
     */
    @GetMapping("/rpc/get/select/random/talkInfo/by/topicId/{topicId}")
    List<CommentModel> getSelectRandomTalkInfoRpc(
            @PathVariable("topicId") Long topicId,
            @RequestParam("randomNumber") Integer randomNumber);

    /**
     * 随机获取一篇议论的评论
     *
     * @param talkId
     * @param randomNumber
     * @return
     */
    @GetMapping("/rpc/get/select/random/talkComment1Info/by/talkId/{talkId}")
    List<CommentModel> getSelectRandomTalkComment1InfoRpc(
            @PathVariable("talkId") Long talkId,
            @RequestParam("randomNumber") Integer randomNumber);


    /**
     * 获取一级普通评论
     *
     * @param comment1Id
     * @return
     */
    @PostMapping("/rpc/post/select/single/comment1Info")
    CommentModel postSelectSingleComment1InfoRpc(@RequestParam("comment1Id") Long comment1Id);

    /**
     * 获取议论
     *
     * @param talkId
     * @return
     */
    @PostMapping("/rpc/post/select/single/talkModel")
    CommentModel postSelectSingleTalkInfoRpc(@RequestParam("talkId") Long talkId);

    /**
     * 获取一级议论评论
     *
     * @param talkComment1Id
     * @return
     */
    @PostMapping("/rpc/post/select/single/talkComment1Model")
    CommentModel postSelectSingleTalkComment1InfoRpc(@RequestParam("talkComment1Id") Long talkComment1Id);

    /**
     * 更新talk点赞数量
     *
     * @param talkId
     * @return
     */
    @PostMapping("/rpc/post/update/talkInfo/touch/star/amount")
    int postUpdateTalkInfoTouchStarAmountRpc(@RequestParam("talkId") Long talkId);

    /**
     * 更新talk点踩数量
     *
     * @param talkId
     * @return
     */
    @PostMapping("/rpc/post/update/talkInfo/touch/tread/amount")
    int postUpdateTalkInfoTouchTreadAmountRpc(@RequestParam("talkId") Long talkId);

    /**
     * 更新talk评论点赞数量
     *
     * @param talkComment1Id
     * @return
     */
    @PostMapping("/rpc/post/update/talkComment1Info/touch/star/amount")
    int postUpdateTalkComment1InfoTouchStarAmountRpc(@RequestParam("talkComment1Id") Long talkComment1Id);

    /**
     * 更新talk评论点踩数量
     *
     * @param talkComment1Id
     * @return
     */
    @PostMapping("/rpc/post/update/talkComment1Info/touch/tread/amount")
    int postUpdateTalkComment1InfoTouchTreadAmountRpc(@RequestParam("talkComment1Id") Long talkComment1Id);

    /**
     * 查询用户所有评论
     *
     * @param userId
     * @param page
     * @return
     */
    @GetMapping("/rpc/get/select/multi/user/publiced/commentInfo")
    List<CommentModel> getSelectMultiUserPublicedCommentInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("page") Long page);

    /**
     * 获取用户发布所有评论数量
     *
     * @return
     */
    @GetMapping("/rpc/get/select/multi/user/publiced/commentInfo/page")
    Long getSelectMultiUserPublicedCommentInfoPageRpc(@RequestParam("userId") Long userId);

    /**
     * 获取一级评论点赞量
     *
     * @param comment1Id
     * @return
     */
    @GetMapping("/rpc/get/select/comment1Info/star/amount")
    Long getSelectComment1InfoStarAmountRpc(@RequestParam("comment1Id") Long comment1Id);

    /**
     * 获取一级议论评论点赞量
     *
     * @param talkComment1Id
     * @return
     */
    @GetMapping("/rpc/get/select/talkComment1Info/star/amount")
    Long getSelectTalkComment1InfoStarAmountRpc(@RequestParam("talkComment1Id") Long talkComment1Id);

    /**
     * 获取著述最新的几条评论,不分类型
     *
     * @param topicId
     * @param number
     * @return
     */
    @PostMapping("/rpc/post/select/multi/lastAll/commentInfo")
    List<CommentModel> postSelectMultiLastAllCommentInfoRpc(
            @RequestParam("topicId") Long topicId,
            @RequestParam("number") Integer number
    );

    /**
     * 获取一级评判下的二级评论数量
     *
     * @param comment1Id
     * @return
     */
    @PostMapping("/rpc/post/select/comment2Info/amount/by/comment1Id")
    Long postSelectComment2InfoAmountByComment1IdRpc(@RequestParam("comment1Id") Long comment1Id);

    /**
     * 判断一级评论是否点过赞
     *
     * @param userId
     * @param comment1Id
     * @return
     */
    @PostMapping("/rpc/post/select/is/star/for/comment1Info")
    int postSelectIsStarForComment1InfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("comment1Id") Long comment1Id
    );

    /**
     * 判断一级评论是否点过踩
     *
     * @param userId
     * @param comment1Id
     * @return
     */
    @PostMapping("/rpc/post/select/is/tread/for/comment1Info")
    int postSelectIsTreadForComment1InfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("comment1Id") Long comment1Id
    );

    /**
     * 获取著述下所有普通评论的总数量
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/commentInfo/amount/by/topicId")
    Long postSelectCommentInfoAmountByTopicIdRpc(
            @RequestParam("topicId") Long topicId
    );
    /**
     * 获取著述下所有普通1级评论的总数量
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/comment1Info/amount/by/topicId")
    Long postSelectComment1InfoAmountByTopicIdRpc(@RequestParam("topicId") Long topicId);
}
