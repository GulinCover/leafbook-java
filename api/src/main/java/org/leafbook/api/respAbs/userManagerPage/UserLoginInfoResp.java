package org.leafbook.api.respAbs.userManagerPage;

import lombok.Data;

import java.util.List;

@Data
public class UserLoginInfoResp {
    private Integer code;
    private List<UserLoginAbs> userLoginAbsList;
}
