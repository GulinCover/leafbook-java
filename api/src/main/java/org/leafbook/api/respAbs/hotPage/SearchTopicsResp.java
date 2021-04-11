package org.leafbook.api.respAbs.hotPage;

import lombok.Data;
import org.leafbook.api.respAbs.topicSearch.SearchEntryAbs;

import java.util.List;

@Data
public class SearchTopicsResp {
    private Integer code;
    private Long maxPage;
    private Long maxAmount;
    private List<TopicInfoAbs> topicInfoAbsList;
    private List<SearchEntryAbs> searchEntryAbsList;
}
