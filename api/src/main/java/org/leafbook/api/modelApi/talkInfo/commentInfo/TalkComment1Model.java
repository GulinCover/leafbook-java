package org.leafbook.api.modelApi.talkInfo.commentInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class TalkComment1Model extends Model {
    private Long talkComment1Id;
    private Long userId;
    private Long talkId;
    private String talkCommentContent;
}
