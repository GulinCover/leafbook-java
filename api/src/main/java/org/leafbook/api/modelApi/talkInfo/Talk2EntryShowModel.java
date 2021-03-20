package org.leafbook.api.modelApi.talkInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class Talk2EntryShowModel extends Model {
    private Long talk2EntryShowModelId;
    private Long userId;
    private Long talkId;
    private Long entryId;
}
