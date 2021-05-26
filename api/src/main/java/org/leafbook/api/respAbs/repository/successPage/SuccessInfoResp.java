package org.leafbook.api.respAbs.repository.successPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class SuccessInfoResp {
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long page;
    private List<SuccessAbs> successAbsList;
}
