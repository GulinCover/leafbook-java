package org.leafbook.api.respAbs.topicPublicPage.topic;

import lombok.Data;

import java.util.List;

@Data
public class BranchInfosResp {
    private Integer code;
    private List<BranchAbs> branchAbsList;
}
