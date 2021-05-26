package org.leafbook.api.respAbs.topicPublicPage.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TopicComment2InfoResp implements Serializable {
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long page;
    private List<TopicComment2Abs> topicComment2AbsList;
}
