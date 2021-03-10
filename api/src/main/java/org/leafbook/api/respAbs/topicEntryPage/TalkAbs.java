package org.leafbook.api.respAbs.topicEntryPage;

import lombok.Data;

import java.util.List;

@Data
public class TalkAbs {
    private Long talkId;
    private String talkTitle;
    private Long userId;
    private String username;
    private String userAvatar;
    private String userTalkCommentTime;
    private String talkCommentContent;
    private List<EntryAbs> entryAbsList;
}
