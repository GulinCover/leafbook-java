package org.leafbook.api.respAbs.repository.publicCommentPage;

import lombok.Data;

@Data
public class BillAbs {
    private String billUUID;
    private String sellingTime;
    private String sellerUUID;
    private String buyTime;
    private String buyerUUID;
    private String getPrice;
    private String positive;

    private String type;
    private String topicTitle;
    private Long topicId;
    private String nickname;
}
