package org.leafbook.api.respAbs.topicPublicPage.topic;

import lombok.Data;

@Data
public class BranchAbs {
    private Long articleId;
    private Long mainNumber;
    private Long branchNumber;
    private String articleTitle;
}
