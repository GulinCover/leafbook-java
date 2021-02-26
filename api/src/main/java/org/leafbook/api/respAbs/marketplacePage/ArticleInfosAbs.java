package org.leafbook.api.respAbs.marketplacePage;

import lombok.Data;

@Data
public class ArticleInfosAbs extends ArticleInfosResp {
    private Long entryId;
    private String entryName;
    private String entryDesc;
}
