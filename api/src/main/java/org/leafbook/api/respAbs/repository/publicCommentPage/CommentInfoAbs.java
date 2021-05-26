package org.leafbook.api.respAbs.repository.publicCommentPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class CommentInfoAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicId;
    private String topicTitle;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long talkId;
    private List<EntryAbs> entryAbsList;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long likeNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long comment1Id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long comment2Id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long talkComment1Id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long talkComment2Id;
    private String commentContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long commentTime;
}
