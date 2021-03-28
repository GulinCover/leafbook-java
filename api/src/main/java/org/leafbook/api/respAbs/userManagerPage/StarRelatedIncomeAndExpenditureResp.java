package org.leafbook.api.respAbs.userManagerPage;

import lombok.Data;

import java.util.List;

@Data
public class StarRelatedIncomeAndExpenditureResp {
    private Integer code;
    private List<StarRelatedIncomeAndExpenditureAbs> starRelatedIncomeAndExpenditureAbsList;
}
