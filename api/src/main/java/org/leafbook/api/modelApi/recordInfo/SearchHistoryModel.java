package org.leafbook.api.modelApi.recordInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class SearchHistoryModel extends Model {
    private Long searchHistoryModelId;
    private Long userId;
    private String content;
}
