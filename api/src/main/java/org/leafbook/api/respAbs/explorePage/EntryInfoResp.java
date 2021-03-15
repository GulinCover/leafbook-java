package org.leafbook.api.respAbs.explorePage;

import lombok.Data;

import java.util.List;

@Data
public class EntryInfoResp {
    private Integer code;
    private String entryName;
    private String isLeft;
    private Long entryId;
    private String entryDesc;
    private String entryAvatar;
    private List<EntryAbs> entryAbsList;
}
