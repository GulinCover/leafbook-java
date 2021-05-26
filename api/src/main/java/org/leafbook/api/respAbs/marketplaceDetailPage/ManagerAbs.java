package org.leafbook.api.respAbs.marketplaceDetailPage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ManagerAbs {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    private String userAvatar;
    private String username;
    private Integer userLevel;
    private String sex;
}
