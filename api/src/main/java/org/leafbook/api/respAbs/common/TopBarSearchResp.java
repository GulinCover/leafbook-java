package org.leafbook.api.respAbs.common;

import lombok.Data;

import java.util.List;

@Data
public class TopBarSearchResp {
    private Integer code;
    private List<SearchHistoryAbs> searchHistories;
}
