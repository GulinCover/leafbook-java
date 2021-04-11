package org.leafbook.api.respAbs.repository.publicCommentPage;

import lombok.Data;

import java.util.List;

@Data
public class BillInfosResp {
    private Integer code;
    private Long page;
    private Long balance;
    private List<BillAbs> billAbsList;
}
