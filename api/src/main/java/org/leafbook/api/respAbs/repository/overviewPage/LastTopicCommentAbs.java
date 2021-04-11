package org.leafbook.api.respAbs.repository.overviewPage;

import lombok.Data;

import java.util.List;

@Data
public class LastTopicCommentAbs {
    private Long topicId;
    private String topicTitle;
    private List<LastCommentAbs> lastCommentAbsList;
}
