package org.leafbook.api.respAbs.repository.bidingPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class BidingAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long auctionId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long price;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long bidTime;
}
