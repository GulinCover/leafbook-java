package org.leafbook.api.respAbs.marketplaceDetailPage;

import lombok.Data;

import java.util.List;

@Data
public class ArticleInfoResp {
    private String code;
    private Long articleId;
    private String articleUUID;
    private String startPrice;
    private String maxPrice;
    private String bidUUID;
    private String bidName;

    private String uploadTime;
    private String bidTime;

    private SellerAbs sellerAbs;

    private String type;
    private String topicTitle;
    private String nickname;

    private String topicDesc;
    private List<ManagerAbs> managerAbsList;

    private List<EntryAbs> entryAbsList;

}
