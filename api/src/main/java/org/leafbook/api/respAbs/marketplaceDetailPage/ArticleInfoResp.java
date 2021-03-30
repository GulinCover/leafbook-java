package org.leafbook.api.respAbs.marketplaceDetailPage;

import lombok.Data;

import java.util.List;

@Data
public class ArticleInfoResp {
    private Integer code;
    private Long auctionId;
    private Long startPrice;

    private Long maxPrice;
    private Long maxPriceUserId;
    private String maxPriceUserUuid;

    private Long uploadTime;
    private Long bidTime;

    private Long sellerUserId;
    private String sellerUuid;
    private Integer sellerSex;
    private String sellerUserDesc;
    private Integer sellerUserLevel;
    private String sellerUserAvatar;

    private Integer type;//0:topic,1:nickname,2:renameCard
    private Long topicId;
    private String topicTitle;
    private String topicDesc;

    private String nickname;

    private List<ManagerAbs> managerAbsList;

    private List<EntryAbs> entryAbsList;

}
