package org.leafbook.api.respAbs.topicPublicPage.comment;

import lombok.Data;

import java.util.List;

@Data
public class TopicComment2InfoResp {
    private Integer code;
    private Long page;
    private List<TopicComment2Abs> topicComment2AbsList;
}
