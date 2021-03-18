package org.leafbook.api.modelApi.topicInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class TopicEntryInfoShowModel extends Model {
    private Long topicEntryInfoShowModelId;
    private Long topicId;
    private Long entryId;
}
