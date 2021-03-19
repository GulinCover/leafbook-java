package org.leafbook.api.modelApi.topicInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class DirectoryModel extends Model {
    private Long directoryModelId;
    private Long topicId;
    private Long articleId;
    private Long pageNumber;
}
