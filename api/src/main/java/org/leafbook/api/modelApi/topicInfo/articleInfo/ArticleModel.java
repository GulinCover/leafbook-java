package org.leafbook.api.modelApi.topicInfo.articleInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class ArticleModel extends Model {
    private Long articleId;
    private Long userId;
    private Long topicId;
    private String articleTitle;
    private String articleDesc;
    private String articleContent;
    private Long mainNumber;
    private Long branchNumber;

    private Long nextArticleId;
    private Long preArticleId;
}
