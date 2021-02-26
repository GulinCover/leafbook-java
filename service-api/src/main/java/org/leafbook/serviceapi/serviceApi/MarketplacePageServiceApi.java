package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.respAbs.marketplacePage.ArticleInfoAbs;
import org.leafbook.api.respAbs.marketplacePage.ArticleInfosAbs;
import org.leafbook.api.respAbs.marketplacePage.EntryAbs;
import org.leafbook.api.testModel.marketplacePage.MarketplaceTestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MarketplacePageServiceApi {

    public List<String> getSelectSellType() {
        List<String> list = new LinkedList<>();
        list.add("著述");
        list.add("昵称");
        return list;
    }

    public List<EntryAbs> getSelectOfficialEntryInfos() {
        return MarketplaceTestModel.createRandomEntryAbsList();
    }

    public List<EntryAbs> getSelectRandomFourOfficialEntryInfos() {
        return MarketplaceTestModel.createFourEntryAbsList();
    }

    public List<ArticleInfoAbs> getSelectRandomPopularArticleInfos() {
        return MarketplaceTestModel.createRandomArticleAbsList();
    }

    public List<ArticleInfosAbs> getSelectRandomArticleInfosByEntryInfos() {
        return MarketplaceTestModel.createRandomArticleInfosAbsList();
    }
}











