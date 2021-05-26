package org.leafbook.serviceapi.serviceApi.userManager;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.leafbook.utils.tools.Covert2Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@GlobalTransactional
@Service
public class ProfilePageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;

    /**
     * 修改用户信息,图片url;单独修改
     * @param userId
     * @param form:userAvatar,userDesc,userLocation,userSex,backdrop
     * @return
     */
    public int postUpdateUserInfo(Long userId,Map<String, String> form) {
        UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
        if (Objects.isNull(userModel)) return 403;

        String userAvatar = form.get("userAvatar");
        String userDesc = form.get("userDesc");
        String userLocation = form.get("userLocation");
        String userSex = form.get("userSex");
        String backdrop = form.get("backdrop");
        if (Objects.isNull(userAvatar) ||
                Objects.isNull(userDesc) ||
                Objects.isNull(backdrop) ||
                Objects.isNull(userLocation) ||
                Objects.isNull(userSex)) return 403;

        if (!Covert2Tools.isDigital(userSex)) form.put("userSex", "0");

        form.put("userId",String.valueOf(userId));
        int ret = userServiceRpc.postUpdateSingleUserInfoRpc(form);
        if (ret == 0) return 500;
        return ret;
    }
}
