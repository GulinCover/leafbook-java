package org.leafbook.api.modelApi.userInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class AttentionModel extends Model {
    private Long attentionModelId;
    private Long userId;
    private Long attentionUserId;
    private Long lastViewTimestamp;//根据此时间戳查找未浏览的消息
}
