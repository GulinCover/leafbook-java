package org.leafbook.api.respAbs.repository.repositoryPage;

import lombok.Data;

@Data
public class ConsumableInfoAbs {
    private Long resId;
    private Long billId;
    private Integer type;
    private Long topicId;
    private String topicTitle;
    private String nickname;
    private Long buyTime;
    private Long buyPrice;
}
