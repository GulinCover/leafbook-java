package org.leafbook.api.respAbs.marketplacePage;

import lombok.Data;

import java.util.List;

@Data
public class ArticleInfoAbs {
    private Long auctionId;
    private Integer articleType;//0:topic,1:nickname,2:renameCard

    private Long userId;//上架用户id
    private String userAvatar;//上架用户头像

    private String nickname;

    private Long topicId;
    private String topicTitle;
    private String topicDesc;
    private List<EntryAbs> entryAbsList;
    private Long currentPrice;
    private Long bidPrice;
}
