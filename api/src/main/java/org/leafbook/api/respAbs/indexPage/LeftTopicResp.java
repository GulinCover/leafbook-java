package org.leafbook.api.respAbs.indexPage;

import lombok.Data;

import java.util.List;

@Data
public class LeftTopicResp {
    private String code;
    private List<TopicAbs> topicAbsList;
}
