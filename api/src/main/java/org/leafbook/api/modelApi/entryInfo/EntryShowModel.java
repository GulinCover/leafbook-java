package org.leafbook.api.modelApi.entryInfo;

import lombok.Data;

@Data
public class EntryShowModel extends EntryModel {
    private Long entryId;
    private Long userId;
    private String entryName;
    private String entryDesc;
    private String entryAvatar = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg";
    private String entryType;//类型:hot,official,nonofficial
    private Integer status;//审核状态:pending_review,under_review,review_completed,
}
