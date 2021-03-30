package org.leafbook.api.testModel.marketplacePage;

import org.leafbook.api.respAbs.marketplaceDetailPage.ArticleInfoResp;
import org.leafbook.api.respAbs.marketplaceDetailPage.ManagerAbs;
import org.leafbook.api.respAbs.marketplacePage.ArticleInfoAbs;
import org.leafbook.api.respAbs.marketplacePage.ArticleInfosAbs;
import org.leafbook.api.respAbs.marketplacePage.EntryAbs;
import org.leafbook.api.testModel.indexPage.TestModel;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MarketplaceTestModel extends TestModel {

    public static List<EntryAbs> createRandomEntryAbsList() {
        List<EntryAbs> entryAbsList = new LinkedList<>();
        for (int i = 0; i < (new Random().nextInt(15)) + 5; ++i) {
            EntryAbs entryAbs = new EntryAbs();
            entryAbs.setEntryId((long) i);
            entryAbs.setEntryAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");
            entryAbs.setEntryDesc(randomString().toString());
            entryAbs.setEntryName(randomWord());

            entryAbsList.add(entryAbs);
        }

        return entryAbsList;
    }

    public static List<EntryAbs> createFourEntryAbsList() {
        List<EntryAbs> entryAbsList = new LinkedList<>();
        for (int i = 0; i < 4; ++i) {
            EntryAbs entryAbs = new EntryAbs();
            entryAbs.setEntryId((long) i);
            entryAbs.setEntryAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");
            entryAbs.setEntryDesc(randomString().toString());
            entryAbs.setEntryName(randomWord());

            entryAbsList.add(entryAbs);
        }

        return entryAbsList;
    }

    public static List<ArticleInfoAbs> createRandomArticleAbsList() {
        List<ArticleInfoAbs> articleInfoAbsList = new LinkedList<>();

        for (int i = 0; i < (new Random().nextInt(15)) + 5; ++i) {
            ArticleInfoAbs articleInfoAbs = new ArticleInfoAbs();
            articleInfoAbs.setUserId((long) i);
            articleInfoAbs.setArticleType(0);
            articleInfoAbs.setTopicId((long) i);
            articleInfoAbs.setTopicTitle(randomWord());
            articleInfoAbs.setTopicDesc(randomString().toString());
            articleInfoAbs.setBidPrice((long)new Random().nextInt(15) + 5);
            articleInfoAbs.setUserAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");
            articleInfoAbs.setCurrentPrice((long)new Random().nextInt(15) + 5);

            List<EntryAbs> entryAbsList = new LinkedList<>();

            for (int j = 0; j < (new Random().nextInt(15)) + 5; ++j) {
                EntryAbs entryAbs = new EntryAbs();
                entryAbs.setEntryName(randomWord());
                entryAbs.setEntryId((long) j);

                entryAbsList.add(entryAbs);
            }
            articleInfoAbs.setEntryAbsList(entryAbsList);
            articleInfoAbsList.add(articleInfoAbs);
        }

        return articleInfoAbsList;
    }

    public static List<ArticleInfosAbs> createRandomArticleInfosAbsList() {
        List<ArticleInfosAbs> articleInfosAbsList = new LinkedList<>();

        for (int i = 0; i < (new Random().nextInt(15)) + 5; ++i) {
            ArticleInfosAbs articleInfosAbs = new ArticleInfosAbs();
            articleInfosAbs.setEntryId((long) i);
            articleInfosAbs.setEntryDesc(randomString().toString());
            articleInfosAbs.setEntryName(randomWord());
            articleInfosAbs.setArticleInfoAbsList(createRandomArticleAbsList());

            articleInfosAbsList.add(articleInfosAbs);
        }

        return articleInfosAbsList;
    }

//    public static ArticleInfoResp createArticleInfo(Long articleId) {
//        ArticleInfoResp resp = new ArticleInfoResp();
//
//        resp.setArticleId(articleId);
//        resp.setBidUUID(randomWord());
//        resp.setBidName(randomWord());
//        resp.setArticleUUID(randomWord());
//
//        resp.setBidTime(new Date().toString());
//        resp.setUploadTime(new Date().toString());
//
//        resp.setMaxPrice(String.valueOf(new Random().nextInt(1000)));
//        resp.setStartPrice(String.valueOf(new Random().nextInt(1000)));
//        resp.setType("topic");
//        resp.setTopicTitle(randomWord());
//        resp.setTopicDesc(randomString().toString());
//
//        SellerAbs sellerAbs = new SellerAbs();
//        sellerAbs.setSex("1");
//        sellerAbs.setUserAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");
//        sellerAbs.setUserDesc(randomString().toString());
//        sellerAbs.setUserId(1L);
//        sellerAbs.setUserLevel(String.valueOf(new Random().nextInt(5000)+1000));
//        sellerAbs.setUsername("Aelx");
//        sellerAbs.setUUID(randomWord());
//        resp.setSellerAbs(sellerAbs);
//
//        List<org.leafbook.api.respAbs.marketplaceDetailPage.EntryAbs> entryAbsList = new LinkedList<>();
//        for (int i = 0;i<new Random().nextInt(10);++i) {
//            org.leafbook.api.respAbs.marketplaceDetailPage.EntryAbs entryAbs = new org.leafbook.api.respAbs.marketplaceDetailPage.EntryAbs();
//            entryAbs.setEntryId(12L);
//            entryAbs.setEntryName(randomWord());
//
//            entryAbsList.add(entryAbs);
//        }
//        resp.setEntryAbsList(entryAbsList);
//
//        List<ManagerAbs> managerAbsList = new LinkedList<>();
//        for (int i = 0;i<new Random().nextInt(10);++i) {
//            ManagerAbs managerAbs = new ManagerAbs();
//            managerAbs.setSex("0");
//            managerAbs.setUserAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");
//            managerAbs.setUserId(45L);
//            managerAbs.setUserLevel(String.valueOf(new Random().nextInt(5000)+1000));
//            managerAbs.setUsername(randomWord());
//
//            managerAbsList.add(managerAbs);
//        }
//        resp.setManagerAbsList(managerAbsList);
//
//        return resp;
//    }

}
