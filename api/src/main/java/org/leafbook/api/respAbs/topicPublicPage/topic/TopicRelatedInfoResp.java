package org.leafbook.api.respAbs.topicPublicPage.topic;

import lombok.Data;

@Data
public class TopicRelatedInfoResp {
    private Integer code;
    private Long topicId;
    private String topicTitle;
    private Long StarAmount;
    private Long browseAmount;
    private Long userId;
    private String username;
}
