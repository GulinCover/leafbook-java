package org.leafbook.api.respAbs.marketplacePage;

import lombok.Data;

import java.util.List;

@Data
public class ArticleInfoAbs {
    private Long articleId;
    private String articleType;
    private Long nicknameId;
    private Long topicId;
    private String nickname;
    private String topicTitle;
    private String topicDesc;
    private List<EntryAbs> entryAbsList;
    private String userAvatar;
    private String price;
    private String bidPrice;
}
