package org.leafbook.api.respAbs.repository.repositoryPage;

import lombok.Data;

@Data
public class ConsumableInfoAbs {
    private Long consumableId;
    private String consumableUUID;
    private String type;
    private Long topicId;
    private String topicTitle;
    private String nickname;
    private String buyTime;
    private String buyPrice;
}
