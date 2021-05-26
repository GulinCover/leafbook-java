package org.leafbook.api.respAbs.topicPublicPage.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class TopicComment1InfoResp implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long commentTotalAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long page;
    private Integer code;
}
