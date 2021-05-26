package org.leafbook.api.respAbs.topicPublicPage.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class TopicCommentEntryAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long entryId;
    private String entryName;
}
