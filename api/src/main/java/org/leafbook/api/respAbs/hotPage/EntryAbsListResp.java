package org.leafbook.api.respAbs.hotPage;

import lombok.Data;

import java.util.List;

@Data
public class EntryAbsListResp {
    private String code;
    private List<EntryAbs> entryAbsList;
}
