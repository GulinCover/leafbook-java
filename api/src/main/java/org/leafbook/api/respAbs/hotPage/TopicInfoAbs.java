package org.leafbook.api.respAbs.hotPage;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TopicInfoAbs {
    private Long topicId;
    private Long userId;
    private String topicTitle;
    private String userName;
    private String topicDesc;
    private List<EntryAbs> entryAbsList;
    private Long contributorNumber;
    private Long likeNumber;
    private Long publicTime;
}
