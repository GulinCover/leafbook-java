package org.leafbook.serviceUserApi.service;

import org.leafbook.api.modelApi.UserModel;
import org.leafbook.serviceUserApi.dao.UserModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserRelatedServiceRpc {
    @Autowired
    private UserModelMapper userModelMapper;

    public UserModel postSelectSingleUserInfo(Long userId) {
        return userModelMapper.selectSingleUserInfo(userId);
    }

    public List<UserModel> postSelectMultiUserInfo(List<Long> userIds) {
        List<UserModel> userModelList = new LinkedList<>();
        userIds.forEach((it)->{
            userModelList.add(userModelMapper.selectSingleUserInfo(it));
        });
        return userModelList;
    }

    public Long postCreateSingleUserInfo(String username,String email,String password) {
        return userModelMapper.createSingleUserInfo(username, email, password);
    }

    /**
     * @param form
     * form: desc,location,sex,avatar,backdrop
     * @return code
     */
    public int postUpdateSingleUserInfo(Map<String, String> form) {
        String desc = form.get("desc");
        String location = form.get("location");
        String sex = form.get("sex");
        String avatar = form.get("avatar");
        String backdrop = form.get("backdrop");
        return userModelMapper.updateSingleUserInfo(desc,location,sex,avatar,backdrop);
    }

    public int postUpdateSingleUserInfoUsername(Long userId,String username) {
        return userModelMapper.updateSingleUserInfoUsername(userId, username);
    }
}
