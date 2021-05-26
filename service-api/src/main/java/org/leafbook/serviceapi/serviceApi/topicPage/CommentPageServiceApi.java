package org.leafbook.serviceapi.serviceApi.topicPage;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.commentInfo.CommentModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.topicPublicPage.comment.*;
import org.leafbook.serviceapi.serviceRpc.commentService.CommentServiceRpc;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.leafbook.utils.tools.Covert2Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@GlobalTransactional
@Service
public class CommentPageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private CommentServiceRpc commentServiceRpc;
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    @Autowired
    private CommonServiceRpc commonServiceRpc;
    /**
     * 获取普通一级评论
     * @param userId
     * @param topicId
     * @param page
     * @return
     */
    public List<TopicComment1Abs> getSelectTopicCommentPageCommentInfo(Long userId,Long topicId,Long page) {
        List<TopicComment1Abs> topicComment1AbsList = new LinkedList<>();
        List<CommentModel> comment1InfoList = commentServiceRpc.getSelectMultiComment1InfoRpc(topicId, page);
        if (Objects.nonNull(comment1InfoList) && !comment1InfoList.isEmpty()) {
            for (CommentModel commentModel:comment1InfoList) {
                TopicComment1Abs comment1Abs = new TopicComment1Abs();
                comment1Abs.setComment1Id(commentModel.getComment1Id());
                comment1Abs.setCommentContent(commentModel.getContent());
                comment1Abs.setCommentTime(commentModel.getUpdateTime());

                UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(commentModel.getUserId());
                if (Objects.nonNull(userModel)) {
                    comment1Abs.setUserAvatar(userModel.getAvatar());
                    comment1Abs.setUserId(userModel.getId());
                    comment1Abs.setUserLevel(userModel.getLevel());
                    comment1Abs.setUsername(userModel.getUsername());
                }

                comment1Abs.setStarAmount(commentServiceRpc.getSelectComment1InfoStarAmountRpc(commentModel.getComment1Id()));

                comment1Abs.setCommentAmount(commentServiceRpc.postSelectComment2InfoAmountByComment1IdRpc(commentModel.getComment1Id()));

                comment1Abs.setIsStar(commonServiceRpc.postSelectTouchedStarRpc(userId,commentModel.getComment1Id(),"comment"));

                comment1Abs.setIsTread(commonServiceRpc.postSelectTouchedTreadRpc(userId,commentModel.getComment1Id(),"comment"));

                List<Long> entryIds = commentServiceRpc.getSelectEntryInfoForComment1InfoRpc(commentModel.getComment1Id());
                if (Objects.nonNull(entryIds) && !entryIds.isEmpty()) {
                    List<TopicCommentEntryAbs> topicCommentEntryAbsList = new LinkedList<>();

                    List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIds);
                    for (EntryShowModel entryShowModel:entryInfoList) {
                        TopicCommentEntryAbs topicCommentEntryAbs = new TopicCommentEntryAbs();
                        topicCommentEntryAbs.setEntryId(entryShowModel.getEntryId());
                        topicCommentEntryAbs.setEntryName(entryShowModel.getEntryName());

                        topicCommentEntryAbsList.add(topicCommentEntryAbs);
                    }
                    comment1Abs.setEntryAbsList(topicCommentEntryAbsList);

                }

                topicComment1AbsList.add(comment1Abs);
            }
        }


        return topicComment1AbsList;
    }
    /**
     * 获取著述一级评论数量信息
     * @param userId
     * @param topicId
     * @return
     */
    public TopicComment1InfoResp getSelectTopicComment1Info(Long userId,Long topicId) {
        TopicComment1InfoResp resp = new TopicComment1InfoResp();

        resp.setCommentTotalAmount(commentServiceRpc.postSelectCommentInfoAmountByTopicIdRpc(topicId));

        Long maxPage = commentServiceRpc.postSelectComment1InfoAmountByTopicIdRpc(topicId);
        resp.setPage(maxPage);
        return resp;
    }
    /**
     * 获取二级评论
     * @param userId
     * @param page
     * @return
     */
    public TopicComment2InfoResp getSelectTopicComment2Info(Long userId,Long comment1Id,Long page) {
        TopicComment2InfoResp resp = new TopicComment2InfoResp();

        List<TopicComment2Abs> topicComment2AbsList = new LinkedList<>();

        List<CommentModel> comment2InfoList = commentServiceRpc.getSelectMultiComment2InfoRpc(comment1Id, page);
        if (Objects.nonNull(comment2InfoList) && !comment2InfoList.isEmpty()) {
            for (CommentModel commentModel:comment2InfoList) {
                TopicComment2Abs topicComment2Abs = new TopicComment2Abs();
                topicComment2Abs.setComment2Id(commentModel.getComment2Id());
                topicComment2Abs.setCommentContent(commentModel.getContent());
                topicComment2Abs.setCommentTime(commentModel.getCreateTime());

                UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(commentModel.getUserId());
                if (Objects.nonNull(userModel)) {
                    topicComment2Abs.setUserAvatar(userModel.getAvatar());
                    topicComment2Abs.setUserId(userModel.getId());
                    topicComment2Abs.setUserLevel(userModel.getLevel());
                    topicComment2Abs.setUsername(userModel.getUsername());
                }

                topicComment2AbsList.add(topicComment2Abs);
            }
        }

        resp.setTopicComment2AbsList(topicComment2AbsList);

        resp.setPage(commentServiceRpc.postSelectComment2InfoAmountByComment1IdRpc(comment1Id));

        return resp;
    }

    /**
     * 发布普通评论
     * @param userId
     * @param form:userId,topicId,content,comment1Id
     * @return
     */
    public int postInsertCommentInfo(Long userId, Map<String,String> form) {
        String commentUserIdStr = form.get("userId");
        String topicIdStr = form.get("topicId");
        String content = form.get("content");
        String comment1IdStr = form.get("comment1Id");

        if (userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId) != 1) {
            return 403;
        }

        if (!Covert2Tools.isDigital(topicIdStr)) {
            return 4000;
        }

        Long topicId = Covert2Tools.covertToLong(topicIdStr);
        content = content
                .replaceAll("<","&lt;")
                .replaceAll(">","&gt;")
                .replaceAll("\"","&quot;")
                .replaceAll("'","&#39;");

        System.out.println(content);

        if ("".equals(comment1IdStr) || "0".equals(comment1IdStr) || Objects.isNull(comment1IdStr)) {
            return commentServiceRpc.postPublicComment1InfoRpc(userId, topicId, content);
        } else {
            if (!Covert2Tools.isDigital(comment1IdStr) || !Covert2Tools.isDigital(commentUserIdStr)) {
                return 4000;
            }

            Long comment1Id = Covert2Tools.covertToLong(comment1IdStr);
            Long commentUserId = Covert2Tools.covertToLong(commentUserIdStr);

            if (userServiceRpc.postSelectDetectLegalityWithUserIdRpc(commentUserId) != 1) {
                return 403;
            }
            return commentServiceRpc.postPublicComment2InfoRpc(userId, topicId, comment1Id, commentUserId, content);
        }
    }
}
