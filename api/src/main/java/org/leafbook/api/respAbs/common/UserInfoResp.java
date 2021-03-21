package org.leafbook.api.respAbs.common;

import lombok.Data;

@Data
public class UserInfoResp {
    private Integer code;
    private Long userId;
    private String UUID;
    private String username;
    private String userAvatar;
    private Long balance;
    private Integer sex;//0:男,1:女
    private Integer userLevel;
    private String location;
    private String userDesc;
}
