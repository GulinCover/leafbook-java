package org.leafbook.serviceapi.serviceApi.topicPage;

import org.leafbook.api.modelApi.commentInfo.CommentModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.topicPublicPage.comment.TopicComment1Abs;
import org.leafbook.api.respAbs.topicPublicPage.comment.TopicComment1InfoResp;
import org.leafbook.api.respAbs.topicPublicPage.comment.TopicCommentEntryAbs;
import org.leafbook.serviceapi.serviceRpc.commentService.CommentServiceRpc;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
    /**
     * 获取普通评论
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

                comment1Abs.setIsStar(commentServiceRpc.postSelectIsStarForComment1InfoRpc(userId,commentModel.getComment1Id()));

                comment1Abs.setIsTread(commentServiceRpc.postSelectIsTreadForComment1InfoRpc(userId,commentModel.getComment1Id()));

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
        resp.setPage((long)Math.ceil(maxPage / 20));
        return resp;
    }
}
