package org.leafbook.api.respAbs.marketplacePage;

import lombok.Data;

import java.util.List;

@Data
public class ArticleInfosResp {
    private Integer code;
    private List<ArticleInfoAbs> articleInfoAbsList;
}
