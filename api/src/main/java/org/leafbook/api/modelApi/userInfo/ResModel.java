package org.leafbook.api.modelApi.userInfo;

import lombok.Data;
import org.leafbook.api.annotation.RemainingProblem;
import org.leafbook.api.modelApi.common.Model;

@Data
public class ResModel extends Model {
    private Long resId;//0:renameCardId
    private Long billId;
    private Integer resType;//0:topic,1:nickname,2:renameCard
    private Long userId;
    private Long topicId;
    private String nickname;
    private Long price;
    @RemainingProblem
    private Integer status;//0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用

}
