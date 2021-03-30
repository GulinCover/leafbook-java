package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.marketplaceDetailPage.ArticleInfoResp;
import org.leafbook.api.respAbs.marketplaceDetailPage.EntryAbs;
import org.leafbook.api.respAbs.marketplaceDetailPage.ManagerAbs;
import org.leafbook.api.testModel.marketplacePage.MarketplaceTestModel;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.marketplaceService.MarketplaceServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MarketplaceDetailPageServiceApi {
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private MarketplaceServiceRpc marketplaceServiceRpc;
    @Autowired
    private EntryServiceRpc entryServiceRpc;

    /**
     * 获取拍卖品信息
     * @param auctionId
     * @return
     */
    public ArticleInfoResp getSelectArticleInfo(Long auctionId) {
        ArticleInfoResp articleInfoResp = new ArticleInfoResp();
        //获取拍卖物品信息
        AuctionModel auctionModel = marketplaceServiceRpc.getSelectSingleAuctionInfoRpc(auctionId);
        if (Objects.nonNull(auctionModel)) {
            articleInfoResp.setAuctionId(auctionId);
            articleInfoResp.setBidTime(auctionModel.getExpireTimestamp());
            articleInfoResp.setMaxPrice(auctionModel.getCurrentPrice());
            articleInfoResp.setStartPrice(auctionModel.getStartPrice());
            articleInfoResp.setUploadTime(auctionModel.getPublicTime());

            //获取售卖者信息
            UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(auctionModel.getUserId());
            if (Objects.nonNull(userModel)) {
                articleInfoResp.setSellerUserId(userModel.getId());
                articleInfoResp.setSellerUserAvatar(userModel.getAvatar());
                articleInfoResp.setSellerUserDesc(userModel.getDesc());
                articleInfoResp.setSellerUserLevel(userModel.getLevel());
                articleInfoResp.setSellerUuid(userModel.getUuid());
                articleInfoResp.setSellerSex(userModel.getSex());
            }

            //获取当前最高竞价者信息
            articleInfoResp.setMaxPriceUserId(auctionModel.getCurrentPriceUserId());
            articleInfoResp.setMaxPriceUserUuid(auctionModel.getCurrentPriceUserUuid());

            articleInfoResp.setType(auctionModel.getType());

            //根据类型获取信息
            switch (auctionModel.getType()) {
                case 0:
                    TopicModel topicInfo = topicServiceRpc.getSelectSingleTopicInfoRpc(auctionModel.getTopicId());
                    if (Objects.nonNull(topicInfo)) {
                        articleInfoResp.setTopicId(topicInfo.getTopicId());
                        articleInfoResp.setTopicDesc(topicInfo.getTopicDesc());
                        articleInfoResp.setTopicTitle(topicInfo.getTopicTitle());
                    }

                    //获取著述词条信息
                    List<Long> entryIdList = topicServiceRpc.getSelectSingleTopicInfoForEntryIdsRpc(topicInfo.getTopicId());
                    if (Objects.nonNull(entryIdList) && !entryIdList.isEmpty()) {
                        List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIdList);
                        if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
                            List<EntryAbs> entryAbsList = new LinkedList<>();

                            for (EntryShowModel entryShowModel:entryInfoList) {
                                EntryAbs entryAbs = new EntryAbs();
                                entryAbs.setEntryId(entryShowModel.getEntryId());
                                entryAbs.setEntryName(entryShowModel.getEntryName());

                                entryAbsList.add(entryAbs);
                            }

                            articleInfoResp.setEntryAbsList(entryAbsList);
                        }
                    }

                    //获取管理者信息
                    List<Long> managerIdList = topicServiceRpc.getSelectTopicManagerRpc(topicInfo.getTopicId());
                    if (Objects.nonNull(managerIdList) && !managerIdList.isEmpty()) {
                        List<UserModel> userModelList = userServiceRpc.postSelectMultiUserInfoRpc(managerIdList);

                        if (Objects.nonNull(userModelList) && !userModelList.isEmpty()) {
                            List<ManagerAbs> managerAbsList = new LinkedList<>();
                            for (UserModel managerUserModel:userModelList) {
                                ManagerAbs managerAbs = new ManagerAbs();
                                managerAbs.setUserId(managerUserModel.getId());
                                managerAbs.setSex(managerAbs.getSex());
                                managerAbs.setUserLevel(managerUserModel.getLevel());
                                managerAbs.setUsername(managerUserModel.getUsername());
                                managerAbs.setUserAvatar(managerUserModel.getAvatar());

                                managerAbsList.add(managerAbs);
                            }

                            articleInfoResp.setManagerAbsList(managerAbsList);
                        }
                    }

                    break;
                case 1:
                    articleInfoResp.setNickname(auctionModel.getNickname());
            }
        }

        return articleInfoResp;
    }

    /**
     * 竞拍物品
     * @param userId
     * @param form: auctionId,price
     * @return
     */
    public int postInsertBidingAuctionInfo(Long userId, Map<String,Long> form) {
        final Long auctionId = form.get("auctionId");
        final Long price = form.get("price");

        //判断是否是用户自己上架的物品,自己上架的物品不能竞拍
        AuctionModel auctionModel = marketplaceServiceRpc.postSelectSingleResInfoRpc(userId, auctionId);
        if (Objects.nonNull(auctionModel)) return 4000;

        //检测auctionId合法性
        int ret = marketplaceServiceRpc.postSelectDetectAuctionIdLegalityRpc(auctionId);
        if (ret == 0) return 4000;

        //判断用户是否存在
        UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
        if (Objects.isNull(userModel)) return 5003;

        //比较出价和用户余额
        if (price > userModel.getBalance()) return 5000;

        //比较出价和现最高价格
        AuctionModel auctionInfo = marketplaceServiceRpc.getSelectSingleAuctionInfoRpc(auctionId);
        if (price <= auctionInfo.getCurrentPrice()) return 4000;

        //存储竞拍信息
        ret = marketplaceServiceRpc.postBidingSingleAuctionInfoRpc(userId, auctionId, price);
        if (ret == 0) return 500;

        Long balance = userModel.getBalance();
        userModel.setBalance(balance - price);

        //更改用户余额
        ret = userServiceRpc.postUpdateSingleUserInfoByUserInfoRpc(userModel);
        if (ret == 0) return 500;

        //更改买拍信息当前最高价
        auctionInfo.setCurrentPrice(price);
        auctionInfo.setCurrentPriceUserId(userId);
        auctionInfo.setCurrentPriceUserUuid(userModel.getUuid());
        ret = marketplaceServiceRpc.postUpdateAuctionInfoByAuctionInfoRpc(auctionInfo);
        if (ret == 0) return 500;

        return 1;
    }
}
