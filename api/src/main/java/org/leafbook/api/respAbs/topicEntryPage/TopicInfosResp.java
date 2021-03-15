package org.leafbook.api.respAbs.topicEntryPage;

import lombok.Data;

import java.util.List;

@Data
public class TopicInfosResp {
    private Integer code;
    private List<TopicDetailAbs> topicDetailAbsList;
}
