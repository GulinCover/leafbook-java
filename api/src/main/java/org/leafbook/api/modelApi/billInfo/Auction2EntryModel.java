package org.leafbook.api.modelApi.billInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class Auction2EntryModel extends Model {
    private Long auction2EntryModelId;
    private Long auctionId;
    private Long entryId;
}
