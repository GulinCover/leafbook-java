package org.leafbook.api.respAbs.topicEntryPage;

import lombok.Data;

@Data
public class EntryInfoResp {
    private Integer code;
    private Long entryId;
    private String entryName;
    private String entryDesc;
    private String entryAvatar;
    private String likedNumber;
    private String passTime;
    private String applicant;
    private Long entryCreatorId;
    private String relatedTopicNumber;
}
