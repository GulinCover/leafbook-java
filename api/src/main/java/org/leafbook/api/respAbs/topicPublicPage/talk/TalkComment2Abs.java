package org.leafbook.api.respAbs.topicPublicPage.talk;

import lombok.Data;

@Data
public class TalkComment2Abs {
    private Long talkId;
    private Long talkComment2Id;
    private String commentContent;
    private Long commentTime;
    private Integer type;
    private Long userId;
    private String username;
    private String userAvatar;
    private Integer userLevel;
}
