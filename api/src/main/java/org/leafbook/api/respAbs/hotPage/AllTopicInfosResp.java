package org.leafbook.api.respAbs.hotPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class AllTopicInfosResp {
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long page;
    private List<TopicInfoAbs> topicInfoAbsList;
}
