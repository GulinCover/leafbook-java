package org.leafbook.api.respAbs.userManagerPage;

import lombok.Data;

import java.util.List;

@Data
public class TopicRelatedIncomeAndExpenditureResp {
    private Integer code;
    private List<TopicRelatedIncomeAndExpenditureAbs> topicRelatedIncomeAndExpenditureAbsList;
}
