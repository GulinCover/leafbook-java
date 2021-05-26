package org.leafbook.api.respAbs.common;

import lombok.Data;

import java.util.List;

@Data
public class OtherUserTopicResp {
    private Integer code;
    private Long page;
    private List<OtherUserTopicAbs> topicAbsList;
}
