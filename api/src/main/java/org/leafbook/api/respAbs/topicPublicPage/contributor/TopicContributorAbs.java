package org.leafbook.api.respAbs.topicPublicPage.contributor;

import lombok.Data;

@Data
public class TopicContributorAbs {
    private Long userId;
    private String uuid;
    private String username;
    private String userDesc;
    private String userAvatar;
    private Integer isAttention;
}
