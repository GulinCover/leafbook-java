package org.leafbook.api.respAbs.topicEntryPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class CommentAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    private String username;
    private String userAvatar;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userCommentTime;
    private String commentContent;
    private List<EntryAbs> entryAbsList;
}
