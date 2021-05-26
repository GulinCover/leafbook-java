package org.leafbook.api.respAbs.topicPublicPage.topic;

import lombok.Data;

import java.util.List;

@Data
public class TopicArticleInfoResp {
    private Integer code;
    private Long userId;
    private Integer isOwner;
    private Integer userLevel;
    private String username;
    private String userAvatar;
    private Long publicTime;

    private Long articleId;
    private String articleTitle;
    private String articleDesc;
    private String articleContent;
    private Long mainNumber;
    private Long branchNumber;

    private List<ArticleEntryAbs> articleEntryAbsList;
}
