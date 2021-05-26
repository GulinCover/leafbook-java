package org.leafbook.api.respAbs.repository.overviewPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class LastTopicCommentAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicId;
    private String topicTitle;
    private List<LastCommentAbs> lastCommentAbsList;
}
