package org.leafbook.api.modelApi.userInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class LoginInfoModel extends Model {
    private Long loginInfoId;
    private Long userId;
    private String ip;
    private String loginLocation;
}
