package org.leafbook.api.modelApi;

import lombok.Data;

@Data
public class LoginInfoModel {
    private Long loginInfoId;
    private Long userId;
    private String IP;
    private String loginLocation;
}
