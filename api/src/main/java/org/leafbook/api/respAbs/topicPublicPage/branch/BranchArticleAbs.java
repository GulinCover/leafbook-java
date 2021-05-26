package org.leafbook.api.respAbs.topicPublicPage.branch;

import lombok.Data;

@Data
public class BranchArticleAbs {
    private Long mainNumber;
    private Long branchNumber;
    private Long articleId;
    private String articleTitle;
    private Integer isBrowse;
}
