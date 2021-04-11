package org.leafbook.api.respAbs.repository.bidingPage;

import lombok.Data;

@Data
public class BidingAbs {
    private Long auctionId;
    private Long price;
    private Long bidTime;
}
