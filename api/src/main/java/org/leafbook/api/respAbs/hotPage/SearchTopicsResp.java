package org.leafbook.api.respAbs.hotPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.leafbook.api.annotation.RemainingProblem;
import org.leafbook.api.respAbs.topicSearch.SearchEntryAbs;

import java.util.List;

@Data
public class SearchTopicsResp {
    @RemainingProblem
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long maxPage;
    @RemainingProblem
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long maxAmount;
    @RemainingProblem
    private List<SearchEntryAbs> searchEntryAbsList;

    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long page;
    private List<TopicInfoAbs> topicInfoAbsList;

}
