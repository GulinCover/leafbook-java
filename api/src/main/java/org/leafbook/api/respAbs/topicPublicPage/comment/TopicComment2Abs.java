package org.leafbook.api.respAbs.topicPublicPage.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class TopicComment2Abs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long comment2Id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    private String userAvatar;
    private String username;
    private Integer userLevel;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long commentTime;
    private String commentContent;
}
