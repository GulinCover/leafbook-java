package org.leafbook.api.respAbs.topicEntryPage;

import lombok.Data;

@Data
public class EntryInfoResp {
    private Integer code;
    private Long entryId;
    private String entryName;
    private String entryDesc;
    private String entryAvatar;
    private Long likedNumber;
    private Long passTime;
    private String applicant;
    private Long entryCreatorId;
    private Long relatedTopicNumber;
}
