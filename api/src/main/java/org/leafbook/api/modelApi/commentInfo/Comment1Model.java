package org.leafbook.api.modelApi.commentInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class Comment1Model extends Model {
    private Long commentId;
    private Long userId;
    private Long topicId;
    private String commentContent;
}
