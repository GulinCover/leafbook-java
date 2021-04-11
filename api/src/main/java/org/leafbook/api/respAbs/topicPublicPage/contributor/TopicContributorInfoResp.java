package org.leafbook.api.respAbs.topicPublicPage.contributor;

import lombok.Data;

import java.util.List;

@Data
public class TopicContributorInfoResp {
    private Integer code;
    private Long page;
    private List<TopicContributorAbs> topicContributorAbsList;
}
