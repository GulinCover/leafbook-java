package org.leafbook.api.respAbs.repository.failedPage;

import lombok.Data;

import java.util.List;

@Data
public class FailedInfoResp {
    private Integer code;
    private Long page;
    private List<FailedAbs> failedAbsList;
}
