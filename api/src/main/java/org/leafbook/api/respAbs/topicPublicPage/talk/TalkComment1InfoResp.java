package org.leafbook.api.respAbs.topicPublicPage.talk;

import lombok.Data;

import java.util.List;

@Data
public class TalkComment1InfoResp {
    private Integer code;
    private Long page;
    private Long talkId;
    private String talkTitle;
    private String talkDesc;
    private List<TalkInfoEntryAbs> entryAbsList;
    private Long userId;
    private Long publicTime;
    private List<TalkComment1Abs> talkComment1AbsList;

}
