package org.leafbook.api.respAbs.topicPublicPage.manager;

import lombok.Data;

import java.util.List;

@Data
public class TopicManagerInfoResp {
    private Integer code;
    private List<TopicManagerAbs> topicManagerAbsList;
}
