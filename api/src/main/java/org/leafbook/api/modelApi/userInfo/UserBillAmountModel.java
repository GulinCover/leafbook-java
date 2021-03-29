package org.leafbook.api.modelApi.userInfo;

import lombok.Data;

@Data
public class UserBillAmountModel {
    private Long userBillAmountModelId;
    private Long userId;
    private Long starIncomeAmount;
    private Long starExpenditureAmount;

    private Long topicIncomeAmount;
    private Long topicExpenditureAmount;

    private Long buyAndSellIncomeAmount;
    private Long buyAndSellExpenditureAmount;

    private Long starIncomeAndExpenditureAmount;
    private Long topicIncomeAndExpenditureAmount;
    private Long buyAndSellIncomeAndExpenditureAmount;
}
