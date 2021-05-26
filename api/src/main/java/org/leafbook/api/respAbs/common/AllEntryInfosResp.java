package org.leafbook.api.respAbs.common;

import lombok.Data;

import java.util.List;

@Data
public class AllEntryInfosResp {
    private Integer code;
    private List<CommonEntryAbs> entryAbsList;
}
