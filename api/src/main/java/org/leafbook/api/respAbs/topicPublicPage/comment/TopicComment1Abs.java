package org.leafbook.api.respAbs.topicPublicPage.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class TopicComment1Abs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long comment1Id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    private String userAvatar;
    private String username;
    private Integer userLevel;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long commentTime;
    private String commentContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long starAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long commentAmount;
    private Integer isStar;
    private Integer isTread;
    private List<TopicCommentEntryAbs> entryAbsList;
}
