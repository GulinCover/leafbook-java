package org.leafbook.api.respAbs.marketplaceDetailPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class ArticleInfoResp {
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long auctionId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long startPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long maxPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long maxPriceUserId;
    private String maxPriceUserUuid;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long uploadTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long bidTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long sellerUserId;
    private String sellerUuid;
    private Integer sellerSex;
    private String sellerUserDesc;
    private Integer sellerUserLevel;
    private String sellerUserAvatar;

    private Integer type;//0:topic,1:nickname,2:renameCard
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long topicId;
    private String topicTitle;
    private String topicDesc;

    private String nickname;

    private List<ManagerAbs> managerAbsList;

    private List<EntryAbs> entryAbsList;

}
