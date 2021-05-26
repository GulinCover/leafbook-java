package org.leafbook.api.respAbs.repository.repositoryPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ConsumableInfoAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long resId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long billId;
    private Integer type;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicId;
    private String topicTitle;
    private String nickname;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long buyTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long buyPrice;
}
