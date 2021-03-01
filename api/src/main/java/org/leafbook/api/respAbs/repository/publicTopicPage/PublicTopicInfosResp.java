package org.leafbook.api.respAbs.repository.publicTopicPage;

import lombok.Data;

import java.util.List;

@Data
public class PublicTopicInfosResp {
    private String code;
    private List<PublicTopicAbs> publicTopicAbsList;
}
