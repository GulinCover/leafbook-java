package org.leafbook.api.respAbs.repository.sellingPage;

import lombok.Data;

import java.util.List;

@Data
public class SellingInfoResp {
    private Integer code;
    private Long page;
    private List<SellingAbs> sellingAbsList;
}
