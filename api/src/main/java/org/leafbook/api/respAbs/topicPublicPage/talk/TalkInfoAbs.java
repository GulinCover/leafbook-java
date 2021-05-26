package org.leafbook.api.respAbs.topicPublicPage.talk;

import lombok.Data;

import java.util.List;

@Data
public class TalkInfoAbs {
    private Long talkId;
    private String talkTitle;
    private String talkDesc;
    private Long publicTime;
    private Long starAmount;
    private List<TalkInfoEntryAbs> entryAbsList;
    private Long talkCommentAmount;

    private Long userId;
    private String username;
    private String userAvatar;
    private Integer userLevel;
    private Integer isStar;
}
