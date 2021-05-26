package org.leafbook.api.respAbs.repository.publicCommentPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class BillInfosResp {
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long page;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long balance;
    private List<BillAbs> billAbsList;
}
