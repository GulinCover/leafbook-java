package org.leafbook.api.modelApi.recordInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class BrowseHistoryModel extends Model {
    private Long historyModelId;
    private Long userId;
    private Long topicId;

}
