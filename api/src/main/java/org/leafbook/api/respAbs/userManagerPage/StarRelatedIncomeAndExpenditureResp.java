package org.leafbook.api.respAbs.userManagerPage;

import lombok.Data;

import java.util.List;

@Data
public class StarRelatedIncomeAndExpenditureResp {
    private Integer code;
    private Long page;
    private List<StarRelatedIncomeAndExpenditureAbs> starRelatedIncomeAndExpenditureAbsList;
}
