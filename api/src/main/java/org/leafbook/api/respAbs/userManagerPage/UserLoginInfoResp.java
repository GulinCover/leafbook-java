package org.leafbook.api.respAbs.userManagerPage;

import lombok.Data;

import java.util.List;

@Data
public class UserLoginInfoResp {
    private String code;
    private List<UserLoginAbs> userLoginAbsList;
}
