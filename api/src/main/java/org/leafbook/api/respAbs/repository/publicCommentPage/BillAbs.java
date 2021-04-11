package org.leafbook.api.respAbs.repository.publicCommentPage;

import lombok.Data;

@Data
public class BillAbs {
    private Long billId;

    private Long sellingTime;
    private Long buyTime;

    private String sellerUUID;

    private String buyerUUID;

    private Long getPrice;
    private Integer positive;//0:负,1:正

    private Integer type;//0:topic,1:nickname
    private Long topicId;
    private String topicTitle;
    private String nickname;
}
