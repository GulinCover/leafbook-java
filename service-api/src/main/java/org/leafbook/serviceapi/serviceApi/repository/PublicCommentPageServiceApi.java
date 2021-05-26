package org.leafbook.serviceapi.serviceApi.repository;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.commentInfo.CommentModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.respAbs.repository.publicCommentPage.CommentInfoAbs;
import org.leafbook.api.respAbs.repository.publicCommentPage.CommentInfosResp;
import org.leafbook.api.respAbs.repository.publicTopicPage.EntryAbs;
import org.leafbook.api.respAbs.repository.publicTopicPage.PublicTopicAbs;
import org.leafbook.api.testModel.repositoryPage.RepositoryTestModel;
import org.leafbook.serviceapi.serviceRpc.commentService.CommentServiceRpc;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@GlobalTransactional
@Service
public class PublicCommentPageServiceApi {
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private CommentServiceRpc commentServiceRpc;
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private EntryServiceRpc entryServiceRpc;

    public List<PublicTopicAbs> postSelectPublicTopicInfo() {
//        return RepositoryTestModel.createPublicTopicAbsList();
        return null;
    }

    public List<EntryAbs> postSelectPublicTopicInfoAllEntryList() {
        return RepositoryTestModel.createPublicTopicAllEntryList();
    }
    /**
     * 获取回复的相关信息
     * @param userId
     * @param form:page
     * @return
     */
    public CommentInfosResp postSelectPublicCommentInfoAllEntryList(Long userId, Map<String,Long> form) {
        CommentInfosResp resp = new CommentInfosResp();
        List<CommentInfoAbs> commentInfoAbsList = new LinkedList<>();

        Long page = form.get("page");
        if (page <= 0) page = 1L;

        //查询用户发布的所有评论
        List<CommentModel> commentInfoList = commentServiceRpc.getSelectMultiUserPublicedCommentInfoRpc(userId,page);
        if (Objects.nonNull(commentInfoList) && !commentInfoList.isEmpty()) {
            for (CommentModel commentModel:commentInfoList) {
                CommentInfoAbs commentInfoAbs = new CommentInfoAbs();
                commentInfoAbs.setCommentContent(commentModel.getContent());
                commentInfoAbs.setCommentTime(commentModel.getUpdateTime());
                commentInfoAbs.setTopicId(commentModel.getTopicId());

                TopicModel topicInfo = topicServiceRpc.getSelectSingleTopicInfoRpc(commentModel.getTopicId());
                if (Objects.nonNull(topicInfo)) {
                    commentInfoAbs.setTopicTitle(topicInfo.getTopicTitle());
                }

                commentInfoAbs.setComment1Id(commentModel.getComment1Id());
                commentInfoAbs.setComment2Id(commentModel.getComment2Id());
                commentInfoAbs.setTalkComment1Id(commentModel.getTalkComment1Id());
                commentInfoAbs.setTalkComment2Id(commentModel.getTalkComment2Id());

                Long starAmount = 0L;
                if (commentModel.getTalkId() == null && commentModel.getComment2Id() == null) {
                    starAmount = commentServiceRpc.getSelectComment1InfoStarAmountRpc(commentModel.getComment1Id());
                } else if (commentModel.getTalkId() != null && commentModel.getTalkComment2Id() == null) {
                    starAmount = commentServiceRpc.getSelectTalkComment1InfoStarAmountRpc(commentModel.getTalkComment1Id());
                }

                commentInfoAbs.setLikeNumber(starAmount);

                commentInfoAbsList.add(commentInfoAbs);
            }
        }

        resp.setCommentInfoAbsList(commentInfoAbsList);

        Long maxPage = commentServiceRpc.getSelectMultiUserPublicedCommentInfoPageRpc(userId);
        resp.setPage(maxPage);
        return resp;
    }
}











