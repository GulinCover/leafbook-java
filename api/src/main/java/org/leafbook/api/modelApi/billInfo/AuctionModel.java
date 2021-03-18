package org.leafbook.api.modelApi.billInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class AuctionModel extends Model {
    private Long auctionId;

    private Integer type;//0:topic,1:nickname
    private Long topicId;
    private String nickname;

    private Long userId;
    private Long startPrice;
    private Long currentPrice;
    private Long expireTimestamp;
}
