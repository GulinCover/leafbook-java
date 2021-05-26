package org.leafbook.serviceapi.serviceApi.topicPage;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.commentInfo.CommentModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.topicPublicPage.talk.*;
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
public class TalkPageServiceApi {
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private CommentServiceRpc commentServiceRpc;
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    @Autowired
    private CommonServiceRpc commonServiceRpc;
    /**
     * 获取talkInfo
     * @param userId
     * @param form:topicId,page
     * @return
     */
    public TalkInfoResp getSelectTalkInfos(Long userId, Map<String,Long> form) {
        TalkInfoResp resp = new TalkInfoResp();
        List<TalkInfoAbs> talkInfoAbsList = new LinkedList<>();

        Long topicId = form.get("topicId");
        if (Objects.isNull(topicId)) return resp;
        Long page = form.get("page");

        if (Objects.isNull(page) || page <= 0) page = 1L;

        List<CommentModel> talkInfoList = commentServiceRpc.getSelectMultiTalkInfoRpc(topicId, page);
        if (Objects.nonNull(talkInfoList) && !talkInfoList.isEmpty()) {
            for (CommentModel commentModel:talkInfoList) {
                TalkInfoAbs talkInfoAbs = new TalkInfoAbs();
                talkInfoAbs.setUserId(commentModel.getUserId());
                talkInfoAbs.setPublicTime(commentModel.getCreateTime());
                talkInfoAbs.setTalkId(commentModel.getTalkId());
                talkInfoAbs.setTalkTitle(commentModel.getTalkTitle());
                talkInfoAbs.setTalkDesc(commentModel.getTalkDesc());
                talkInfoAbs.setIsStar(commonServiceRpc.postSelectTouchedStarRpc(userId, commentModel.getTalkId(),"talkId"));

                UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(commentModel.getUserId());
                if (Objects.nonNull(userModel)) {
                    talkInfoAbs.setUserAvatar(userModel.getAvatar());
                    talkInfoAbs.setUsername(userModel.getUsername());
                    talkInfoAbs.setUserLevel(userModel.getLevel());
                }

                List<Long> entryIds = commentServiceRpc.getSelectTalkInfoForEntryInfoRpc(commentModel.getTalkId());
                List<TalkInfoEntryAbs> entryAbsList = new LinkedList<>();
                if (Objects.nonNull(entryIds) && !entryIds.isEmpty()) {
                    List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIds);
                    if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
                        for (EntryShowModel entryShowModel:entryInfoList) {
                            TalkInfoEntryAbs entryAbs = new TalkInfoEntryAbs();
                            entryAbs.setEntryId(entryShowModel.getEntryId());
                            entryAbs.setEntryName(entryShowModel.getEntryName());

                            entryAbsList.add(entryAbs);
                        }
                    }
                }

                talkInfoAbs.setEntryAbsList(entryAbsList);


                talkInfoAbs.setTalkCommentAmount(commentServiceRpc.getSelectSingleTalkForTalkCommentAmountRpc(commentModel.getTopicId(),commentModel.getTalkId()));
                talkInfoAbs.setStarAmount(commentServiceRpc.getSelectSingleTalkForTalkStarAmountRpc(commentModel.getTalkId()));

                talkInfoAbsList.add(talkInfoAbs);
            }
        }

        resp.setTalkInfoAbsList(talkInfoAbsList);

        resp.setTotalCommentNumber(commentServiceRpc.getSelectAllTalkCommentAmountByTopicIdRpc(topicId));

        resp.setPage(commentServiceRpc.getSelectTalkCommentNumberByTopicIdRpc(topicId));
        return resp;
    }
    /**
     * 获取talk的一级评论
     * @param userId
     * @param form:talkId,page
     * @return
     */
    public TalkComment1InfoResp postSelectTalkComment1Infos(Long userId, Map<String,Long> form) {
        TalkComment1InfoResp resp = new TalkComment1InfoResp();
        List<TalkInfoEntryAbs> talkInfoEntryAbsList = new LinkedList<>();
        List<TalkComment1Abs> talkComment1AbsList = new LinkedList<>();

        Long talkId = form.get("talkId");
        if (Objects.isNull(talkId)) return resp;
        Long page = form.get("page");
        if (Objects.isNull(page) || page <= 0) page = 1L;

        CommentModel talkInfo = commentServiceRpc.postSelectSingleTalkInfoRpc(talkId);
        if (Objects.isNull(talkInfo)) return resp;
        resp.setPublicTime(talkInfo.getCreateTime());
        resp.setTalkDesc(talkInfo.getTalkDesc());
        resp.setTalkId(talkInfo.getTalkId());
        resp.setTalkTitle(talkInfo.getTalkTitle());
        resp.setUserId(talkInfo.getUserId());

        List<CommentModel> comment1InfoList = commentServiceRpc.getSelectMultiTalkComment1InfoRpc(talkId, page);
        if (Objects.nonNull(comment1InfoList) && !comment1InfoList.isEmpty()) {
            for (CommentModel commentModel:comment1InfoList) {
                TalkComment1Abs talkComment1Abs = new TalkComment1Abs();
                talkComment1Abs.setCommentContent(commentModel.getContent());
                talkComment1Abs.setCommentTime(commentModel.getUpdateTime());
                talkComment1Abs.setTalkComment1Id(commentModel.getTalkComment1Id());

                UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(commentModel.getUserId());
                if (Objects.nonNull(userModel)) {
                    talkComment1Abs.setUserId(userModel.getId());
                    talkComment1Abs.setUserAvatar(userModel.getAvatar());
                    talkComment1Abs.setUserLevel(userModel.getLevel());
                    talkComment1Abs.setUsername(userModel.getUsername());
                }

                List<Long> entryIds = commentServiceRpc.getSelectTalkComment1InfoForEntryInfoRpc(commentModel.getTalkComment1Id());
                List<TalkInfoEntryAbs> talkComment1InfoEntryAbsList = new LinkedList<>();
                if (Objects.nonNull(entryIds) && !entryIds.isEmpty()) {
                    List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIds);
                    if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
                        for (EntryShowModel entryShowModel:entryInfoList) {
                            TalkInfoEntryAbs entryAbs = new TalkInfoEntryAbs();
                            entryAbs.setEntryId(entryShowModel.getEntryId());
                            entryAbs.setEntryName(entryShowModel.getEntryName());

                            talkComment1InfoEntryAbsList.add(entryAbs);
                        }
                    }
                }
                talkComment1Abs.setEntryAbsList(talkComment1InfoEntryAbsList);

                talkComment1Abs.setStarAmount(commentServiceRpc.getSelectTalkComment1InfoStarAmountRpc(commentModel.getTalkComment1Id()));
                talkComment1Abs.setIsStar(commonServiceRpc.postSelectTouchedStarRpc(userId,commentModel.getTalkComment1Id(),"talkComment"));

                talkComment1AbsList.add(talkComment1Abs);
            }
        }
        resp.setTalkComment1AbsList(talkComment1AbsList);


        List<Long> entryIds = commentServiceRpc.getSelectTalkInfoForEntryInfoRpc(talkId);
        if (Objects.nonNull(entryIds) && !entryIds.isEmpty()) {
            List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIds);
            if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
                for (EntryShowModel entryShowModel:entryInfoList) {
                    TalkInfoEntryAbs entryAbs = new TalkInfoEntryAbs();
                    entryAbs.setEntryId(entryShowModel.getEntryId());
                    entryAbs.setEntryName(entryShowModel.getEntryName());

                    talkInfoEntryAbsList.add(entryAbs);
                }
            }
        }
        resp.setEntryAbsList(talkInfoEntryAbsList);

        resp.setPage(commentServiceRpc.getSelectTalkComment1InfoAmountByTalkIdRpc(talkId));
        return resp;
    }
    /**
     * 获取二级评论
     * @param userId
     * @param form:talkComment1Id,page
     * @return
     */
    public TalkComment2InfoResp postSelectTalkComment2Info(Long userId, Map<String,Long> form) {
        TalkComment2InfoResp resp = new TalkComment2InfoResp();
        List<TalkComment2Abs> talkComment2AbsList = new LinkedList<>();

        Long talkComment1Id = form.get("talkComment1Id");
        if (Objects.isNull(talkComment1Id)) return resp;
        Long page = form.get("page");
        if (Objects.isNull(page) || page <= 0) page = 1L;

        List<CommentModel> talkComment2InfoList = commentServiceRpc.getSelectMultiTalkComment2InfoRpc(talkComment1Id, page);
        if (Objects.nonNull(talkComment2InfoList) && !talkComment2InfoList.isEmpty()) {
            for (CommentModel commentModel:talkComment2InfoList) {
                TalkComment2Abs talkComment2Abs = new TalkComment2Abs();
                talkComment2Abs.setCommentContent(commentModel.getContent());
                talkComment2Abs.setCommentTime(commentModel.getCreateTime());
                talkComment2Abs.setTalkComment2Id(commentModel.getTalkComment2Id());
                talkComment2Abs.setTalkId(commentModel.getTalkId());
                talkComment2Abs.setType(commentModel.getCommentType());

                talkComment2Abs.setUserId(commentModel.getUserId());
                UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(commentModel.getUserId());
                if (Objects.nonNull(userModel)) {
                    talkComment2Abs.setUserAvatar(userModel.getAvatar());
                    talkComment2Abs.setUserLevel(userModel.getLevel());
                    talkComment2Abs.setUsername(userModel.getUsername());
                }

                talkComment2AbsList.add(talkComment2Abs);
            }
        }

        resp.setTalkComment2AbsList(talkComment2AbsList);

        resp.setPage(commentServiceRpc.getSelectTalkComment2InfoAmountByTalkComment1IdRpc(talkComment1Id));
        return resp;
    }
    /**
     * 创建talkInfo
     * @param userId
     * @param form:talkTitle,talkDesc,topicId
     * @return
     */
    public CreateTalkInfoResp postInsertCreateTalkInfo(Long userId,Map<String,String> form) {
        CreateTalkInfoResp resp = new CreateTalkInfoResp();

        String talkTitle = form.get("talkTitle");
        String talkDesc = form.get("talkDesc");
        String topicId = form.get("topicId");
        if (Objects.isNull(talkTitle) || (talkTitle.length() <= 4 || talkTitle.length() >= 200)) {
            resp.setCode(4000);
            return resp;
        }
        if (Objects.isNull(talkDesc) || talkDesc.length() >= 500) {
            resp.setCode(4000);
            return resp;
        }
        if (Objects.isNull(topicId) || !Covert2Tools.isDigital(topicId)) {
            resp.setCode(4000);
            return resp;
        }

        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) {
            resp.setCode(403);
            return resp;
        }
        ret = topicServiceRpc.postIsExistForTopicInfoRpc(Covert2Tools.covertToLong(topicId));
        if (ret == 0) {
            resp.setCode(403);
            return resp;
        }

        Long retTalkId = commentServiceRpc.postPublicTalkInfoRpc(Covert2Tools.covertToLong(topicId), userId, talkTitle, talkDesc);

        resp.setCode(200);
        resp.setTalkId(retTalkId);
        return resp;
    }
}















