package org.leafbook.api.respAbs.repository.repositoryPage;

import lombok.Data;

import java.util.List;

@Data
public class ConsumableInfosResp {
    private String code;
    private List<ConsumableInfoAbs> consumableInfoAbsList;
}
