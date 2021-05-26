package org.leafbook.api.respAbs.topicPublicPage.talk;

import lombok.Data;

import java.util.List;

@Data
public class TalkComment1Abs {
    private Long talkComment1Id;
    private Long userId;
    private String username;
    private String userAvatar;
    private Integer userLevel;
    private Long commentTime;
    private String commentContent;
    private List<TalkInfoEntryAbs> entryAbsList;
    private Long starAmount;
    private Integer isStar;
}
