package org.leafbook.api.modelApi.userInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class UserBillModel extends Model {
    private Long userBillId;
    private Long userId;
    private Integer behavior;//行为 0:点赞,1:点踩,2:购买

    private Long price;
    private Integer position;//正负 0:正,1:负

    private Long billId;//购买的账单id
    private Long topicId;
    private Long commentId;
    private Long talkId;
    private Long talkCommentId;
}
