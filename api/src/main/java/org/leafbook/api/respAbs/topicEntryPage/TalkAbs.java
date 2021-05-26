package org.leafbook.api.respAbs.topicEntryPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class TalkAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long talkId;
    private String talkTitle;
    private String talkDesc;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    private String username;
    private String userAvatar;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userLevel;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userTalkCommentTime;
    private String talkCommentContent;
    private List<EntryAbs> entryAbsList;
}
