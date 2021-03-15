package org.leafbook.api.respAbs.topicPage;

import lombok.Data;

import java.util.List;

@Data
public class RecommendedEntriesResp {
    private Integer code;
    private List<EntryAbs> entryAbsList;
}
