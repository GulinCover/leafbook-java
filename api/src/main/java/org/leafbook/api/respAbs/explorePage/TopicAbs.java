package org.leafbook.api.respAbs.explorePage;

import lombok.Data;

@Data
public class TopicAbs extends org.leafbook.api.respAbs.indexPage.TopicAbs {
    private String topicDesc;
    private String topicUsername;
    private Long entryId;
    private Long userId;
    private Long likeNumber;
}
