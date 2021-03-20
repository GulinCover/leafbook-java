package org.leafbook.api.modelApi.talkInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class Talk2StarAndTreadModel extends Model {
    private Long talk2StarAndTreadModelId;
    private Long talkId;
    private Long starAmount;
    private Long treadAmount;
}
