package org.leafbook.serviceapi.serviceApi.userManager;

import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.commentInfo.Comment1Model;
import org.leafbook.api.modelApi.talkInfo.TalkModel;
import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment1Model;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.userInfo.UserBillModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.userManagerPage.StarRelatedIncomeAndExpenditureAbs;
import org.leafbook.serviceapi.serviceRpc.commentService.CommentServiceRpc;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.leafbook.serviceapi.serviceRpc.marketplaceService.MarketplaceServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class BillAndPayPageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private MarketplaceServiceRpc marketplaceServiceRpc;
    @Autowired
    private CommonServiceRpc commonServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private CommentServiceRpc commentServiceRpc;
    /**
     * 获取用户余额
     * @param userId
     * @return
     */
    public Long postSelectUserBalance(Long userId) {
        UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
        return Objects.nonNull(userModel) ? userModel.getBalance() : 0L;
    }

    /**
     * 获取用户赞相关的收支
     * @param userId
     * @param page
     * @return
     */
    public List<StarRelatedIncomeAndExpenditureAbs> postSelectStarRelatedIncomeAndExpenditure(Long userId, Long page) {
        List<StarRelatedIncomeAndExpenditureAbs> starRelatedIncomeAndExpenditureAbsList = new LinkedList<>();
        List<UserBillModel> userBillModelList = commonServiceRpc.postSelectStarRelatedIncomeAndExpenditureRpc(userId,page);
        if (Objects.nonNull(userBillModelList) && !userBillModelList.isEmpty()) {
            for (UserBillModel userBillModel:userBillModelList) {
                StarRelatedIncomeAndExpenditureAbs abs = new StarRelatedIncomeAndExpenditureAbs();
                abs.setPosition(userBillModel.getPosition());
                abs.setPrice(userBillModel.getPrice());
                abs.setTime(userBillModel.getPublicTime());

                Long resId = 0L;
                String type = "";
                String name = "";
                Long topicId = userBillModel.getTopicId();
                Long commentId = userBillModel.getCommentId();
                Long talkId = userBillModel.getTalkId();
                Long talkCommentId = userBillModel.getTalkCommentId();
                if (topicId != 0) {
                    resId = topicId;
                    type = "topic";
                    TopicModel topicInfoRpc = topicServiceRpc.getSelectSingleTopicInfoRpc(topicId);
                    UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(topicInfoRpc.getUserId());

                    name = userModel.getUsername();
                } else if (commentId != 0) {
                    resId = commentId;
                    type = "comment";
                    Comment1Model comment1Model = commentServiceRpc.postSelectSingleComment1InfoRpc(commentId);
                    UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(comment1Model.getUserId());

                    name = userModel.getUsername();
                } else if (talkId != 0) {
                    resId = talkId;
                    type = "talk";
                    TalkModel talkModel = commentServiceRpc.postSelectSingleTalkInfoRpc(talkId);
                    UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(talkModel.getUserId());

                    name = userModel.getUsername();
                } else if (talkCommentId != 0) {
                    resId = talkCommentId;
                    type = "talkComment";
                    TalkComment1Model talkComment1Model = commentServiceRpc.postSelectSingleTalkComment1InfoRpc(talkCommentId);
                    UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(talkComment1Model.getUserId());

                    name = userModel.getUsername();
                } else {
                    return null;
                }

                abs.setResId(resId);
                abs.setType(type);
                abs.setName(name);

                starRelatedIncomeAndExpenditureAbsList.add(abs);
            }
        }

        return starRelatedIncomeAndExpenditureAbsList;
    }
}
