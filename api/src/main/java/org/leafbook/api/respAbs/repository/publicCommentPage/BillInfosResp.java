package org.leafbook.api.respAbs.repository.publicCommentPage;

import lombok.Data;

import java.util.List;

@Data
public class BillInfosResp {
    private Integer code;
    private String balance;
    private List<BillAbs> billAbsList;
}
