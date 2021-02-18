package org.leafbook.api.respAbs.indexPage;

import lombok.Data;

import java.util.List;

@Data
public class RightBrowseHistoryResp {
    private String code;
    private List<BrowseHistoryAbs> browseHistoryAbsList;
}
