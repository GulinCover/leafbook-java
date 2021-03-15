package org.leafbook.api.respAbs.explorePage;

import lombok.Data;

@Data
public class UserReplyDataResp {
    private Integer code;
    private String username;
    private String UUID;
    private String userAvatar;
    private Integer topicReplyNumber;
    private Integer commentReplyNumber;
    private Integer talkReplyNumber;
}
