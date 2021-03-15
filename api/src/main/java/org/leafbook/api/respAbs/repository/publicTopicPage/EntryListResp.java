package org.leafbook.api.respAbs.repository.publicTopicPage;

import lombok.Data;

import java.util.List;

@Data
public class EntryListResp {
    private Integer code;
    private List<EntryAbs> entryAbsList;
}
