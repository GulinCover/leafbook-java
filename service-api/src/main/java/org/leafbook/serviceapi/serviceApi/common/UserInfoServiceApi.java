package org.leafbook.serviceapi.serviceApi.common;

import org.leafbook.api.modelApi.commentInfo.Comment1Model;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.common.UserInfoResp;
import org.leafbook.serviceapi.serviceRpc.commentService.CommentServiceRpc;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.recordService.RecordServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.leafbook.utils.tools.Covert2Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerError;
import java.util.Map;
import java.util.Objects;

@Service
public class UserInfoServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private CommonServiceRpc commonServiceRpc;
    @Autowired
    private CommentServiceRpc commentServiceRpc;
    @Autowired
    private EntryServiceRpc entryServiceRpc;

    /**
     * 获取登录用户信息
     *
     * @param userId
     * @return
     */
    public UserInfoResp postSelectUserInfo(Long userId) throws ServerError {
        UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
        if (Objects.isNull(userModel)) throw new ServerError("服务器异常", new Error("数据丢失"));
        UserInfoResp userInfoResp = new UserInfoResp();
        userInfoResp.setLocation(userModel.getLocation());
        userInfoResp.setSex(userModel.getSex());
        userInfoResp.setUserAvatar(userModel.getAvatar());
        userInfoResp.setUserDesc(userModel.getDesc());
        userInfoResp.setUserId(userId);
        userInfoResp.setBalance(userModel.getBalance());
        userInfoResp.setUserLevel(userModel.getLevel());
        userInfoResp.setUUID(userModel.getUuid());
        userInfoResp.setUsername(userModel.getUsername());

        return userInfoResp;
    }

    /**
     * 添加点赞
     *
     * @param userId
     * @param form:  entryId,talkCommentId,talkId,commentId,topicId,articleId,type
     *               - type:topic,comment,talk,talkComment,entry,article
     * @return
     */
    public int postInsertLiked(Long userId, Map<String, String> form) {
        String type = form.get("type");
        if (Objects.isNull(type)) return 403;

        UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
        if (Objects.isNull(userModel)) return 403;
        if (userModel.getBalance() < 2) return 5000;

        if ("topic".equals(type)) {
            String topicIdString = form.get("topicId");
            if (Objects.isNull(topicIdString) || !Covert2Tools.isDigital(topicIdString)) return 403;
            Long topicId = Covert2Tools.covertToLong(topicIdString);

            //检查著述id
            TopicModel topicInfo = topicServiceRpc.getSelectSingleTopicInfoRpc(topicId);
            if (Objects.isNull(topicInfo)) return 403;

            //检查是否点过赞
            int ret = commonServiceRpc.postSelectTouchedStarRpc(userId, topicId, type);
            if (ret == 1) return 5001;

            //记录点赞信息
            ret = commonServiceRpc.postInsertTouchStarRpc(userId, topicId, type);
            if (ret == 0) return 403;

            //更新著述点赞信息数量
            ret = topicServiceRpc.postUpdateTopicStarAmountRpc(topicId);
            if (ret == 0) return 500;

            //更改余额
            Long balance = userModel.getBalance();
            userModel.setBalance(balance - 2);
            ret = userServiceRpc.postUpdateSingleUserInfoByUserInfoRpc(userModel);
            if (ret == 0) return 500;

        }
        else if ("entry".equals(type)) {
            String entryIdString = form.get("entryId");
            if (Objects.isNull(entryIdString) || !Covert2Tools.isDigital(entryIdString)) return 403;
            Long entryId = Covert2Tools.covertToLong(entryIdString);

            //检查词条合法性
            int ret = entryServiceRpc.postSelectDetectLegalityWithEntryIdRpc(entryId);
            if (ret == 0) return 500;

            //检查是否点过赞
            ret = commonServiceRpc.postSelectTouchedStarRpc(userId, entryId, type);
            if (ret == 1) return 403;

            //记录点赞信息
            ret = commonServiceRpc.postInsertTouchStarRpc(userId, entryId, type);
            if (ret == 0) return 403;

            //更新词条点赞信息数量
            ret = entryServiceRpc.postUpdateEntryInfoStarAmountRpc(entryId);
            if (ret == 0) return 500;

            //更改余额
            Long balance = userModel.getBalance();
            userModel.setBalance(balance - 2);
            ret = userServiceRpc.postUpdateSingleUserInfoByUserInfoRpc(userModel);
            if (ret == 0) return 500;
        }
        else if ("comment".equals(type)) {
            String commentIdString = form.get("commentId");
            if (Objects.isNull(commentIdString) || !Covert2Tools.isDigital(commentIdString)) return 403;
            Long commentId = Covert2Tools.covertToLong(commentIdString);

            //检查comment合法性
            Comment1Model comment1Model = commentServiceRpc.postSelectSingleComment1InfoRpc(commentId);
            if (Objects.isNull(comment1Model)) return 403;

            //检查是否点过赞
            int ret = commonServiceRpc.postSelectTouchedStarRpc(userId, commentId, type);
            if (ret == 1) return 5001;

            //记录点赞信息
            ret = commonServiceRpc.postInsertTouchStarRpc(userId, commentId, type);
            if (ret == 0) return 403;

            //更新评论点赞信息数量
            ret = commentServiceRpc.postUpdateComment1InfoTouchStarAmountRpc(commentId);
            if (ret == 0) return 500;

            //更改余额
            Long balance = userModel.getBalance();
            userModel.setBalance(balance - 2);
            ret = userServiceRpc.postUpdateSingleUserInfoByUserInfoRpc(userModel);
            if (ret == 0) return 500;
        }
        else if ("talk".equals(type)) {
            String talkIdString = form.get("talkId");
            if (Objects.isNull(talkIdString) || !Covert2Tools.isDigital(talkIdString)) return 403;
            Long talkId = Covert2Tools.covertToLong(talkIdString);

            //检查talkId合法性
            int ret = commentServiceRpc.postIsExistForTalkInfoRpc(talkId);
            if (ret == 0) return 403;

            //检查是否点过赞
            ret = commonServiceRpc.postSelectTouchedStarRpc(userId, talkId, type);
            if (ret == 1) return 5001;

            //记录点赞信息
            ret = commonServiceRpc.postInsertTouchStarRpc(userId, talkId, type);
            if (ret == 0) return 403;

            //更新talk点赞数量
            ret = commentServiceRpc.postUpdateTalkInfoTouchStarAmountRpc(talkId);
            if (ret == 0) return 500;

            //更改余额
            Long balance = userModel.getBalance();
            userModel.setBalance(balance - 2);
            ret = userServiceRpc.postUpdateSingleUserInfoByUserInfoRpc(userModel);
            if (ret == 0) return 500;
        }
        else if ("talkComment".equals(type)) {
            String talkCommentIdString = form.get("talkCommentId");
            if (Objects.isNull(talkCommentIdString) || !Covert2Tools.isDigital(talkCommentIdString)) return 403;
            Long talkCommentId = Covert2Tools.covertToLong(talkCommentIdString);

            //检查talk评论合法性
            int ret = commentServiceRpc.postIsExistForTalkComment1InfoRpc(talkCommentId);
            if (ret == 0) return 403;

            //检查是否点过赞
            ret = commonServiceRpc.postSelectTouchedStarRpc(userId, talkCommentId, type);
            if (ret == 1) return 5001;

            //记录点赞信息
            ret = commonServiceRpc.postInsertTouchStarRpc(userId, talkCommentId, type);
            if (ret == 0) return 403;

            //更新talk评论点赞数量
            ret = commentServiceRpc.postUpdateTalkComment1InfoTouchStarAmountRpc(talkCommentId);
            if (ret == 0) return 500;

            //更改余额
            Long balance = userModel.getBalance();
            userModel.setBalance(balance - 2);
            ret = userServiceRpc.postUpdateSingleUserInfoByUserInfoRpc(userModel);
            if (ret == 0) return 500;
        }
        else if ("article".equals(type)) {
            String articleIdString = form.get("articleId");
            if (Objects.isNull(articleIdString) || !Covert2Tools.isDigital(articleIdString)) return 403;
            Long articleId = Covert2Tools.covertToLong(articleIdString);

            //检查文章合法性
            ArticleModel articleModel = topicServiceRpc.getSelectSingleArticleInfoRpc(articleId);
            if (Objects.isNull(articleModel)) return 403;

            //检查是否点过赞
            int ret = commonServiceRpc.postSelectTouchedStarRpc(userId, articleId, type);
            if (ret == 1) return 5001;

            //记录点赞信息
            ret = commonServiceRpc.postInsertTouchStarRpc(userId, articleId, type);
            if (ret == 0) return 403;

            //更新文章点赞数量
            ret = topicServiceRpc.postUpdateArticleStarAmountRpc(articleId);
            if (ret == 0) return 500;


            //更改余额
            Long balance = userModel.getBalance();
            userModel.setBalance(balance - 2);
            ret = userServiceRpc.postUpdateSingleUserInfoByUserInfoRpc(userModel);
            if (ret == 0) return 500;
        }
        else {
            return 4000;
        }

        return 200;
    }


    /**
     * 添加点踩
     *
     * @param userId
     * @param form:  entryId,talkCommentId,talkId,commentId,topicId,articleId,type
     *               - type:topic,comment,talk,talkComment,entry,article
     * @return
     */
    public int postInsertTread(Long userId, Map<String, String> form) {
        String type = form.get("type");
        if (Objects.isNull(type)) return 403;

        UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
        if (Objects.isNull(userModel)) return 403;
        if (userModel.getBalance() < 2) return 5000;


        if ("topic".equals(type)) {
            String topicIdString = form.get("topicId");
            if (Objects.isNull(topicIdString) || !Covert2Tools.isDigital(topicIdString)) return 403;
            Long topicId = Covert2Tools.covertToLong(topicIdString);

            //检查著述id
            TopicModel topicInfo = topicServiceRpc.getSelectSingleTopicInfoRpc(topicId);
            if (Objects.isNull(topicInfo)) return 403;

            //检查是否点过踩
            int ret = commonServiceRpc.postSelectTouchedTreadRpc(userId, topicId, type);
            if (ret == 1) return 5002;

            //记录点踩信息
            ret = commonServiceRpc.postInsertTouchTreadRpc(userId, topicId, type);
            if (ret == 0) return 403;

            //更新著述点踩信息数量
            ret = topicServiceRpc.postUpdateTopicTreadAmountRpc(topicId);
            if (ret == 0) return 500;

            //更改余额
            Long balance = userModel.getBalance();
            userModel.setBalance(balance - 2);
            ret = userServiceRpc.postUpdateSingleUserInfoByUserInfoRpc(userModel);
            if (ret == 0) return 500;

        }
        else if ("entry".equals(type)) {
            String entryIdString = form.get("entryId");
            if (Objects.isNull(entryIdString) || !Covert2Tools.isDigital(entryIdString)) return 403;
            Long entryId = Covert2Tools.covertToLong(entryIdString);

            //检查词条合法性
            int ret = entryServiceRpc.postSelectDetectLegalityWithEntryIdRpc(entryId);
            if (ret == 0) return 500;

            //检查是否点过踩
            ret = commonServiceRpc.postSelectTouchedStarRpc(userId, entryId, type);
            if (ret == 1) return 5002;

            //记录点踩信息
            ret = commonServiceRpc.postInsertTouchStarRpc(userId, entryId, type);
            if (ret == 0) return 403;

            //更新词条点踩信息数量
            ret = entryServiceRpc.postUpdateEntryInfoStarAmountRpc(entryId);
            if (ret == 0) return 500;

            //更改余额
            Long balance = userModel.getBalance();
            userModel.setBalance(balance - 2);
            ret = userServiceRpc.postUpdateSingleUserInfoByUserInfoRpc(userModel);
            if (ret == 0) return 500;
        }
        else if ("comment".equals(type)) {
            String commentIdString = form.get("commentId");
            if (Objects.isNull(commentIdString) || !Covert2Tools.isDigital(commentIdString)) return 403;
            Long commentId = Covert2Tools.covertToLong(commentIdString);

            //检查comment合法性
            Comment1Model comment1Model = commentServiceRpc.postSelectSingleComment1InfoRpc(commentId);
            if (Objects.isNull(comment1Model)) return 403;

            //检查是否点过踩
            int ret = commonServiceRpc.postSelectTouchedTreadRpc(userId, commentId, type);
            if (ret == 1) return 5002;

            //记录点踩信息
            ret = commonServiceRpc.postInsertTouchTreadRpc(userId, commentId, type);
            if (ret == 0) return 403;

            //更新评论点踩信息数量
            ret = commentServiceRpc.postUpdateComment1InfoTouchTreadAmountRpc(commentId);
            if (ret == 0) return 500;

            //更改余额
            Long balance = userModel.getBalance();
            userModel.setBalance(balance - 2);
            ret = userServiceRpc.postUpdateSingleUserInfoByUserInfoRpc(userModel);
            if (ret == 0) return 500;
        }
        else if ("talk".equals(type)) {
            String talkIdString = form.get("talkId");
            if (Objects.isNull(talkIdString) || !Covert2Tools.isDigital(talkIdString)) return 403;
            Long talkId = Covert2Tools.covertToLong(talkIdString);

            //检查talkId合法性
            int ret = commentServiceRpc.postIsExistForTalkInfoRpc(talkId);
            if (ret == 0) return 403;

            //检查是否点过踩
            ret = commonServiceRpc.postSelectTouchedTreadRpc(userId, talkId, type);
            if (ret == 1) return 5002;

            //记录点踩信息
            ret = commonServiceRpc.postInsertTouchTreadRpc(userId, talkId, type);
            if (ret == 0) return 403;

            //更新talk点踩数量
            ret = commentServiceRpc.postUpdateTalkInfoTouchTreadAmountRpc(talkId);
            if (ret == 0) return 500;

            //更改余额
            Long balance = userModel.getBalance();
            userModel.setBalance(balance - 2);
            ret = userServiceRpc.postUpdateSingleUserInfoByUserInfoRpc(userModel);
            if (ret == 0) return 500;
        }
        else if ("talkComment".equals(type)) {
            String talkCommentIdString = form.get("talkCommentId");
            if (Objects.isNull(talkCommentIdString) || !Covert2Tools.isDigital(talkCommentIdString)) return 403;
            Long talkCommentId = Covert2Tools.covertToLong(talkCommentIdString);

            //检查talk评论合法性
            int ret = commentServiceRpc.postIsExistForTalkComment1InfoRpc(talkCommentId);
            if (ret == 0) return 403;

            //检查是否点过踩
            ret = commonServiceRpc.postSelectTouchedTreadRpc(userId, talkCommentId, type);
            if (ret == 1) return 5002;

            //记录点踩信息
            ret = commonServiceRpc.postInsertTouchTreadRpc(userId, talkCommentId, type);
            if (ret == 0) return 403;

            //更新talk评论点踩数量
            ret = commentServiceRpc.postUpdateTalkComment1InfoTouchTreadAmountRpc(talkCommentId);
            if (ret == 0) return 500;

            //更改余额
            Long balance = userModel.getBalance();
            userModel.setBalance(balance - 2);
            ret = userServiceRpc.postUpdateSingleUserInfoByUserInfoRpc(userModel);
            if (ret == 0) return 500;
        }
        else if ("article".equals(type)) {
            String articleIdString = form.get("articleId");
            if (Objects.isNull(articleIdString) || !Covert2Tools.isDigital(articleIdString)) return 403;
            Long articleId = Covert2Tools.covertToLong(articleIdString);

            //检查文章合法性
            ArticleModel articleModel = topicServiceRpc.getSelectSingleArticleInfoRpc(articleId);
            if (Objects.isNull(articleModel)) return 403;

            //检查是否点过踩
            int ret = commonServiceRpc.postSelectTouchedTreadRpc(userId, articleId, type);
            if (ret == 1) return 5002;

            //记录点踩信息
            ret = commonServiceRpc.postInsertTouchTreadRpc(userId, articleId, type);
            if (ret == 0) return 403;

            //更新文章点踩数量
            ret = topicServiceRpc.postUpdateArticleTreadAmountRpc(articleId);
            if (ret == 0) return 500;


            //更改余额
            Long balance = userModel.getBalance();
            userModel.setBalance(balance - 2);
            ret = userServiceRpc.postUpdateSingleUserInfoByUserInfoRpc(userModel);
            if (ret == 0) return 500;
        }
        else {
            return 4000;
        }

        return 200;
    }
}
