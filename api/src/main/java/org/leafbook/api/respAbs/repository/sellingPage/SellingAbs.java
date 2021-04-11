package org.leafbook.api.respAbs.repository.sellingPage;

import lombok.Data;

@Data
public class SellingAbs {
    private Long auctionId;
    private Integer type;
    private Long publicTime;
    private Long expiredTime;
    private Long currentPrice;
    private Long startPrice;

    private String nickname;

    private Long topicId;
    private String topicTitle;
    private String topicDesc;
}
