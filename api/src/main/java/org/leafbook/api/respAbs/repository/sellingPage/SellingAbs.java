package org.leafbook.api.respAbs.repository.sellingPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class SellingAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long auctionId;
    private Integer type;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long publicTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long expiredTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long currentPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long startPrice;

    private String nickname;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicId;
    private String topicTitle;
    private String topicDesc;
}
