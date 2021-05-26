package org.leafbook.api.respAbs.userManagerPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class RepositoryManagerTopicAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicId;
    private String topicTitle;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long startAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long commentAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long contributorAmount;
}
