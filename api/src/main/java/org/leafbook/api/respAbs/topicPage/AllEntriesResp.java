package org.leafbook.api.respAbs.topicPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class AllEntriesResp extends RecommendedEntriesResp {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long page;
}
