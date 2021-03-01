package org.leafbook.api.respAbs.common;

import lombok.Data;

@Data
public class UserInfoResp {
    private String code;
    private Long userId;
    private String UUID;
    private String username;
    private String userAvatar;
    private String sex;
    private String userLevel;
    private String location;
    private String userDesc;
}
