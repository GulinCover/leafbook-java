package org.leafbook.serviceUserApi.dao;

import org.leafbook.api.modelApi.userInfo.UserModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserModelMapper {

    public UserModel selectSingleUserInfo(Long userId) {
        int ret = new Random().nextInt(10);
        if (ret > 0) {
            UserModel userModel = new UserModel();
            userModel.setId(userId);
            userModel.setBalance(1452L);
            userModel.setEmail("958803486@qq.com");
            userModel.setLevel(4001);
            userModel.setLocation("shanghai");
            userModel.setLoginMark(";1;2;");
            userModel.setPhone("130123456789");
            userModel.setSex(1);
            userModel.setUsername("Alex");
            userModel.setUuid("acfd-1234-3456-cdfb");
            return userModel;
        } else {
            return null;
        }
    }

    public Long createSingleUserInfo(String username,String email,String password) {
        return (long) new Random().nextInt(1000);
    }

    public int updateSingleUserInfo(String desc,String location,String sex,String avatar,String backdrop) {
        return new Random().nextInt(2);
    }

    public int updateSingleUserInfoUsername(Long userId, String username) {
        return new Random().nextInt(2);
    }

    public int updateSingleUserInfoPassword(Long userId, String password) {
        return new Random().nextInt(2);
    }

    public int updateSingleUserInfoPhone(Long userId, String phone) {
        return new Random().nextInt(2);
    }

    public int updateSingleUserInfoEmail(Long userId, String email) {
        return new Random().nextInt(2);
    }

    public String selectSingleUserInfoLoginMark(Long userId) {
        return ";1;2;3;4;5;";
    }

    public int updateSingleUserInfoLoginMark(Long userId, String loginMark) {
        return new Random().nextInt(2);
    }
}
