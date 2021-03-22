package org.leafbook.api.respAbs.indexPage;

import lombok.Data;

import java.util.List;

@Data
public class BrowseHistoryAbs {
    private Long topicId;
    private String topicTitle;
    private String topicDesc;
    private Long likeNumber;
    private List<EntryData> entryDataList;
}
