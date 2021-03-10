package org.leafbook.api.respAbs.topicEntryPage;

import lombok.Data;

import java.util.List;

@Data
public class ContentAbs {
    private String topicDesc;
    private List<EntryAbs> entryAbsList;
}
