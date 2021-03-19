package org.leafbook.api.modelApi.topicInfo.articleInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class ArticleLikedAndTreadAndBrowseModel extends Model {
    private Long articleLikedAndTreadModelId;
    private Long likedAmount;//点赞量
    private Long treadAmount;//点踩量
    private Long browseAmount;//浏览量
}
