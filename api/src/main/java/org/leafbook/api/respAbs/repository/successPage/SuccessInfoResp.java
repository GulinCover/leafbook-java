package org.leafbook.api.respAbs.repository.successPage;

import lombok.Data;

import java.util.List;

@Data
public class SuccessInfoResp {
    private Integer code;
    private Long page;
    private List<SuccessAbs> successAbsList;
}
