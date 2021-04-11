package org.leafbook.api.respAbs.repository.publicTopicPage;

import lombok.Data;

import java.util.List;

@Data
public class PublicTopicInfosResp {
    private Integer code;
    private Long page;
    private List<PublicTopicAbs> publicTopicAbsList;
}
