package org.leafbook.api.modelApi.talkInfo.commentInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class TalkComment2Model extends Model {
    private Long talkComment2Id;
    private Long userId;
    private Long talkComment1Id;
    private String talkCommentContent;
}
