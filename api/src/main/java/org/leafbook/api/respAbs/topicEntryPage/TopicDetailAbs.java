package org.leafbook.api.respAbs.topicEntryPage;

import lombok.Data;
import org.apache.maven.model.Contributor;

import java.util.List;

@Data
public class TopicDetailAbs {
    private Long topicId;
    private String topicTitle;

    private Long userId;
    private String username;

    private String likedNumber;
    private String updateTime;
    private List<EntryAbs> entryAbsList;

    private ContentAbs contentAbs;
    private CommentAbs commentAbs;
    private TalkAbs talkAbs;
    private ContributorAbs contributorAbs;
}
