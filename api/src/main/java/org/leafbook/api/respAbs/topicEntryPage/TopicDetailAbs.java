package org.leafbook.api.respAbs.topicEntryPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.maven.model.Contributor;

import java.util.List;

@Data
public class TopicDetailAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicId;
    private String topicTitle;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    private String username;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long likedNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long updateTime;
    private List<EntryAbs> entryAbsList;

    private ContentAbs contentAbs;
    private CommentAbs commentAbs;
    private TalkAbs talkAbs;
    private ContributorAbs contributorAbs;
}
