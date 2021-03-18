package org.leafbook.serviceUserApi.service;

import org.leafbook.api.modelApi.common.CodeModel;
import org.leafbook.api.modelApi.userInfo.LoginInfoModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
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

    /**
     * 更改用户名
     * @param userId
     * @return code
     */
    public int postUpdateSingleUserInfoUsername(Long userId,String username) {
        return userModelMapper.updateSingleUserInfoUsername(userId, username);
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
     * 发送手机验证码
     * @param userId
     * @param phone
     * @return 验证码
     */
    public String postGeneratePhoneCode(Long userId,String phone) {
        //查询mysql里是否存在验证码，时间是否间隔300秒
        Long timestamp = new Date().getTime();
        String code = codeModelMapper.selectCodeIntervalLT(userId, phone, timestamp);

        if (!"".equals(code)) return "";
        code = RandomCodeTools.generateRandomCode();
        CodeModel codeModel = new CodeModel();
        codeModel.setUserId(userId);
        codeModel.setCode(code);
        codeModel.setPhone(phone);
        codeModel.setTimestamp(timestamp);
        codeModelMapper.insert(codeModel);
        //手机发送验证码
        return code;
    }

    /**
     * 发送邮箱验证码
     * @param userId
     * @param email
     * @return 验证码
     */
    public String postGenerateEmailCode(Long userId,String email) {
        //查询mysql里是否存在验证码，时间是否间隔300秒
        Long timestamp = new Date().getTime();
        String code = codeModelMapper.selectCodeIntervalLT(userId, email, timestamp);

        if (!"".equals(code)) return "";
        code = RandomCodeTools.generateRandomCode();
        CodeModel codeModel = new CodeModel();
        codeModel.setUserId(userId);
        codeModel.setCode(code);
        codeModel.setEmail(email);
        codeModel.setTimestamp(timestamp);
        codeModelMapper.insert(codeModel);
        //邮箱发送验证码
        return code;
    }


}
