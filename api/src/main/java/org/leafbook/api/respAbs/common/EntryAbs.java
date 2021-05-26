package org.leafbook.api.respAbs.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class EntryAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long entryId;
    private String entryName;
    private String entryDesc;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long likeNumber;
    private String entryAvatar;
    private Integer isLiked;//0没点赞,1点过赞
}
