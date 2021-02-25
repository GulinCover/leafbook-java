package org.leafbook.api.respAbs.explorePage;

import lombok.Data;

import java.util.List;

@Data
public class TopicInfosResp {
    private String code;
    private List<TopicAbs> topicAbsList;
}
