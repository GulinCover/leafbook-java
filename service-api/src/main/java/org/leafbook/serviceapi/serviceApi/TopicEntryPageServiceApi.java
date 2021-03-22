package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.modelApi.commentInfo.Comment1Model;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.talkInfo.TalkModel;
import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment1Model;
import org.leafbook.api.modelApi.topicInfo.ContributorModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.topicEntryPage.*;
import org.leafbook.api.testModel.topicEntryPage.TopicEntryTestModel;
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
public class TopicEntryPageServiceApi {
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private CommentServiceRpc commentServiceRpc;

    /**
     * 获取词条信息
     *
     * @param entryId
     * @return
     */
    public EntryInfoResp getSelectEntryInfo(Long entryId) {
        EntryShowModel entryShowModel = entryServiceRpc.getSelectSingleEntryInfoRpc(entryId);
        if (Objects.isNull(entryShowModel)) return null;

        EntryInfoResp entryInfoResp = new EntryInfoResp();

        //用户查询
        UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(entryShowModel.getUserId());
        if (Objects.isNull(userModel)) return null;
        entryInfoResp.setApplicant(userModel.getUsername());

        entryInfoResp.setEntryId(entryId);
        entryInfoResp.setEntryDesc(entryShowModel.getEntryDesc());
        entryInfoResp.setEntryName(entryShowModel.getEntryName());
        entryInfoResp.setEntryAvatar(entryShowModel.getEntryAvatar());
        entryInfoResp.setEntryCreatorId(entryShowModel.getUserId());
        entryInfoResp.setPassTime(entryShowModel.getPublicTime());

        //查询点赞数
        Long starAmount = entryServiceRpc.getSelectEntryInfoStarAmountRpc(entryId);
        entryInfoResp.setLikedNumber(starAmount);

        entryInfoResp.setRelatedTopicNumber(topicServiceRpc.getSelectMultiTopicInfoAmountByEntryInfoRpc(entryId));

        return entryInfoResp;
    }

