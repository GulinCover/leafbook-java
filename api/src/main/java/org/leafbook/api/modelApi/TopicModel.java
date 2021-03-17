package org.leafbook.api.modelApi;

import lombok.Data;

@Data
public class TopicModel {
    private Long topicId;
    private String topicName;
    private String topicDesc;

    private Long likedNumber;//点赞数量
    private Long treadNumber;//点踩数量
}
