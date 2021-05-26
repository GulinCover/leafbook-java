package org.leafbook.api.respAbs.repository.bidingPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class BidingInfoResp {
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long page;
    private List<BidingAbs> bidingAbsList;
}
