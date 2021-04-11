package org.leafbook.api.modelApi.commentInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class CommentStarAndTreadModel extends Model {
    private Long comment2StarAndTreadModelId;
    private Long comment1Id;
    private Long talkId;
    private Long talkComment1Id;
    private Long starAmount;
    private Long treadAmount;
}
