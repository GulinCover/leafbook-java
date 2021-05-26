package org.leafbook.api.respAbs.topicPublicPage.topic;

import lombok.Data;

import java.util.List;

@Data
public class MainInfosResp {
    private Integer code;
    private Long page;
    private List<MainAbs> mainAbsList;
}
