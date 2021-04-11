package org.leafbook.api.respAbs.repository.publicTopicPage;

import lombok.Data;

import java.util.List;

@Data
public class PublicTopicAbs {
    private Long topicId;
    private String topicTitle;
    private String topicDesc;
    private Long updateTime;
    private Long likeNumber;
    private List<EntryAbs> entryAbsList;
}
