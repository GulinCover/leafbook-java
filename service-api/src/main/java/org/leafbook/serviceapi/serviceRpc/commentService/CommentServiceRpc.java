package org.leafbook.serviceapi.serviceRpc.commentService;

import org.leafbook.api.modelApi.commentInfo.Comment1Model;
import org.leafbook.api.modelApi.commentInfo.Comment2Model;
import org.leafbook.api.modelApi.talkInfo.TalkModel;
import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment1Model;
import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment2Model;
import org.leafbook.serviceapi.openfeinFallback.commentService.CommentServiceRpcFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    @GetMapping("/rpc/get/select/multi/comment1Info/by/topicId/{topicId}")
    List<Comment1Model> getSelectMultiComment1InfoRpc(
            @PathVariable("topicId") Long topicId);

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
     * 根据一级评论id获取所有二级评论
     *
     * @param comment1Id
     * @return
     */
    @GetMapping("/rpc/get/select/multi/comment2Info/by/topicId/{comment1Id}")
    List<Comment2Model> getSelectMultiComment2InfoRpc(
            @PathVariable("comment1Id") Long comment1Id);

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
    List<Comment1Model> getSelectRandomComment1InfoRpc(
            @PathVariable("topicId")Long topicId,
            @RequestParam("randomNumber")Integer randomNumber);


    /**
     * 获取评论词条
     * @param comment1Id
     * @return
     */
    @GetMapping("/rpc/get/select/multi/entryId/by/comment1Id/{comment1Id}")
    List<Long> getSelectMultiEntryIdsByComment1IdRpc(@PathVariable("comment1Id")Long comment1Id);

    //talk service rpc

    /**
     * 获取议论信息
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/select/multi/talkInfo/by/topicId/{topicId}")
    List<TalkModel> getSelectMultiTalkInfoRpc(
            @PathVariable("topicId") Long topicId);

    /**
     * 获取议论一级评论
     *
     * @param talkId
     * @return
     */
    @GetMapping("/rpc/select/multi/talkInfo/comment1Info/by/{talkId}")
    List<TalkComment1Model> getSelectMultiTalkComment1InfoRpc(
            @PathVariable("talkId") Long talkId);

    /**
     * 获取议论二级评论
     *
     * @param talkComment1Id
     * @return
     */
    @GetMapping("/rpc/select/multi/talkInfo/comment2Info/by/{talkComment1Id}")
    List<TalkComment2Model> getSelectMultiTalkComment2InfoRpc(
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
            @PathVariable("talkComment1Id")Long talkComment1Id);

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
     * @param topicId
     * @param randomNumber
     * @return
     */
    @GetMapping("/rpc/get/select/random/talkInfo/by/topicId/{topicId}")
    List<TalkModel> getSelectRandomTalkInfoRpc(
            @PathVariable("topicId")Long topicId,
            @RequestParam("randomNumber")Integer randomNumber);

    /**
     * 随机获取一篇议论的评论
     * @param talkId
     * @param randomNumber
     * @return
     */
    @GetMapping("/rpc/get/select/random/talkComment1Info/by/talkId/{talkId}")
    List<TalkComment1Model> getSelectRandomTalkComment1InfoRpc(
            @PathVariable("talkId")Long talkId,
            @RequestParam("randomNumber")Integer randomNumber);
}
