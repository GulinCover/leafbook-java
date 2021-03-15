package org.leafbook.api.respAbs.marketplacePage;

import lombok.Data;

import java.util.List;

@Data
public class SearchArticleInfosResp {
    private Integer code;
    private Integer maxPage;
    private List<ArticleInfosAbs> articleInfosAbsList;
}