    /**
     * 根据词条获取著述信息
     * @param entryId
     * @param page: 页码
     * @return
     */
    public List<TopicDetailAbs> getSelectTopicInfos(Long entryId,Long page) {
        List<TopicModel> topicModelList = topicServiceRpc.getSelectMultiTopicInfoRpcByEntryIdRpc(entryId, page);
        if (Objects.isNull(topicModelList)) {
            return null;
        } else if (topicModelList.isEmpty()) {
            return null;
        }

        List<TopicDetailAbs> topicDetailAbsList = new LinkedList<>();
        for (TopicModel topicModel:topicModelList) {
            TopicDetailAbs topicDetailAbs = new TopicDetailAbs();
            topicDetailAbs.setTopicId(topicModel.getTopicId());
            topicDetailAbs.setTopicTitle(topicModel.getTopicTitle());
            topicDetailAbs.setUserId(topicModel.getUserId());

            //获取用户信息
            UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(topicModel.getUserId());
            if (Objects.isNull(userModel)) return null;
            topicDetailAbs.setUsername(userModel.getUsername());

            topicDetailAbs.setLikedNumber(topicServiceRpc.getSelectTopicStarAmountRpc(topicModel.getTopicId()));

            //获取著述词条
            List<Long> entryIds = topicServiceRpc.getSelectSingleTopicInfoForEntryIdsRpc(topicModel.getTopicId());
            if (!Objects.isNull(entryIds)) {
                if (!entryIds.isEmpty()) {
                    List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIds);
                    if (!Objects.isNull(entryShowModelList)) {
                        if (!entryShowModelList.isEmpty()) {
                            List<EntryAbs> entryAbsList = new LinkedList<>();
                            for (EntryShowModel entryShowModel:entryShowModelList) {
                                EntryAbs entryAbs = new EntryAbs();
                                entryAbs.setEntryName(entryShowModel.getEntryName());
                                entryAbs.setEntryId(entryShowModel.getEntryId());

                                entryAbsList.add(entryAbs);
                            }
                            topicDetailAbs.setEntryAbsList(entryAbsList);
                        }
                    }
                }
            }

            //随机获取一条文章内容
            List<ArticleModel> randomArticleInfoList = topicServiceRpc.getSelectRandomArticleInfoRpc(topicModel.getTopicId(),1);

            if (!Objects.isNull(randomArticleInfoList)) {
                if (!randomArticleInfoList.isEmpty()) {
                    ContentAbs contentAbs = new ContentAbs();
                    contentAbs.setTopicDesc(randomArticleInfoList.get(0).getArticleDesc());

                    //获取文章词条
                    List<EntryAbs> entryAbsList = new LinkedList<>();
                    List<Long> articleEntryIds = topicServiceRpc.getSelectMultiEntryIdsByArticleIdRpc(randomArticleInfoList.get(0).getArticleId());
                    if (!Objects.isNull(articleEntryIds)) {
                        if (!articleEntryIds.isEmpty()) {
                            List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectMultiEntryInfoRpc(articleEntryIds);
                            if (!Objects.isNull(entryShowModelList)) {
                                if (!entryShowModelList.isEmpty()) {
                                    for (EntryShowModel entryShowModel:entryShowModelList) {
                                        EntryAbs entryAbs = new EntryAbs();
                                        entryAbs.setEntryId(entryShowModel.getEntryId());
                                        entryAbs.setEntryName(entryShowModel.getEntryName());

                                        entryAbsList.add(entryAbs);
                                    }
                                }
                            }

                        }
                    }

                    contentAbs.setEntryAbsList(entryAbsList);
                    topicDetailAbs.setContentAbs(contentAbs);
                }
            }

            //随机获取一条评论内容
            List<Comment1Model> randomComment1InfoList = commentServiceRpc.getSelectRandomComment1InfoRpc(topicModel.getTopicId(), 1);
            if (!Objects.isNull(randomComment1InfoList)) {
                if (!randomComment1InfoList.isEmpty()) {
                    CommentAbs commentAbs = new CommentAbs();
                    commentAbs.setCommentContent(randomComment1InfoList.get(0).getCommentContent());
                    commentAbs.setUserId(randomComment1InfoList.get(0).getUserId());
                    commentAbs.setUserCommentTime(randomComment1InfoList.get(0).getPublicTime());

                    //获取用户信息
                    UserModel commentUserInfo = userServiceRpc.postSelectSingleUserInfoRpc(randomComment1InfoList.get(0).getUserId());
                    if (!Objects.isNull(commentUserInfo)) {
                        commentAbs.setUsername(commentUserInfo.getUsername());
                        commentAbs.setUserAvatar(commentUserInfo.getAvatar());
                    }

                    //获取评论词条
                    List<EntryAbs> entryAbsList = new LinkedList<>();
                    List<Long> comment1EntryIds = commentServiceRpc.getSelectMultiEntryIdsByComment1IdRpc(randomComment1InfoList.get(0).getCommentId());
                    if (!Objects.isNull(comment1EntryIds)) {
                        if (!comment1EntryIds.isEmpty()) {
                            List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectMultiEntryInfoRpc(comment1EntryIds);
                            if (!Objects.isNull(entryShowModelList)) {
                                if (!entryShowModelList.isEmpty()) {
                                    for (EntryShowModel entryShowModel:entryShowModelList) {
                                        EntryAbs entryAbs = new EntryAbs();
                                        entryAbs.setEntryId(entryShowModel.getEntryId());
                                        entryAbs.setEntryName(entryShowModel.getEntryName());

                                        entryAbsList.add(entryAbs);
                                    }
                                }
                            }
                        }
                    }
                    commentAbs.setEntryAbsList(entryAbsList);
                    topicDetailAbs.setCommentAbs(commentAbs);
                }

            }

            //随机获取一条议论内容
            List<TalkModel> randomTalkInfoList = commentServiceRpc.getSelectRandomTalkInfoRpc(topicModel.getTopicId(), 1);
            if (!Objects.isNull(randomTalkInfoList)) {
                if (!randomTalkInfoList.isEmpty()) {
                    TalkAbs talkAbs = new TalkAbs();
                    talkAbs.setTalkTitle(randomTalkInfoList.get(0).getTalkTitle());
                    talkAbs.setTalkId(randomTalkInfoList.get(0).getTalkId());

                    //随机获取一条议论评论
                    List<TalkComment1Model> randomTalkComment1InfoList = commentServiceRpc.getSelectRandomTalkComment1InfoRpc(randomTalkInfoList.get(0).getTalkId(), 1);
                    if (!Objects.isNull(randomTalkComment1InfoList)) {
                        if (!randomTalkComment1InfoList.isEmpty()) {
                            talkAbs.setUserId(randomTalkComment1InfoList.get(0).getUserId());
                            talkAbs.setTalkCommentContent(randomTalkComment1InfoList.get(0).getTalkCommentContent());
                            talkAbs.setUserTalkCommentTime(randomTalkComment1InfoList.get(0).getPublicTime());


                            //获取用户信息
                            UserModel talkCommentUserInfo = userServiceRpc.postSelectSingleUserInfoRpc(randomTalkComment1InfoList.get(0).getUserId());
                            if (!Objects.isNull(talkCommentUserInfo)) {
                                talkAbs.setUsername(talkCommentUserInfo.getUsername());
                                talkAbs.setUserAvatar(talkCommentUserInfo.getAvatar());
                            }

                            //获取评论词条
                            List<EntryAbs> entryAbsList = new LinkedList<>();
                            List<Long> talkComment1EntryIds = commentServiceRpc.getSelectTalkComment1InfoForEntryInfoRpc(randomTalkComment1InfoList.get(0).getTalkComment1Id());
                            if (!Objects.isNull(talkComment1EntryIds)) {
                                if (!talkComment1EntryIds.isEmpty()) {
                                    List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectMultiEntryInfoRpc(talkComment1EntryIds);
                                    if (!Objects.isNull(entryShowModelList)) {
                                        if (!entryShowModelList.isEmpty()) {
                                            for (EntryShowModel entryShowModel:entryShowModelList) {
                                                EntryAbs entryAbs = new EntryAbs();
                                                entryAbs.setEntryId(entryShowModel.getEntryId());
                                                entryAbs.setEntryName(entryShowModel.getEntryName());

                                                entryAbsList.add(entryAbs);
                                            }
                                        }
                                    }
                                }
                            }
                            talkAbs.setEntryAbsList(entryAbsList);
                            topicDetailAbs.setTalkAbs(talkAbs);
                        }

                    }
                }
            }

            //随机获取8条著述贡献者
            List<ContributorModel> randomContributorInfoList = topicServiceRpc.getSelectRandomContributorInfoRpc(topicModel.getTopicId(), 8);
            if (!Objects.isNull(randomContributorInfoList)) {
                if (!randomContributorInfoList.isEmpty()) {
                    ContributorAbs contributorAbs = new ContributorAbs();
                    List<UserInfoAbs> userInfoAbsList = new LinkedList<>();

                    final List<Long> userIdList = new LinkedList<>();
                    randomContributorInfoList.forEach(it->{
                        userIdList.add(it.getUserId());
                    });
                    List<UserModel> userModelList = userServiceRpc.postSelectMultiUserInfoRpc(userIdList);

                    for (UserModel contributorUserInfo:userModelList) {
                        UserInfoAbs userInfoAbs = new UserInfoAbs();
                        userInfoAbs.setUuid(contributorUserInfo.getUuid());
                        userInfoAbs.setUsername(contributorUserInfo.getUsername());
                        userInfoAbs.setUserAvatar(contributorUserInfo.getAvatar());
                        userInfoAbs.setUserId(contributorUserInfo.getId());

                        userInfoAbsList.add(userInfoAbs);
                    }

                    contributorAbs.setUserInfoAbsList(userInfoAbsList);
                    topicDetailAbs.setContributorAbs(contributorAbs);
                }
            }
            topicDetailAbsList.add(topicDetailAbs);
        }

        return topicDetailAbsList;
    }
}
