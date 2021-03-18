package org.leafbook.api.modelApi.topicInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class Topic2EntryModel extends Model {
    private Long topic2EntryModelId;
    private Long topicId;
    private Long entryId;

    private Long userId;
}
