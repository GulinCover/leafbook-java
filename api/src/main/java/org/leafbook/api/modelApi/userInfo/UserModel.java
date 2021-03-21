package org.leafbook.api.modelApi.userInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class UserModel extends Model {
    private Long id;
    private String username;
    private String usedName;
    private String uuid;

    private String desc;
    private String avatar = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg";
    private Integer level;
    private Integer sex;//0:男,1:女
    private String location;
    private Long balance;

    private String phone;
    private String email;
    private String password;
    private String loginMark;//登录信息,;1;2;3;4;,例.踢出2号登录信息
}
