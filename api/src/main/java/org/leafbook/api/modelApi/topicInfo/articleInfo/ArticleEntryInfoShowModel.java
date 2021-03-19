package org.leafbook.api.modelApi.topicInfo.articleInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class ArticleEntryInfoShowModel extends Model {
    private Long articleEntryInfoShowModelId;
    private Long articleId;
    private Long entryId;
}
