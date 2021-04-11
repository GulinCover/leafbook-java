package org.leafbook.api.respAbs.repository.bidingPage;

import lombok.Data;

import java.util.List;

@Data
public class BidingInfoResp {
    private Integer code;
    private Long page;
    private List<BidingAbs> bidingAbsList;
}
