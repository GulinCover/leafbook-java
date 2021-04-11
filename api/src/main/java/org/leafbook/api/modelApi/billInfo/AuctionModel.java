package org.leafbook.api.modelApi.billInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class AuctionModel extends Model {
    private Long auctionId;

    private Long currentPriceUserId;//当前竞价者id
    private String currentPriceUserUuid;//当前竞价者uuid

    private Integer type;//0:topic,1:nickname,2:renameCard
    private Long topicId;
    private String nickname;

    private Integer status;//0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用

    private Long userId;
    private Long startPrice;
    private Long currentPrice;
    private Long expireTimestamp;
    private Long publicTimestamp;
}
