package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.respAbs.marketplaceDetailPage.ArticleInfoResp;
import org.leafbook.api.testModel.marketplacePage.MarketplaceTestModel;
import org.springframework.stereotype.Service;

@Service
public class MarketplaceDetailPageServiceApi {

    public ArticleInfoResp getSelectArticleInfo(Long articleId) {
        return MarketplaceTestModel.createArticleInfo(articleId);
    }
}
