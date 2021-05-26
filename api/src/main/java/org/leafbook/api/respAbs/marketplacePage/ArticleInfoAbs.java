package org.leafbook.api.respAbs.marketplacePage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class ArticleInfoAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long auctionId;
    private Integer articleType;//0:topic,1:nickname,2:renameCard

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;//上架用户id
    private String userAvatar;//上架用户头像

    private String nickname;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicId;
    private String topicTitle;
    private String topicDesc;
    private List<EntryAbs> entryAbsList;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long currentPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long bidPrice;
}
