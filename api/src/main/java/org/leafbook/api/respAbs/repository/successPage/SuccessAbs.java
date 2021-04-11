package org.leafbook.api.respAbs.repository.successPage;

import lombok.Data;

@Data
public class SuccessAbs {
    private Long billId;
    private Integer type;
    private String nickname;
    private Long topicId;

    private Long startPrice;
    private Long photographedTime;//拍下时间
    private Long photographedPrice;//拍下价格
}
