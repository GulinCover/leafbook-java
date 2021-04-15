package org.leafbook.api.modelApi.commentInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class CommentModel extends Model {
    private Long talkId;
    private Long topicId;
    private String talkTitle;
    private String talkDesc;

    private Long comment1Id;
    private Long comment2Id;
    private Long talkComment1Id;
    private Long talkComment2Id;

    private Long userId;
    private Long commentedUserId;
    private String content;
}
