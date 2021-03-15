package org.leafbook.api.respAbs.hotPage;

import lombok.Data;

import java.util.List;

@Data
public class SearchTopicsResp {
    private Integer code;
    private List<TopicInfoAbs> topicInfoAbsList;
}
