package org.leafbook.api.respAbs.repository.sellingPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class SellingInfoResp {
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long page;
    private List<SellingAbs> sellingAbsList;
}
