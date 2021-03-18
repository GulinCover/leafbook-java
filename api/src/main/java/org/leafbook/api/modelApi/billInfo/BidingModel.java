package org.leafbook.api.modelApi.billInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class BidingModel extends Model {
    private Long bidingId;
    private Long userId;
    private Long price;
    private Long auctionId;
}
