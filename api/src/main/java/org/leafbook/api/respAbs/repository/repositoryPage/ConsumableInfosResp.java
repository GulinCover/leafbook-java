package org.leafbook.api.respAbs.repository.repositoryPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class ConsumableInfosResp {
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long page;
    private List<ConsumableInfoAbs> consumableInfoAbsList;
}
