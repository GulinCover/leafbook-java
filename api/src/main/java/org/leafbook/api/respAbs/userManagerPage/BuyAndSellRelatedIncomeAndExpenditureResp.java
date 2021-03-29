package org.leafbook.api.respAbs.userManagerPage;

import lombok.Data;

import java.util.List;

@Data
public class BuyAndSellRelatedIncomeAndExpenditureResp {
    private Integer code;
    private List<BuyAndSellRelatedIncomeAndExpenditureAbs> buyAndSellRelatedIncomeAndExpenditureAbsList;
}
