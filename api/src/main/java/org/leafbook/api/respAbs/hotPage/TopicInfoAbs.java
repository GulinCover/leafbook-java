package org.leafbook.api.respAbs.hotPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TopicInfoAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    private String topicTitle;
    private String userName;
    private String topicDesc;
    private List<EntryAbs> entryAbsList;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long contributorNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long likeNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long publicTime;
}
