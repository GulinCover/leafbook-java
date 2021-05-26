package org.leafbook.api.respAbs.topicPublicPage.comment;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TopicCommentPageResp implements Serializable {
    private Integer code;
    private List<TopicComment1Abs> topicComment1AbsList;
}
