package org.leafbook.api.modelApi.topicInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class ContributorModel extends Model {
    private Long contributorModelId;
    private Long userId;
    private Long topicId;
}
