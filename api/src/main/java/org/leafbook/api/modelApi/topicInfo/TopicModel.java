package org.leafbook.api.modelApi.topicInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class TopicModel extends Model {
    private Long topicId;
    private String topicTitle;
    private String topicDesc;
    private String topicAvatar = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg";

    private Integer status;//0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用

    private Integer type;//1:小说,2:漫画,3:音乐
    private Long userId;
}
