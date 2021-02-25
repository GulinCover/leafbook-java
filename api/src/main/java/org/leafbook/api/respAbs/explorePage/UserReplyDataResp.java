package org.leafbook.api.respAbs.explorePage;

import lombok.Data;

@Data
public class UserReplyDataResp {
    private String code;
    private String username;
    private String UUID;
    private String avatar;
    private Integer topicReplyNumber;
    private Integer commentReplyNumber;
    private Integer talkReplyNumber;
}
