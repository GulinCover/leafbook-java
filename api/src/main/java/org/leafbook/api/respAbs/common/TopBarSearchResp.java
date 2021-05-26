package org.leafbook.api.respAbs.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TopBarSearchResp implements Serializable {
    private Integer code;
    private List<SearchHistoryAbs> searchHistories;
}
