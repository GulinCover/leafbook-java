package org.leafbook.api.respAbs.repository.overviewPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class LastCommentAbs {
    private String comment;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long publicTime;
}
