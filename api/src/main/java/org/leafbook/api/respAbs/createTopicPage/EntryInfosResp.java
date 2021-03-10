package org.leafbook.api.respAbs.createTopicPage;

import lombok.Data;

import java.util.List;

@Data
public class EntryInfosResp {
    private String code;
    private List<EntryAbs> entryAbsList;
}
