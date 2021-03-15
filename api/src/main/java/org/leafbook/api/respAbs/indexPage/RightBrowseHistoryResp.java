package org.leafbook.api.respAbs.indexPage;

import lombok.Data;

import java.util.List;

@Data
public class RightBrowseHistoryResp {
    private Integer code;
    private List<BrowseHistoryAbs> browseHistoryAbsList;
}
