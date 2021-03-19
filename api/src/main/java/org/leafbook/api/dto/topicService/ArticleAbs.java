package org.leafbook.api.dto.topicService;

import lombok.Data;

@Data
public class ArticleAbs {
    private Long topicId;
    private Long userId;
    private Long mainNumber;
    private Long branchNumber;
    private String articleTitle;
    private String articleDesc;
    private String articleContent;
}
