package org.leafbook.api.respAbs.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoResp implements Serializable {
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    private String UUID;
    private String username;
    private String userAvatar;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long balance;
    private Integer sex;//0:男,1:女
    private Integer userLevel;
    private String location;
    private String userDesc;
}
