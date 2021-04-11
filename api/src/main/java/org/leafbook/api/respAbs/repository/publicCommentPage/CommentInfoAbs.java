package org.leafbook.api.respAbs.repository.publicCommentPage;

import lombok.Data;

import java.util.List;

@Data
public class CommentInfoAbs {
    private Long topicId;
    private String topicTitle;
    private Long talkId;
    private List<EntryAbs> entryAbsList;

    private Long likeNumber;
    private Long comment1Id;
    private Long comment2Id;
    private Long talkComment1Id;
    private Long talkComment2Id;
    private String commentContent;
    private Long commentTime;
}
