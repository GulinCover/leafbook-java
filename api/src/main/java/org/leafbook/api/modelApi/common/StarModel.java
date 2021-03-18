package org.leafbook.api.modelApi.common;

import lombok.Data;

@Data
public class StarModel {
    private Long starId;
    private Long userId;
    private Long topicId;
    private Long commentId;
    private Long talkCommentId;
    private Long entryId;
    private Long likedUserId;
    private String type;
}
