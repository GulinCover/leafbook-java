package org.leafbook.api.respAbs.common;

import lombok.Data;
import org.leafbook.api.respAbs.indexPage.TopicAbs;

import java.util.List;

@Data
public class SearchDataResp {
    private String code;
    private List<TopicAbs> topicAbsList;
}
