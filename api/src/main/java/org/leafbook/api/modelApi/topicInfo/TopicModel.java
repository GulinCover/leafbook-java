package org.leafbook.api.modelApi.topicInfo;

import lombok.Data;

@Data
public class TopicModel {
    private Long topicId;
    private String topicTitle;
    private String topicDesc;
    private String topicAvatar = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg";

    private Integer type;//1:小说,2:漫画,3:音乐
    private Long userId;
}
