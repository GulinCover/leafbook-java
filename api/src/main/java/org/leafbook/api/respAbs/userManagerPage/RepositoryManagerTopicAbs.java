package org.leafbook.api.respAbs.userManagerPage;

import lombok.Data;

@Data
public class RepositoryManagerTopicAbs {
    private Long topicId;
    private String topicTitle;
    private Long startAmount;
    private Long commentAmount;
    private Long contributorAmount;
}
