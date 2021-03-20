package org.leafbook.api.modelApi.talkInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class TalkModel extends Model {
    private Long talkId;
    private Long topicId;
    private Long userId;
    private String talkTitle;
    private String talkDesc;
}
