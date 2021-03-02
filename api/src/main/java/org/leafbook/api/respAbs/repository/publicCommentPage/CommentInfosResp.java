package org.leafbook.api.respAbs.repository.publicCommentPage;

import lombok.Data;

import java.util.List;

@Data
public class CommentInfosResp {
    private String code;
    private List<CommentInfoAbs> commentInfoAbsList;
}
