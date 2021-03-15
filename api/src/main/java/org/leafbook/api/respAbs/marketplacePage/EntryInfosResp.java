package org.leafbook.api.respAbs.marketplacePage;

import lombok.Data;

import java.util.List;

@Data
public class EntryInfosResp {
    private Integer code;
    private List<EntryAbs> entryAbsList;
}
