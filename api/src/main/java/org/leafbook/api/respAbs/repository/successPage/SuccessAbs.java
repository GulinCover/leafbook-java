package org.leafbook.api.respAbs.repository.successPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class SuccessAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long billId;
    private Integer type;
    private String nickname;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long startPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long photographedTime;//拍下时间
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long photographedPrice;//拍下价格
}
