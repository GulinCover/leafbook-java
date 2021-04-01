package org.leafbook.api.respAbs.indexPage;

import lombok.Data;

import java.util.List;

@Data
public class TopicListResp {
    private Integer code;
    private List<TopicAbs> topicAbsList;
}
