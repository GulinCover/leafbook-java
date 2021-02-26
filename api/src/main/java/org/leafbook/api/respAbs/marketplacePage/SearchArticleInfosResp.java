package org.leafbook.api.respAbs.marketplacePage;

import lombok.Data;

import java.util.List;

@Data
public class SearchArticleInfosResp {
    private String code;
    private Integer maxPage;
    private List<ArticleInfosAbs> articleInfosAbsList;
}
