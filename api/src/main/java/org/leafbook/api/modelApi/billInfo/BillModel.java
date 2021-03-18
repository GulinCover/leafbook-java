package org.leafbook.api.modelApi.billInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class BillModel extends Model {
    private Long billId;
    private Long price;

    private Long sellerId;
    private String sellerUuid;

    private Long buyerId;
    private String buyerUuid;

    private Long resId;//物品id
}
