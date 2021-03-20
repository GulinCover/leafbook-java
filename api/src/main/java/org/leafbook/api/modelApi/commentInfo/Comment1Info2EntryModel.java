package org.leafbook.api.modelApi.commentInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class Comment1Info2EntryModel extends Model {
    private Long comment1Info2EntryModelId;
    private Long comment1Id;
    private Long userId;
    private Long entryId;
}
