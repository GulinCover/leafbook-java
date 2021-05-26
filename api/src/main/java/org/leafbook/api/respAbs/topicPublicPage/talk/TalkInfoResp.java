package org.leafbook.api.respAbs.topicPublicPage.talk;

import lombok.Data;

import java.util.List;

@Data
public class TalkInfoResp {
    private Integer code;
    private Long page;
    private Long totalCommentNumber;
    private List<TalkInfoAbs> talkInfoAbsList;
}
