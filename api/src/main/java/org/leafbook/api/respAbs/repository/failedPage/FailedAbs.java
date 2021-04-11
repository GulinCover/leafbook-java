package org.leafbook.api.respAbs.repository.failedPage;

import lombok.Data;

@Data
public class FailedAbs {
    private Long auctionId;
    private Long bidTime;
    private Long price;
}
