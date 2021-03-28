package org.leafbook.api.respAbs.userManagerPage;

import lombok.Data;

import java.util.List;

@Data
public class RepositoryManagerTopicResp {
    private Integer code;
    private List<RepositoryManagerTopicAbs> repositoryManagerTopicAbsList;
}
