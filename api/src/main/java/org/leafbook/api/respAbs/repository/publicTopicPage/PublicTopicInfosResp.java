package org.leafbook.api.respAbs.repository.publicTopicPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class PublicTopicInfosResp {
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long page;
    private List<PublicTopicAbs> publicTopicAbsList;
}
