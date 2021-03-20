package org.leafbook.api.modelApi.commentInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class Comment1InfoEntryShowModel extends Model {
    private Long comment1InfoEntryShowModelId;
    private Long comment1Id;
    private Long entryId;
}
