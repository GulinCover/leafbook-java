package org.leafbook.api.respAbs.marketplacePage;

import lombok.Data;

import java.util.List;

@Data
public class SearchArticleInfosResp {
    private Integer code;
    private Long maxPage;
    private Long entryId;
    private String entryName;
    private String entryDesc;
    private List<ArticleInfoAbs> articleInfoAbsList;
}
