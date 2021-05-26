package org.leafbook.api.modelApi.commentInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class CommentModel extends Model {
    private Long topicId;

    private Long talkId;
    private String talkTitle;
    private String talkDesc;

    private Long comment1Id;
    private Long comment2Id;
    private Long talkComment1Id;
    private Long talkComment2Id;

    private Long userId;
    private Long commentedUserId;
    private String content;

    private Integer commentType;//二级议论评论的类型:0:赞同,1:反对,2:中立
}
