package org.leafbook.api.respAbs.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OtherUserTopicAbs implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicId;
    private String topicTitle;
    private String topicDesc;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long publicTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long starAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long browseAmount;
    private List<OtherEntryAbs> entryAbsList;
}
