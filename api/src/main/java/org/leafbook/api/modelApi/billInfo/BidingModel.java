package org.leafbook.api.modelApi.billInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class BidingModel extends Model {
    private Long bidingId;
    private Long userId;
    private Long price;
    private Long auctionId;
    private Integer status;//0:正在竞拍,1:竞拍成功,2:竞拍失败
}
