package org.leafbook.serviceUserApi.daoImpl;

import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.serviceUserApi.dao.UserModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.leafbook.utils.tools.UuidGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserModelMapperImpl {
    @Autowired
    private UserModelMapper userModelMapper;

    /**
     * 根据email查询用户信息
     * @param email
     * @return
     */
    public UserModel selectSingleUserInfoByEmail(String email) {
        return userModelMapper.selectSingleUserInfoByEmail(email);
    }

    /**
     * 根据email查询密码
     * @param email
     * @return
     */
    public String selectSingleUserPwdByEmail(String email) {
        return userModelMapper.selectPwdByEmail(email);
    }
    /**
     * 根据uuid查询用户
     *
     * @param uuid
     * @return
     */
    public UserModel selectSingleUserInfoByUuid(String uuid) {
        return userModelMapper.selectByUuid(uuid);
    }

    /**
     * 检测用户合法性
     *
     * @param userId
     * @return
     */
    public int selectSingleUserInfoIsExist(Long userId) {
        return userModelMapper.selectSingleUserIsExist(userId);
    }

    /**
     * email查重
     * @param email
     * @return
     */
    public int selectSingleDetectIsExistByEmail(String email) {
        return userModelMapper.selectSingleEmailIsExist(email);
    }

    /**
     * 检测用户登陆标记合法性
     *
     * @param userId
     * @return
     */
    public int selectSingleUserLoginIdIsExist(Long userId, Long loginId) {
        String loginMark = userModelMapper.selectForLoginMarkByUserId(userId);
        if (Objects.nonNull(loginMark)) {
            String[] split = loginMark.split(";");
            for (String s : split) {
                if (s.equals(String.valueOf(loginId))) {
                    return 1;
                }
            }
        } else {
            return 0;
        }

        return 0;
    }

    /**
     * 用户查询
     *
     * @param userId
     * @return
     */
    public UserModel selectSingleUserInfo(Long userId) {
        return userModelMapper.selectById(userId);
    }

    /**
     * 查询用户登陆标记
     *
     * @param userId
     * @return
     */
    public String selectSingleUserInfoLoginMark(Long userId) {
        return userModelMapper.selectForLoginMarkByUserId(userId);
    }

    /**
     * 创建用户
     *
     * @param username
     * @param email
     * @param password
     * @return
     */
    public Long createSingleUserInfo(String username, String email, String password) {
        UserModel userModel = new UserModel();
        Long userId = IdGeneratorTools.nextId();
        userModel.setUuid(UuidGeneratorTools.createGenerate());
        userModel.setLevel(1000);
        userModel.setBalance(0L);
        userModel.setIsBlack(0);
        userModel.setVersion(1);
        userModel.setId(userId);
        userModel.setUsername(username);
        userModel.setEmail(email);
        userModel.setLoginMark(";1;");

        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(241234254233214L);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(31,secureRandom);
        userModel.setPassword(bCryptPasswordEncoder.encode(password));

        Long time = new Date().getTime();
        userModel.setCreateTime(time);
        userModel.setUpdateTime(time);
        int ret = userModelMapper.insert(userModel);
        return ret == 1 ? userId : 0;
    }

    /**
     * 多用户查询
     * @param userIds
     * @return
     */
    public List<UserModel> selectMultiUserInfoByUserIds(List<Long> userIds) {
        return userModelMapper.selectMultiUserInfoByUserIds(userIds);
    }

    /**
     * 用户数据更新
     *
     * @param userDesc
     * @param location
     * @param sex
     * @param avatar
     * @param backdrop
     * @return
     */
    public int updateSingleUserInfo(Long userId, String userDesc, String location, Integer sex, String avatar, String backdrop) {
        UserModel userModel = userModelMapper.selectById(userId);
        userModel.setUserDesc(userDesc);
        userModel.setLocation(location);
        userModel.setSex(sex);
        userModel.setAvatar(avatar);
        Long time = new Date().getTime();
        userModel.setUpdateTime(time);
        userModel.setCreateTime(time);
//        userModel.backdrop
        return userModelMapper.updateById(userModel);
    }

    public int updateSingleUserInfoUsername(UserModel userModel) {
        return userModelMapper.updateById(userModel);
    }

    public int updateSingleUserInfoPassword(Long userId, String password) {
        return userModelMapper.updateUserPassword(userId, password, new Date().getTime());
    }

    public int updateSingleUserInfoPasswordByEmail(String email, String password) {
        return userModelMapper.updateUserPasswordByEmail(email, password, new Date().getTime());
    }

    public int updateSingleUserInfoPhone(Long userId, String phone) {
        return userModelMapper.updateUserPhone(userId, phone, new Date().getTime());
    }

    public int updateSingleUserInfoEmail(Long userId, String email) {
        return userModelMapper.updateUserEmail(userId, email, new Date().getTime());
    }

    public int updateSingleUserInfoLoginMark(Long userId, String loginMark) {
        return userModelMapper.updateUserLoginMark(userId, loginMark, new Date().getTime());
    }

    /**
     * 更改用户信息
     *
     * @param userModel
     * @return
     */
    public int updateSingleUserInfoByUserInfo(UserModel userModel) {
        return userModelMapper.updateById(userModel);
    }

    /**
     * 更新用户余额
     *
     * @param userId
     * @param balance
     * @return
     */
    public int updateSingleUserInfoBalance(Long userId, Long balance) {
        return userModelMapper.updateUserBalance(userId, balance, new Date().getTime());
    }

}
