package org.leafbook.serviceapi.serviceApi.repository;

import org.leafbook.api.modelApi.commentInfo.CommentModel;
import org.leafbook.api.modelApi.entryInfo.EntryModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.respAbs.repository.overviewPage.EntryAbs;
import org.leafbook.api.respAbs.repository.overviewPage.LastCommentAbs;
import org.leafbook.api.respAbs.repository.overviewPage.LastTopicAbs;
import org.leafbook.api.respAbs.repository.overviewPage.LastTopicCommentAbs;
import org.leafbook.serviceapi.serviceRpc.commentService.CommentServiceRpc;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class OverviewPageServiceApi {
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    @Autowired
    private CommentServiceRpc commentServiceRpc;
    /**
     * 获取用户发布的一些著述
     * @param userId
     * @return
     */
    public List<LastTopicAbs> postSelectMultiLastTopicInfo(Long userId) {
        List<LastTopicAbs> lastTopicAbsList = new LinkedList<>();
        List<TopicModel> topicModelList = topicServiceRpc.postSelectMultiLastTopicInfoRpc(userId,6);
        if (Objects.nonNull(topicModelList) && !topicModelList.isEmpty()) {
            for (TopicModel topicModel:topicModelList) {
                LastTopicAbs lastTopicAbs = new LastTopicAbs();
                lastTopicAbs.setPublicTime(topicModel.getPublicTime());
                lastTopicAbs.setTopicDesc(topicModel.getTopicDesc());
                lastTopicAbs.setTopicId(topicModel.getTopicId());
                lastTopicAbs.setTopicTitle(topicModel.getTopicTitle());

                List<EntryAbs> entryAbsList = new LinkedList<>();

                List<Long> entryIds = topicServiceRpc.getSelectSingleTopicInfoForEntryIdsRpc(topicModel.getTopicId());
                if (Objects.nonNull(entryIds) && !entryIds.isEmpty()) {
                    List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIds);

                    if (Objects.nonNull(entryShowModelList) && !entryShowModelList.isEmpty()) {
                        for (EntryShowModel entryShowModel:entryShowModelList) {
                            EntryAbs entryAbs = new EntryAbs();
                            entryAbs.setEntryName(entryShowModel.getEntryName());

                            entryAbsList.add(entryAbs);
                        }
                    }
                }

                lastTopicAbs.setEntryAbsList(entryAbsList);

                lastTopicAbsList.add(lastTopicAbs);
            }
        }

        return lastTopicAbsList;
    }

    /**
     * 获取用户发布著述的最近的一些评论
     * @param userId
     * @return
     */
    public List<LastTopicCommentAbs> postSelectMultiLastCommentInfo(Long userId) {
        List<LastTopicCommentAbs> lastTopicCommentAbsList = new LinkedList<>();

        List<TopicModel> topicModelList = topicServiceRpc.postSelectMultiLastTopicInfoRpc(userId,6);
        if (Objects.nonNull(topicModelList) && !topicModelList.isEmpty()) {
            for (TopicModel topicModel:topicModelList) {
                LastTopicCommentAbs topicCommentAbs = new LastTopicCommentAbs();
                topicCommentAbs.setTopicId(topicModel.getTopicId());
                topicCommentAbs.setTopicTitle(topicModel.getTopicTitle());

                List<LastCommentAbs> lastCommentAbsList = new LinkedList<>();

                //查询著述最新几条评论,部分类型
                List<CommentModel> commentModelList = commentServiceRpc.postSelectMultiLastAllCommentInfoRpc(topicModel.getTopicId(),6);
                if (Objects.nonNull(commentModelList) && !commentModelList.isEmpty()) {
                    for (CommentModel commentModel:commentModelList) {
                        LastCommentAbs lastCommentAbs = new LastCommentAbs();
                        lastCommentAbs.setComment(commentModel.getContent());
                        lastCommentAbs.setPublicTime(commentModel.getPublicTime());

                        lastCommentAbsList.add(lastCommentAbs);
                    }
                }

                topicCommentAbs.setLastCommentAbsList(lastCommentAbsList);

                lastTopicCommentAbsList.add(topicCommentAbs);
            }
        }

        return lastTopicCommentAbsList;
    }
}
