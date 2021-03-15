package org.leafbook.api.respAbs.explorePage;

import lombok.Data;

import java.util.List;

@Data
public class TopicInfosResp {
    private Integer code;
    private List<TopicAbs> topicAbsList;
}
