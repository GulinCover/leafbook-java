package org.leafbook.api.respAbs.topicPublicPage.manager;

import lombok.Data;

@Data
public class TopicManagerAbs {
    private Long userId;
    private String uuid;
    private String userAvatar;
    private String userDesc;
    private String username;
    private Integer isAttention;
}
