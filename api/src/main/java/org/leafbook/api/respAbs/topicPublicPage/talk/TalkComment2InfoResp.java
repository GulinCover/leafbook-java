package org.leafbook.api.respAbs.topicPublicPage.talk;

import lombok.Data;

import java.util.List;

@Data
public class TalkComment2InfoResp {
    private Integer code;
    private Long page;
    private List<TalkComment2Abs> talkComment2AbsList;
}
