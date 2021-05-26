package org.leafbook.api.respAbs.marketplacePage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class SearchArticleInfosResp {
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long page;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long entryId;
    private String entryName;
    private String entryDesc;
    private List<ArticleInfoAbs> articleInfoAbsList;
}
