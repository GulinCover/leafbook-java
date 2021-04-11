package org.leafbook.api.respAbs.repository.overviewPage;

import lombok.Data;

import java.util.List;

@Data
public class LastCommentInfoResp {
    private Integer code;
    private List<LastTopicCommentAbs> lastTopicCommentAbsList;
}
