package org.leafbook.api.respAbs.topicSearch;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class SearchEntryAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long entryId;
    private String entryName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicAmount;
}
