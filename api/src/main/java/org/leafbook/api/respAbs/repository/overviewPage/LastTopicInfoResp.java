package org.leafbook.api.respAbs.repository.overviewPage;

import lombok.Data;

import java.util.List;

@Data
public class LastTopicInfoResp {
    private Integer code;
    private List<LastTopicAbs> lastTopicAbsList;
}
