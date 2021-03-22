package org.leafbook.api.respAbs.topicEntryPage;

import lombok.Data;

import java.util.List;

@Data
public class CommentAbs {
    private Long userId;
    private String username;
    private String userAvatar;
    private Long userCommentTime;
    private String commentContent;
    private List<EntryAbs> entryAbsList;
}
