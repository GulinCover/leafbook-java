package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.api.modelApi.userInfo.ResModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.marketplacePage.ArticleInfoAbs;
import org.leafbook.api.respAbs.marketplacePage.ArticleInfosAbs;
import org.leafbook.api.respAbs.marketplacePage.EntryAbs;
import org.leafbook.api.respAbs.marketplacePage.SearchArticleInfosResp;
import org.leafbook.api.testModel.marketplacePage.MarketplaceTestModel;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.marketplaceService.MarketplaceServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.leafbook.utils.tools.Covert2Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        list.add("nickname");
        list.add("topic");
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
                        articleInfoAbs.setNickname(auctionModel.getNickname());
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
                        articleInfoAbs.setNickname(auctionModel.getNickname());
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
    /**
     * 搜索
     * @param entryName:词条名
     * @param entry:词条类型hot,official,nonofficial
     * @param type:拍卖品类型topic,nickname
     * @param publicTime:desc按时间查找,以~分割
     * @param content:模糊搜索
     * @param page
     * @return
     */
    public SearchArticleInfosResp getSelectArticleInfosBySearch(
            String entryName,
            String entry,
            String type,
            String publicTime,
            String content,
            Long page) {
        SearchArticleInfosResp resp = new SearchArticleInfosResp();
        List<ArticleInfoAbs> articleInfoAbsList = new LinkedList<>();

        Long startTime = 0L;
        Long endTime = 0L;
        String[] split = publicTime.split("~");
        if (split.length != 1) {
            if (!Covert2Tools.isDigital(split[0]) || !Covert2Tools.isDigital(split[1])) {
                resp.setCode(403);
                return resp;
            }

            startTime = Covert2Tools.covertToLong(split[0]);
            endTime = Covert2Tools.covertToLong(split[1]);
            if (startTime <= 0) {
                startTime = 0L;
            }
            if (endTime <= 0) {
                endTime = new Date().getTime();
            }
        } else {
            endTime = new Date().getTime();
        }

        if (page <= 1) {
            page = 1L;
        }

        if ("nickname".equals(type)) {
            List<AuctionModel> auctionModelList = marketplaceServiceRpc.getSelectSearchMultiNicknameAuctionInfoRpc(1,content,startTime,endTime,page);
            Long maxPage = marketplaceServiceRpc.getSelectSearchMultiNicknameAuctionInfoPageRpc(1,content,startTime,endTime,page);

            if (Objects.isNull(auctionModelList) || auctionModelList.isEmpty()) {
                resp.setMaxPage(0L);
                resp.setCode(200);
                return resp;
            }

            for (AuctionModel auctionModel:auctionModelList) {
                ArticleInfoAbs articleInfoAbs = new ArticleInfoAbs();
                articleInfoAbs.setAuctionId(auctionModel.getAuctionId());
                articleInfoAbs.setArticleType(1);
                articleInfoAbs.setUserId(auctionModel.getUserId());
                articleInfoAbs.setNickname(auctionModel.getNickname());

                articleInfoAbs.setCurrentPrice(auctionModel.getCurrentPrice());
                articleInfoAbs.setBidPrice(auctionModel.getStartPrice());

                UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(auctionModel.getUserId());

                if (Objects.nonNull(userModel)) {
                    articleInfoAbs.setUserAvatar(userModel.getAvatar());
                }
                articleInfoAbsList.add(articleInfoAbs);
            }

            resp.setArticleInfoAbsList(articleInfoAbsList);
            resp.setCode(200);
            resp.setMaxPage((long) Math.ceil(maxPage / 20));
            return resp;
        } else {
            List<EntryShowModel> entryShowModelList = entryServiceRpc.getSelectSearchMultiEntryInfoRpc(entryName,entry);

            if (Objects.isNull(entryShowModelList) || entryShowModelList.isEmpty()) {
                resp.setMaxPage(0L);
                resp.setCode(200);
                return resp;
            }
            List<Long> entryIds = new LinkedList<>();
            for (EntryShowModel entryShowModel:entryShowModelList) {
                entryIds.add(entryShowModel.getEntryId());
            }


            //联合查询
            List<TopicModel> topicModelList = topicServiceRpc.getSelectSearchMultiTopicInfoRpc(2,entryIds, content,startTime,endTime,page);
            Long maxPage = topicServiceRpc.getSelectSearchMultiTopicInfoPageRpc(2,entryIds, content,startTime,endTime,page);

            ArticleInfoAbs articleInfoAbs = new ArticleInfoAbs();

            List<Long> topicIds = new LinkedList<>();
            if (Objects.nonNull(topicModelList) && !topicModelList.isEmpty()) {
                for (TopicModel topicModel:topicModelList) {
                    articleInfoAbs.setArticleType(0);
                    articleInfoAbs.setTopicId(topicModel.getTopicId());
                    articleInfoAbs.setTopicDesc(topicModel.getTopicDesc());
                    articleInfoAbs.setTopicTitle(topicModel.getTopicTitle());
                    topicIds.add(topicModel.getTopicId());
                }
            }

            List<AuctionModel> auctionModelList = marketplaceServiceRpc.getSelectMultiAuctionInfoByTopicIdsRpc(topicIds);

            if (Objects.isNull(auctionModelList) || auctionModelList.isEmpty()) {
                resp.setMaxPage(0L);
                resp.setCode(200);
                return resp;
            }

            for (AuctionModel auctionModel:auctionModelList) {
                articleInfoAbs.setBidPrice(auctionModel.getStartPrice());
                articleInfoAbs.setCurrentPrice(auctionModel.getCurrentPrice());
                articleInfoAbs.setAuctionId(auctionModel.getAuctionId());

                UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(auctionModel.getUserId());

                articleInfoAbs.setUserAvatar(userModel.getAvatar());
                articleInfoAbs.setUserId(userModel.getId());

                articleInfoAbsList.add(articleInfoAbs);
            }

            resp.setArticleInfoAbsList(articleInfoAbsList);
            resp.setMaxPage((long) Math.ceil(maxPage / 20));
            resp.setCode(200);
            return resp;

        }
    }
}











