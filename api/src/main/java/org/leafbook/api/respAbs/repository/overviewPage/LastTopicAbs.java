package org.leafbook.api.respAbs.repository.overviewPage;

import lombok.Data;

import java.util.List;

@Data
public class LastTopicAbs {
    private Long topicId;
    private String topicTitle;
    private String topicDesc;
    private List<EntryAbs> entryAbsList;
    private Long publicTime;
}
