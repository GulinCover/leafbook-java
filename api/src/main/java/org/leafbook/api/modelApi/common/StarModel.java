package org.leafbook.api.modelApi.common;

import lombok.Data;

@Data
public class StarModel extends Model {
    private Long starId;
    private Long userId;
    private Long topicId;
    private Long commentId;
    private Long talkCommentId;
    private Long talkId;
    private Long entryId;
    private Long likedUserId;
    private String starType;
}
