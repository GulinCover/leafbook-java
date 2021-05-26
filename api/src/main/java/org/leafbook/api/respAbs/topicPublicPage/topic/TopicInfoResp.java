package org.leafbook.api.respAbs.topicPublicPage.topic;

import lombok.Data;
import org.leafbook.api.respAbs.topicPublicPage.manager.TopicManagerAbs;

import java.util.List;

@Data
public class TopicInfoResp {
    private Integer code;
    private String topicTitle;
    private Long userId;
    private String userAvatar;
    private String username;
    private Integer userLevel;
    private Long updateTime;
    private Long articleAmount;
    private List<TopicEntryAbs> topicEntryAbsList;
    private Long lastArticleId;
    private String lastArticleTitle;
    private Long lastArticlePublicTime;
    private Long lastMainNumber;
    private Long lastBranchNumber;
    private List<TopicManagerAbs> managerAbsList;
}
