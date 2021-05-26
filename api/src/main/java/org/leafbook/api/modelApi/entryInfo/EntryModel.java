package org.leafbook.api.modelApi.entryInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class EntryModel extends Model {
    private Long entryId;
    private Long userId;
    private String entryName;
    private String entryDesc;
    private String entryAvatar = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg";
    private String entryType;//类型:hot,official,nonofficial
    private Integer status;//审核状态 0:pending_review,1:under_review,2:review_completed,
}
