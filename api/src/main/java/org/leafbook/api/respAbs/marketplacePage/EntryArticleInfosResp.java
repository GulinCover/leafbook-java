package org.leafbook.api.respAbs.marketplacePage;

import lombok.Data;

import java.util.List;

@Data
public class EntryArticleInfosResp {
    private Integer code;
    private List<ArticleInfosAbs> articleInfosAbsList;
}
