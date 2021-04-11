package org.leafbook.api.respAbs.topicPublicPage.comment;

import lombok.Data;

import java.util.List;

@Data
public class TopicComment1Abs {
    private Long comment1Id;
    private Long userId;
    private String userAvatar;
    private String username;
    private Integer userLevel;
    private Long commentTime;
    private String commentContent;
    private Long starAmount;
    private Long commentAmount;
    private Integer isStar;
    private Integer isTread;
    private List<TopicCommentEntryAbs> entryAbsList;
}
