package org.leafbook.api.modelApi.entryInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class Entry2StarAndTreadAmountModel extends Model {
    private Long starAndTreadAmountModelId;
    private Long entryId;
    private Long entryStarAmount;
    private Long entryTreadAmount;
}
