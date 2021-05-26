package org.leafbook.api.respAbs.repository.publicCommentPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class BillAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long billId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long sellingTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long buyTime;

    private String sellerUUID;

    private String buyerUUID;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long getPrice;
    private Integer positive;//0:负,1:正

    private Integer type;//0:topic,1:nickname

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicId;
    private String topicTitle;
    private String nickname;
}
