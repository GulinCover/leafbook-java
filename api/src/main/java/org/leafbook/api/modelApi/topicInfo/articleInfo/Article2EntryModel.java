package org.leafbook.api.modelApi.topicInfo.articleInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class Article2EntryModel extends Model {
    private Long article2EntryModelId;
    private Long articleId;
    private Long entryId;
}
