package org.leafbook.api.respAbs.marketplacePage;

import lombok.Data;

import java.util.List;

@Data
public class ArticleInfosResp {
    private String code;
    private List<ArticleInfoAbs> articleInfoAbsList;
}
