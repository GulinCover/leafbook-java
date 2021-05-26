package org.leafbook.api.respAbs.topicEntryPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class UserInfoAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    private String username;
    private String userAvatar;
    private String uuid;
}
