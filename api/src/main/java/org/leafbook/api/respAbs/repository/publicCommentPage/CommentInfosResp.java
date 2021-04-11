package org.leafbook.api.respAbs.repository.publicCommentPage;

import lombok.Data;

import java.util.List;

@Data
public class CommentInfosResp {
    private Integer code;
    private Long page;
    private List<CommentInfoAbs> commentInfoAbsList;
}
