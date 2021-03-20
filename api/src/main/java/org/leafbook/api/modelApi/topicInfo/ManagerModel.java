package org.leafbook.api.modelApi.topicInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class ManagerModel extends Model {
    private Long managerModelId;
    private Long userId;
    private Long topicId;
}
