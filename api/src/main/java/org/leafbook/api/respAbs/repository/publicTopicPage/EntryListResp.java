package org.leafbook.api.respAbs.repository.publicTopicPage;

import lombok.Data;

import java.util.List;

@Data
public class EntryListResp {
    private String code;
    private List<EntryAbs> entryAbsList;
}
