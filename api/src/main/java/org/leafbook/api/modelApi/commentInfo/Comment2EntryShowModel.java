package org.leafbook.api.modelApi.commentInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class Comment2EntryShowModel extends Model {
    private Long Comment2EntryShowModelId;
    private Long userId;
    private Long entryId;

    private Long topicId;
    private Long talkId;
    private Long comment1Id;
    private Long talkComment1Id;
}
