package org.leafbook.api.respAbs.topicPage;

import lombok.Data;

@Data
public class EntryAbs {
    private Long entryId;
    private String entryName;
    private String entryDesc;
    private Long likeNumber;
    private String entryAvatar;
    private Integer isLiked;
}
