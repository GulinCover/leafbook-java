package org.leafbook.api.respAbs.repository.publicCommentPage;

import lombok.Data;

import java.util.List;

@Data
public class CommentInfoAbs {
    private Long topicId;
    private String topicTitle;
    private List<EntryAbs> entryAbsList;

    private String likeNumber;
    private Long commentId;
    private String commentContent;
    private String commentType;
    private String commentLevel;
    private String commentTime;
}
