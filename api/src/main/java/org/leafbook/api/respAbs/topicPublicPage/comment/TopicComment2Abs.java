package org.leafbook.api.respAbs.topicPublicPage.comment;

import lombok.Data;

@Data
public class TopicComment2Abs {
    private Long comment2Id;
    private Long userId;
    private String userAvatar;
    private String username;
    private Integer userLevel;
    private Long commentTime;
    private String commentContent;
}
