package org.leafbook.api.modelApi.billInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class ResModel extends Model {
    private Long resId;
    private Integer type;//0:topic,1:nickname,2:renameCard
    private Long userId;
    private Long topicId;
    private String nickname;
    private Long price;

}
