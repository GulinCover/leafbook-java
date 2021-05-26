package org.leafbook.api.respAbs.topicPublicPage.branch;

import lombok.Data;

import java.util.List;

@Data
public class MainArticleAbs {
    private Long mainNumber;
    private Long branchNumber;
    private Long articleId;
    private String articleTitle;
    private Integer isBrowse;
    private Long page;
    private List<BranchArticleAbs> branchArticleAbsList;
}
