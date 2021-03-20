package org.leafbook.api.modelApi.talkInfo.commentInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class TalkComment1Info2EntryInfoModel extends Model {
    private Long talkComment1Info2EntryInfoModelId;
    private Long userId;
    private Long talkComment1Id;
    private Long entryId;
}
