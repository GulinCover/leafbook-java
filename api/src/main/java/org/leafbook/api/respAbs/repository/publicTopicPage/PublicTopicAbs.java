package org.leafbook.api.respAbs.repository.publicTopicPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class PublicTopicAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicId;
    private String topicTitle;
    private String topicDesc;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long updateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long likeNumber;
    private List<EntryAbs> entryAbsList;
}
