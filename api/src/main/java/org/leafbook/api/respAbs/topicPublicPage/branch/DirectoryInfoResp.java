package org.leafbook.api.respAbs.topicPublicPage.branch;

import lombok.Data;

import java.util.List;

@Data
public class DirectoryInfoResp {
    private Integer code;
    private Long page;
    private List<MainArticleAbs> mainArticleAbsList;
}
