package org.leafbook.api.respAbs.topicEntryPage;

import lombok.Data;
import org.leafbook.api.respAbs.common.EntryAbs;

import java.util.List;

@Data
public class EntryInfosResp {
    private Integer code;
    private List<EntryAbs> entryAbsList;
}
