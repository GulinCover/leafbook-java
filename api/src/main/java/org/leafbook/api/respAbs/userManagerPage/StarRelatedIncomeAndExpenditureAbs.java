package org.leafbook.api.respAbs.userManagerPage;

import lombok.Data;

@Data
public class StarRelatedIncomeAndExpenditureAbs {
    private Long resId;//点赞的对象id
    private String type;//点赞的类型 topic,comment,talk,talkComment
    private String name;//点赞的名称
    private Long time;//点赞的时间
    private Integer position;//正负
    private Long price;//花费
}
