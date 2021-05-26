package org.leafbook.api.respAbs.topicEntryPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class TopicInfosResp {
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long page;
    private List<TopicDetailAbs> topicDetailAbsList;
}
