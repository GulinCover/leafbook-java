package org.leafbook.api.respAbs.topicPage;

import lombok.Data;

import java.util.List;

@Data
public class RecommendedEntriesResp {
    private String code;
    private List<EntryAbs> entryAbsList;
}
