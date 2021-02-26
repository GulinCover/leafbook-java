package org.leafbook.api.testModel.marketplacePage;

import org.leafbook.api.respAbs.marketplacePage.ArticleInfoAbs;
import org.leafbook.api.respAbs.marketplacePage.ArticleInfosAbs;
import org.leafbook.api.respAbs.marketplacePage.EntryAbs;
import org.leafbook.api.testModel.indexPage.TestModel;

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
            articleInfoAbs.setArticleId((long) i);
            articleInfoAbs.setArticleType("topic");
            articleInfoAbs.setTopicId((long) i);
            articleInfoAbs.setTopicTitle(randomWord());
            articleInfoAbs.setTopicDesc(randomString().toString());
            articleInfoAbs.setBidPrice(String.valueOf(new Random().nextInt(15) + 5));
            articleInfoAbs.setUserAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");
            articleInfoAbs.setPrice(String.valueOf(new Random().nextInt(15) + 5));

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

}
