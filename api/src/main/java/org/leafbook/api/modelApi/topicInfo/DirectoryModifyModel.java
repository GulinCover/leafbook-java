package org.leafbook.api.modelApi.topicInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class DirectoryModifyModel extends Model {
    private Long directoryModifyModelId;
    private Long userId;
    private Long page;
    private Long srcArticleId;
    private Long distArticleId;
}
