package org.leafbook.serviceapi.serviceApi.userManager;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.commentInfo.CommentModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.userInfo.UserBillModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.userManagerPage.*;
import org.leafbook.serviceapi.serviceRpc.commentService.CommentServiceRpc;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.leafbook.serviceapi.serviceRpc.marketplaceService.MarketplaceServiceRpc;
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
     * 获取用户月增长
     * @param userId
     * @return
     */
    public Long postSelectUserBalanceIncome(Long userId) {
        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return 0L;
        return commonServiceRpc.postSelectUserBalanceIncomeForMonthRpc(userId);
    }
    /**
     * 获取用户月支出
     * @param userId
     * @return
     */
    public Long postSelectUserBalanceExpenditure(Long userId) {
        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return 0L;
        return commonServiceRpc.postSelectUserBalanceExpenditureForMonthRpc(userId);
    }

    /**
     * 获取用户赞相关的收支
     * @param userId
     * @param form:page
     * @return
     */
    public StarRelatedIncomeAndExpenditureResp postSelectStarRelatedIncomeAndExpenditure(Long userId,Map<String,Long> form) {
        StarRelatedIncomeAndExpenditureResp resp = new StarRelatedIncomeAndExpenditureResp();
        List<StarRelatedIncomeAndExpenditureAbs> starRelatedIncomeAndExpenditureAbsList = new LinkedList<>();

        Long page = form.get("page");
        if (Objects.isNull(page) || page <= 0) {
            resp.setCode(403);
            return resp;
        }

        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return resp;

        List<UserBillModel> userBillModelList = commonServiceRpc.postSelectStarRelatedIncomeAndExpenditureRpc(userId,page);
        if (Objects.nonNull(userBillModelList) && !userBillModelList.isEmpty()) {
            for (UserBillModel userBillModel:userBillModelList) {
                StarRelatedIncomeAndExpenditureAbs abs = new StarRelatedIncomeAndExpenditureAbs();
                abs.setPosition(userBillModel.getPosition());
                abs.setPrice(userBillModel.getPrice());
                abs.setTime(userBillModel.getCreateTime());

                Long resId = 0L;
                String type = "";
                String name = "";
                Long topicId = userBillModel.getTopicId();
                Long commentId = userBillModel.getCommentId();
                Long talkId = userBillModel.getTalkId();
                Long talkCommentId = userBillModel.getTalkCommentId();
                if (Objects.nonNull(topicId) && topicId != 0) {
                    resId = topicId;
                    type = "topic";
                    TopicModel topicInfoRpc = topicServiceRpc.getSelectSingleTopicInfoRpc(topicId);
                    UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(topicInfoRpc.getUserId());

                    name = userModel.getUsername();
                } else if (Objects.nonNull(commentId) && commentId != 0) {
                    resId = commentId;
                    type = "comment";
                    CommentModel comment1Model = commentServiceRpc.postSelectSingleComment1InfoRpc(commentId);
                    UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(comment1Model.getUserId());

                    name = userModel.getUsername();
                } else if (Objects.nonNull(talkId) && talkId != 0) {
                    resId = talkId;
                    type = "talk";
                    CommentModel talkModel = commentServiceRpc.postSelectSingleTalkInfoRpc(talkId);
                    UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(talkModel.getUserId());

                    name = userModel.getUsername();
                } else if (Objects.nonNull(talkCommentId) && talkCommentId != 0) {
                    resId = talkCommentId;
                    type = "talkComment";
                    CommentModel talkComment1Model = commentServiceRpc.postSelectSingleTalkComment1InfoRpc(talkCommentId);
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

        resp.setPage(commonServiceRpc.postSelectUserStarRelatedIncomeAndExpenditurePageAmountRpc(userId));
        resp.setStarRelatedIncomeAndExpenditureAbsList(starRelatedIncomeAndExpenditureAbsList);
        return resp;
    }

    /**
     * 获取用户赞相关的总收支
     * @param userId
     * @return
     */
    public Long postSelectUserStarRelatedIncomeAndExpenditureAmount(Long userId) {
        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return 0L;
        return commonServiceRpc.postSelectUserStarRelatedIncomeAndExpenditureAmountRpc(userId);
    }
    /**
     * 获取用户赞总收入
     * @param userId
     * @return
     */
    public Long postSelectStarIncomeAmount(Long userId) {
        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return 0L;
        return commonServiceRpc.postSelectUserStarRelatedIncomeAmountRpc(userId);
    }
    /**
     * 获取用户赞总支出
     * @param userId
     * @return
     */
    public Long postSelectStarExpenditureAmount(Long userId) {
        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return 0L;
        return commonServiceRpc.postSelectUserStarRelatedExpenditureAmountRpc(userId);
    }


    /**
     * 获取用户著述相关的收支
     * @param userId
     * @param form:page
     * @return
     */
    public TopicRelatedIncomeAndExpenditureResp postSelectTopicRelatedIncomeAndExpenditure(Long userId, Map<String,Long> form) {
        TopicRelatedIncomeAndExpenditureResp resp = new TopicRelatedIncomeAndExpenditureResp();
        List<TopicRelatedIncomeAndExpenditureAbs> topicRelatedIncomeAndExpenditureAbsList = new LinkedList<>();

        Long page = form.get("page");
        if (Objects.isNull(page) || page <= 0) {
            resp.setCode(403);
            return resp;
        }

        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return resp;

        List<UserBillModel> userBillModelList = commonServiceRpc.postSelectTopicRelatedIncomeAndExpenditureRpc(userId, page);
        if (Objects.nonNull(userBillModelList) && !userBillModelList.isEmpty()) {
            for (UserBillModel userBillModel:userBillModelList) {
                TopicRelatedIncomeAndExpenditureAbs abs = new TopicRelatedIncomeAndExpenditureAbs();
                abs.setTopicId(userBillModel.getTopicId());
                abs.setPosition(userBillModel.getPosition());
                abs.setPrice(userBillModel.getPrice());
                abs.setTime(userBillModel.getCreateTime());

                TopicModel topicInfo = topicServiceRpc.getSelectSingleTopicInfoRpc(userBillModel.getTopicId());
                if (Objects.nonNull(topicInfo)) {
                    abs.setTopicTitle(topicInfo.getTopicTitle());
                }

                Long ownerId = topicServiceRpc.getSelectTopicOwnerRpc(userBillModel.getTopicId());
                if (ownerId.equals(userId)) {
                    abs.setIsMe(1);
                } else {
                    abs.setIsMe(0);
                }

                topicRelatedIncomeAndExpenditureAbsList.add(abs);
            }
        }

        resp.setPage(commonServiceRpc.postSelectUserTopicRelatedIncomeAndExpenditurePageAmountRpc(userId));
        resp.setTopicRelatedIncomeAndExpenditureAbsList(topicRelatedIncomeAndExpenditureAbsList);
        return resp;
    }
    /**
     * 获取用户著述相关的总收支
     * @param userId
     * @return
     */
    public Long postSelectUserTopicRelatedIncomeAndExpenditureAmount(Long userId) {
        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return 0L;
        return commonServiceRpc.postSelectUserTopicRelatedIncomeAndExpenditureAmountRpc(userId);
    }

    /**
     * 获取用户著述总收入
     * @param userId
     * @return
     */
    public Long postSelectTopicIncomeAmount(Long userId) {
        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return 0L;
        return commonServiceRpc.postSelectUserTopicRelatedIncomeAmountRpc(userId);
    }
    /**
     * 获取用户著述总支出
     * @param userId
     * @return
     */
    public Long postSelectTopicExpenditureAmount(Long userId) {
        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return 0L;
        return commonServiceRpc.postSelectUserTopicRelatedExpenditureAmountRpc(userId);
    }

    /**
     * 获取用户购入卖出相关的收支
     * @param userId
     * @param form:page
     * @return
     */
    public BuyAndSellRelatedIncomeAndExpenditureResp postSelectBuyAndSellRelatedIncomeAndExpenditure(Long userId, Map<String,Long> form) {
        BuyAndSellRelatedIncomeAndExpenditureResp resp = new BuyAndSellRelatedIncomeAndExpenditureResp();
        List<BuyAndSellRelatedIncomeAndExpenditureAbs> buyAndSellRelatedIncomeAndExpenditureAbsList = new LinkedList<>();

        Long page = form.get("page");
        if (Objects.isNull(page) || page <= 0) {
            resp.setCode(403);
            return resp;
        }

        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return resp;

        List<UserBillModel> userBillModelList = commonServiceRpc.postSelectBuyAndSellRelatedIncomeAndExpenditureRpc(userId, page);
        if (Objects.nonNull(userBillModelList) && !userBillModelList.isEmpty()) {
            for (UserBillModel userBillModel:userBillModelList) {
                BuyAndSellRelatedIncomeAndExpenditureAbs abs = new BuyAndSellRelatedIncomeAndExpenditureAbs();
                abs.setBillId(userBillModel.getBillId());
                abs.setPosition(userBillModel.getPosition());
                abs.setPrice(userBillModel.getPrice());
                abs.setTime(userBillModel.getCreateTime());

                buyAndSellRelatedIncomeAndExpenditureAbsList.add(abs);
            }
        }

        resp.setPage(commonServiceRpc.postSelectUserBuyAndSellRelatedIncomeAndExpenditurePageAmountRpc(userId));
        resp.setBuyAndSellRelatedIncomeAndExpenditureAbsList(buyAndSellRelatedIncomeAndExpenditureAbsList);
        return resp;
    }

    /**
     * 获取用户著述相关的总收支
     * @param userId
     * @return
     */
    public Long postSelectUserBuyAndSellRelatedIncomeAndExpenditureAmount(Long userId) {
        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return 0L;
        return commonServiceRpc.postSelectUserBuyAndSellRelatedIncomeAndExpenditureAmountRpc(userId);
    }

    /**
     * 获取用户买卖总收入
     * @param userId
     * @return
     */
    public Long postSelectBuyAndSellIncomeAmount(Long userId) {
        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return 0L;
        return commonServiceRpc.postSelectUserBuyAndSellRelatedIncomeAmountRpc(userId);
    }
    /**
     * 获取用户买卖总支出
     * @param userId
     * @return
     */
    public Long postSelectBuyAndSellExpenditureAmount(Long userId) {
        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret == 0) return 0L;
        return commonServiceRpc.postSelectUserBuyAndSellRelatedExpenditureAmountRpc(userId);
    }

}
