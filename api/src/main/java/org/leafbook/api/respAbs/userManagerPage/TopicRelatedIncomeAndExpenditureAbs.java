package org.leafbook.api.respAbs.userManagerPage;

import lombok.Data;

@Data
public class TopicRelatedIncomeAndExpenditureAbs {
    private Long topicId;
    private String topicTitle;
    private Long price;
    private Integer position;
    private Long time;
    private Integer isMe;//是否是自己的著述 0:否,1:是
}
