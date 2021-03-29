package org.leafbook.serviceUserApi.dao;

import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserModelMapper {

    /**
     * 检测用户合法性
     * @param userId
     * @return
     */
    public int selectSingleUserInfoIsExist(Long userId) {
        return 1;
    }

    /**
     * 检测用户合法性
     * @param userId
     * @return
     */
    public int selectSingleUserLoginIdIsExist(Long userId,Long loginId) {
        return 1;
    }

    public UserModel selectSingleUserInfo(Long userId) {
        UserModel userModel = new UserModel();
        userModel.setId(userId);
        userModel.setBalance(1452L);
        userModel.setEmail("958803486@qq.com");
        userModel.setLevel(4001);
        userModel.setDesc(TestModel.randomWord());
        userModel.setLocation("shanghai");
        userModel.setLoginMark(";1;2;");
        userModel.setPhone("130123456789");
        userModel.setSex(1);
        userModel.setUsername("Alex");
        userModel.setUsedName(";Alex;");
        userModel.setUuid("acfd-1234-3456-cdfb");
        return userModel;
    }

    public String selectSingleUserInfoLoginMark(Long userId) {
        return ";1;2;3;4;5;";
    }

    public Long createSingleUserInfo(String username, String email, String password) {
        return (long) new Random().nextInt(1000);
    }

    /**
     * 用户数据更新
     * @param desc
     * @param location
     * @param sex
     * @param avatar
     * @param backdrop
     * @return
     */
    public int updateSingleUserInfo(String desc, String location, Integer sex, String avatar, String backdrop) {
        return new Random().nextInt(100);
    }

    public int updateSingleUserInfoUsername(UserModel userModel) {
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

    public int updateSingleUserInfoLoginMark(Long userId, String loginMark) {
        return new Random().nextInt(2);
    }
    /**
     * 更改用户信息
     * @param userModel
     * @return
     */
    public int updateSingleUserInfoByUserInfo(UserModel userModel) {
        return 1;
    }

    /**
     * 添加关注
     * @param userId
     * @param attentionUserId
     * @return
     */
    public int insertAttentionUserForUserId(Long userId,Long attentionUserId) {
        return 1;
    }
}
