package org.leafbook.api.respAbs.repository.failedPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class FailedAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long auctionId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long bidTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long price;
}
