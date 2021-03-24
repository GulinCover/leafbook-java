package org.leafbook.serviceUserApi.service;

import org.leafbook.api.modelApi.common.CodeModel;
import org.leafbook.api.modelApi.userInfo.LoginInfoModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.serviceUserApi.dao.AttentionModeMapper;
import org.leafbook.serviceUserApi.dao.CodeModelMapper;
import org.leafbook.serviceUserApi.dao.LoginInfoModelMapper;
import org.leafbook.serviceUserApi.dao.UserModelMapper;
import org.leafbook.utils.tools.Covert2Tools;
import org.leafbook.utils.tools.RandomCodeTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserRelatedServiceRpc {
    @Autowired
    private UserModelMapper userModelMapper;

    @Autowired
    private LoginInfoModelMapper loginInfoModelMapper;

    @Autowired
    private CodeModelMapper codeModelMapper;
    @Autowired
    private AttentionModeMapper attentionModeMapper;
    /**
     * 单用户查询
     *
     * @param userId
     * @return
     */
    public UserModel postSelectSingleUserInfo(Long userId) {
        return userModelMapper.selectSingleUserInfo(userId);
    }
    /**
     * 多用户查询
     *
     * @param userIds
     * @return
     */
    public List<UserModel> postSelectMultiUserInfo(List<Long> userIds) {
        List<UserModel> userModelList = new LinkedList<>();
        userIds.forEach((it)->{
            userModelList.add(userModelMapper.selectSingleUserInfo(it));
        });
        return userModelList;
    }
    /**
     * 获取用户关注人列表
     * @param userId
     * @return
     */
    public List<Long> postSelectMultiAttentionUserInfo(Long userId) {
        return attentionModeMapper.selectMultiAttentionUserInfoByUserId(userId);
    }
    /**
     * 获取订阅者列表
     * @param userId
     * @return
     */
    public List<Long> postSelectMultiFollowedUserInfo(Long userId) {
        return attentionModeMapper.selectMultiFollowedUserInfoByUserId(userId);
    }
    /**
     * 判断是否已关注
     * @param userId
     * @param attentionId
     * @return
     */
    public int postIsExistAttention(Long userId,Long attentionId) {
        return attentionModeMapper.selectIsExistAttention(userId,attentionId);
    }

    /**
     * 创建用户
     * @param username
     * @param email
     * @param password
     * @return userId
     */
    public Long postCreateSingleUserInfo(String username,String email,String password) {
        return userModelMapper.createSingleUserInfo(username, email, password);
    }

    /**
     * 用户数据更新
     *
     * @param form form: desc,location,sex,avatar,backdrop
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

    /**
     * 更改用户名,
     * 储存到曾用名
     * @param userId
     * @return code
     */
    public int postUpdateSingleUserInfoUsername(Long userId,String username) {
        UserModel userModel = userModelMapper.selectSingleUserInfo(userId);
        userModel.setUsedName(userModel.getUsedName()+userModel.getUsername()+";");
        userModel.setUsername(username);
        return userModelMapper.updateSingleUserInfoUsername(userModel);
    }

    /**
     * 更改密码
     * @param userId
     * @param password
     * @return code
     */
    public int postUpdateSingleUserInfoPassword(Long userId, String password) {
        return userModelMapper.updateSingleUserInfoPassword(userId, password);
    }

    /**
     * 更改密码
     * @param userId
     * @param phone
     * @return code
     */
    public int postUpdateSingleUserInfoPhone(Long userId, String phone) {
        return userModelMapper.updateSingleUserInfoPhone(userId, phone);
    }

    /**
     * 更改密码
     * @param userId
     * @param email
     * @return code
     */
    public int postUpdateSingleUserInfoEmail(Long userId, String email) {
        return userModelMapper.updateSingleUserInfoEmail(userId, email);
    }

    /**
     * 查询当前账户已登录的登陆号
     * @param userId
     * @return 登陆信息
     */
    public List<LoginInfoModel> postSelectSingleLoginInfo(Long userId) {
        String loginMark = userModelMapper.selectSingleUserInfoLoginMark(userId);
        List<Integer> loginMarkList = new LinkedList<>();

        for (String s : loginMark.split(";")) {
            if ("".equals(s)) continue;
            loginMarkList.add(Covert2Tools.covertToInteger(s));
        }

        return loginInfoModelMapper.selectLoginInfoListByLoginMarks(loginMarkList);
    }

    /**
     * 踢出当前账户已登录的某个登陆号
     * @param userId
     * @param loginMark
     * @return code
     */
    public int postDeleteSingleUserInfoLoginMark(Long userId,String loginMark) {
        String mark = userModelMapper.selectSingleUserInfoLoginMark(userId);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(";");

        for (String s : mark.split(";")) {
            if (!loginMark.equals(s)) {
                stringBuilder.append(s);
                stringBuilder.append(";");
            }
        }

        return userModelMapper.updateSingleUserInfoLoginMark(userId, stringBuilder.toString());
    }


    /**
     * 账户密码登陆
     * @param email
     * @param password
     * @return jwt
     */
    public String postLogin(String email,String password) {
        return "jwt";
    }
    /**
     * 检测用户合法性
     * @param userId
     * @return
     */
    public int postSelectDetectLegalityWithUserId(Long userId) {
        return userModelMapper.selectSingleUserInfoIsExist(userId);
    }

}
