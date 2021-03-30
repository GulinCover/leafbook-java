package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.api.modelApi.billInfo.ResModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.marketplacePage.ArticleInfoAbs;
import org.leafbook.api.respAbs.marketplacePage.ArticleInfosAbs;
import org.leafbook.api.respAbs.marketplacePage.EntryAbs;
import org.leafbook.api.testModel.marketplacePage.MarketplaceTestModel;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.marketplaceService.MarketplaceServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class MarketplacePageServiceApi {
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    @Autowired
    private MarketplaceServiceRpc marketplaceServiceRpc;
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    /**
     * 获取售卖类型
     * @return
     */
    public List<String> getSelectSellType() {
        List<String> list = new LinkedList<>();
        list.add("著述");
        list.add("昵称");
        return list;
    }

    /**
     * 获取板块类型(#只显示官方)
     * @return
     */
    public List<EntryAbs> getSelectOfficialEntryInfos() {
        List<EntryAbs> entryAbsList = new LinkedList<>();
        List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectAllEntryInfoWithTypeRpc("official");
        if (Objects.nonNull(entryShowModelList) && !entryShowModelList.isEmpty()) {
            for (EntryShowModel entryShowModel:entryShowModelList) {
                EntryAbs entryAbs = new EntryAbs();
                entryAbs.setEntryId(entryShowModel.getEntryId());
                entryAbs.setEntryAvatar(entryShowModel.getEntryAvatar());
                entryAbs.setEntryDesc(entryShowModel.getEntryDesc());
                entryAbs.setEntryName(entryShowModel.getEntryName());

                entryAbsList.add(entryAbs);
            }
        }
        return entryAbsList;
    }
    /**
     * 随机推荐4条官方词条信息
     * @return
     */
    public List<EntryAbs> getSelectRandomFourOfficialEntryInfos() {
        List<EntryAbs> entryAbsList = new LinkedList<>();
        List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectRandomMultiEntryInfoByTypeRpc("official",4);
        if (Objects.nonNull(entryShowModelList) && !entryShowModelList.isEmpty()) {
            for (EntryShowModel entryShowModel:entryShowModelList) {
                EntryAbs entryAbs = new EntryAbs();
                entryAbs.setEntryId(entryShowModel.getEntryId());
                entryAbs.setEntryAvatar(entryShowModel.getEntryAvatar());
                entryAbs.setEntryDesc(entryShowModel.getEntryDesc());
                entryAbs.setEntryName(entryShowModel.getEntryName());

                entryAbsList.add(entryAbs);
            }
        }
        return entryAbsList;
    }
    /**
     * 随机获取5-10条拍卖品信息
     * @return
     */
    public List<ArticleInfoAbs> getSelectRandomPopularArticleInfos() {
        List<ArticleInfoAbs> articleInfoAbsList = new LinkedList<>();

        Integer number = new Random().nextInt(5)+5;
        List<AuctionModel> auctionModelList = marketplaceServiceRpc.getSelectRandomMultiAuctionInfoRpc(number);

        if (Objects.nonNull(auctionModelList) && !auctionModelList.isEmpty()) {
            for (AuctionModel auctionModel:auctionModelList) {
                ArticleInfoAbs articleInfoAbs = new ArticleInfoAbs();
                articleInfoAbs.setAuctionId(auctionModel.getAuctionId());
                articleInfoAbs.setBidPrice(auctionModel.getStartPrice());
                articleInfoAbs.setCurrentPrice(auctionModel.getCurrentPrice());

                final Long userId = auctionModel.getUserId();
                articleInfoAbs.setTopicId(userId);

                final UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
                if (Objects.nonNull(userModel)) {
                    articleInfoAbs.setUserAvatar(userModel.getAvatar());
                }

                Integer type = auctionModel.getType();
                articleInfoAbs.setArticleType(type);
                switch (type) {
                    case 0:
                        TopicModel topicInfo = topicServiceRpc.getSelectSingleTopicInfoRpc(auctionModel.getTopicId());
                        if (Objects.nonNull(topicInfo)) {
                            articleInfoAbs.setTopicId(topicInfo.getTopicId());
                            articleInfoAbs.setTopicTitle(topicInfo.getTopicTitle());
                            articleInfoAbs.setTopicDesc(topicInfo.getTopicDesc());

                            List<EntryAbs> entryAbsList = new LinkedList<>();

                            List<Long> entryIdList = topicServiceRpc.getSelectSingleTopicInfoForEntryIdsRpc(topicInfo.getTopicId());
                            if (Objects.nonNull(entryIdList) && !entryIdList.isEmpty()) {

                                List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIdList);
                                if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
                                    for (EntryShowModel entryShowModel:entryInfoList) {
                                        EntryAbs entryAbs = new EntryAbs();
                                        entryAbs.setEntryId(entryShowModel.getEntryId());
                                        entryAbs.setEntryName(entryShowModel.getEntryName());
                                        entryAbs.setEntryDesc(entryShowModel.getEntryDesc());
                                        entryAbs.setEntryAvatar(entryShowModel.getEntryAvatar());

                                        entryAbsList.add(entryAbs);
                                    }
                                }
                            }

                            articleInfoAbs.setEntryAbsList(entryAbsList);
                        }
                        break;
                    case 1:
                        ResModel resModel = userServiceRpc.postSelectSingleResInfoByUserIdRpc(userId);
                        if (Objects.nonNull(resModel)) {
                            articleInfoAbs.setNickname(resModel.getNickname());
                        }
                        break;
                    default:
                        break;
                }

                articleInfoAbsList.add(articleInfoAbs);
            }
        }

        return articleInfoAbsList;
    }

    /**
     * 随机获取最新5-10条拍卖品信息
     * @return
     */
    public List<ArticleInfoAbs> getSelectRandomRecentEntryInfos() {
        List<ArticleInfoAbs> articleInfoAbsList = new LinkedList<>();

        Integer number = new Random().nextInt(5)+5;
        List<AuctionModel> auctionModelList = marketplaceServiceRpc.getSelectRandomMultiLatestAuctionRpc(number);

        if (Objects.nonNull(auctionModelList) && !auctionModelList.isEmpty()) {
            for (AuctionModel auctionModel:auctionModelList) {
                ArticleInfoAbs articleInfoAbs = new ArticleInfoAbs();
                articleInfoAbs.setAuctionId(auctionModel.getAuctionId());
                articleInfoAbs.setBidPrice(auctionModel.getStartPrice());
                articleInfoAbs.setCurrentPrice(auctionModel.getCurrentPrice());

                final Long userId = auctionModel.getUserId();
                articleInfoAbs.setTopicId(userId);

                final UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
                if (Objects.nonNull(userModel)) {
                    articleInfoAbs.setUserAvatar(userModel.getAvatar());
                }

                Integer type = auctionModel.getType();
                articleInfoAbs.setArticleType(type);
                switch (type) {
                    case 0:
                        TopicModel topicInfo = topicServiceRpc.getSelectSingleTopicInfoRpc(auctionModel.getTopicId());
                        if (Objects.nonNull(topicInfo)) {
                            articleInfoAbs.setTopicId(topicInfo.getTopicId());
                            articleInfoAbs.setTopicTitle(topicInfo.getTopicTitle());
                            articleInfoAbs.setTopicDesc(topicInfo.getTopicDesc());

                            List<EntryAbs> entryAbsList = new LinkedList<>();

                            List<Long> entryIdList = topicServiceRpc.getSelectSingleTopicInfoForEntryIdsRpc(topicInfo.getTopicId());
                            if (Objects.nonNull(entryIdList) && !entryIdList.isEmpty()) {

                                List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectMultiEntryInfoRpc(entryIdList);
                                if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
                                    for (EntryShowModel entryShowModel:entryInfoList) {
                                        EntryAbs entryAbs = new EntryAbs();
                                        entryAbs.setEntryId(entryShowModel.getEntryId());
                                        entryAbs.setEntryName(entryShowModel.getEntryName());
                                        entryAbs.setEntryDesc(entryShowModel.getEntryDesc());
                                        entryAbs.setEntryAvatar(entryShowModel.getEntryAvatar());

                                        entryAbsList.add(entryAbs);
                                    }
                                }
                            }

                            articleInfoAbs.setEntryAbsList(entryAbsList);
                        }
                        break;
                    case 1:
                        ResModel resModel = userServiceRpc.postSelectSingleResInfoByUserIdRpc(userId);
                        if (Objects.nonNull(resModel)) {
                            articleInfoAbs.setNickname(resModel.getNickname());
                        }
                        break;
                    default:
                        break;
                }

                articleInfoAbsList.add(articleInfoAbs);
            }
        }

        return articleInfoAbsList;
    }

    /**
     * 按词条随机获取拍卖品信息
     * @return
     */
    public List<ArticleInfosAbs> getSelectRandomArticleInfosByEntryInfos() {

        return MarketplaceTestModel.createRandomArticleInfosAbsList();
    }

    public List<ArticleInfosAbs> getSelectArticleInfosBySearch() {
        return MarketplaceTestModel.createRandomArticleInfosAbsList();
    }
}











