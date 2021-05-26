package org.leafbook.serviceUserApi.service;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.leafbook.api.modelApi.userInfo.LoginInfoModel;
import org.leafbook.api.modelApi.userInfo.ResModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.serviceUserApi.daoImpl.*;
import org.leafbook.utils.tools.Covert2Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class UserRelatedServiceRpc {
    @Autowired
    private UserModelMapperImpl userModelMapperImpl;
    @Autowired
    private LoginInfoModelMapperImpl loginInfoModelMapperImpl;
    @Autowired
    private CodeModelMapperImpl codeModelMapperImpl;
    @Autowired
    private AttentionModeMapperImpl attentionModeMapperImpl;
    @Autowired
    private ResModelMapperImpl resModelMapperImpl;
    @Autowired
    private RSAPublicKey publicKey;
    @Autowired
    private CSRFCodeModelMapperImpl csrfCodeModelMapperImpl;

    /**
     * 单用户查询
     *
     * @param userId
     * @return
     */
    public UserModel postSelectSingleUserInfo(Long userId) {
        return userModelMapperImpl.selectSingleUserInfo(userId);
    }

    /**
     * 多用户查询
     *
     * @param userIds
     * @return
     */
    public List<UserModel> postSelectMultiUserInfo(List<Long> userIds) {
        return userModelMapperImpl.selectMultiUserInfoByUserIds(userIds);
    }

    /**
     * 获取用户关注人列表
     *
     * @param userId
     * @return
     */
    public List<Long> postSelectMultiAttentionUserInfo(Long userId, Long page) {
        return attentionModeMapperImpl.selectMultiAttentionUserInfoByUserId(userId, page);
    }

    /**
     * 获取订阅者列表
     *
     * @param userId
     * @return
     */
    public List<Long> postSelectMultiFollowedUserInfo(Long userId, Long page) {
        return attentionModeMapperImpl.selectMultiFollowedUserInfoByUserId(userId, page);
    }

    /**
     * 判断是否已关注
     *
     * @param userId
     * @param attentionId
     * @return
     */
    public int postIsExistAttention(Long userId, Long attentionId) {
        return attentionModeMapperImpl.selectIsExistAttention(userId, attentionId);
    }

    /**
     * 创建用户
     *
     * @param username
     * @param email
     * @param password
     * @return userId
     */
    public Long postCreateSingleUserInfo(String username, String email, String password, String ip) {
        UserModel userInfo = userModelMapperImpl.selectSingleUserInfoByEmail(email);
        if (Objects.nonNull(userInfo)) return 0L;

        Long userId = userModelMapperImpl.createSingleUserInfo(username, email, password);
        UserModel userModel = userModelMapperImpl.selectSingleUserInfo(userId);
        List<Long> loginMarks = new LinkedList<>();
        for (String s:userModel.getLoginMark().split(";")) {
            if (Covert2Tools.isDigital(s) && !"".equals(s)) {
                loginMarks.add(Covert2Tools.covertToLong(s));
            }
        }
        List<LoginInfoModel> loginInfoModels = loginInfoModelMapperImpl.selectLoginInfoListByLoginMarks(userId, loginMarks);
        long max = 0;
        for (LoginInfoModel loginInfoModel:loginInfoModels) {
            if (loginInfoModel.getLoginInfoId() > max) {
                max = loginInfoModel.getLoginInfoId();
            }
        }

        loginInfoModelMapperImpl.insertSingleLoginMark(userId, max, ip);
        return userId;

    }

    /**
     * 用户数据更新
     *
     * @param form:userId,userAvatar,userDesc,userLocation,userSex,backdrop
     * @return code
     */
    public int postUpdateSingleUserInfo(Map<String, String> form) {
        String desc = form.get("userDesc");
        String location = form.get("userLocation");
        String sex = form.get("userSex");
        String avatar = form.get("userAvatar");
//        String backdrop = form.get("backdrop");
        String backdrop = "";
        String userId = form.get("userId");
        return userModelMapperImpl.updateSingleUserInfo(Covert2Tools.covertToLong(userId), desc, location, Covert2Tools.covertToInteger(sex), avatar, backdrop);
    }

    /**
     * 更改用户名,
     * 储存到曾用名
     *
     * @param userId
     * @return code
     */
    public int postUpdateSingleUserInfoUsername(Long userId, String username) {
        UserModel userModel = userModelMapperImpl.selectSingleUserInfo(userId);
        userModel.setUsedName(userModel.getUsedName() + userModel.getUsername() + ";");
        userModel.setUsername(username);
        return userModelMapperImpl.updateSingleUserInfoUsername(userModel);
    }

    /**
     * 更改密码
     *
     * @param userId
     * @param password
     * @return code
     */
    public int postUpdateSingleUserInfoPassword(Long userId, String password) {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(241234254233214L);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(31, secureRandom);
        return userModelMapperImpl.updateSingleUserInfoPassword(userId, encoder.encode(password));
    }

    /**
     * 找回更改密码
     * @param email
     * @param password
     * @return
     */
    public int postUpdateSingleUserInfoPasswordByEmail(String email, String password) {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(241234254233214L);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(31, secureRandom);
        return userModelMapperImpl.updateSingleUserInfoPasswordByEmail(email, encoder.encode(password));
    }


    /**
     * 更改手机
     *
     * @param userId
     * @param phone
     * @return code
     */
    public int postUpdateSingleUserInfoPhone(Long userId, String phone) {
        return userModelMapperImpl.updateSingleUserInfoPhone(userId, phone);
    }

    /**
     * 更改邮箱
     *
     * @param userId
     * @param email
     * @return code
     */
    public int postUpdateSingleUserInfoEmail(Long userId, String email) {
        return userModelMapperImpl.updateSingleUserInfoEmail(userId, email);
    }

    /**
     * 查询当前账户已登录的登陆号
     *
     * @param userId
     * @return 登陆信息
     */
    public List<LoginInfoModel> postSelectSingleLoginInfo(Long userId) {
        String loginMark = userModelMapperImpl.selectSingleUserInfoLoginMark(userId);
        List<Long> loginMarkList = new LinkedList<>();

        for (String s : loginMark.split(";")) {
            if ("".equals(s)) continue;
            loginMarkList.add(Covert2Tools.covertToLong(s));
        }

        return loginMarkList.size() == 0 ? new LinkedList<LoginInfoModel>() : loginInfoModelMapperImpl.selectLoginInfoListByLoginMarks(userId,loginMarkList);
    }

    /**
     * 踢出当前账户已登录的某个登陆号
     *
     * @param userId
     * @param loginMark
     * @return code
     */
    public int postDeleteSingleUserInfoLoginMark(Long userId, String loginMark) {
        if (!Covert2Tools.isDigital(loginMark)) return 0;

        String mark = userModelMapperImpl.selectSingleUserInfoLoginMark(userId);
        if (Objects.isNull(mark)) return 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(";");

        for (String s : mark.split(";")) {
            if (!loginMark.equals(s)) {
                stringBuilder.append(s);
                stringBuilder.append(";");
            }
        }

        loginInfoModelMapperImpl.deleteWithLogic(userId, Covert2Tools.covertToLong(loginMark));
        return userModelMapperImpl.updateSingleUserInfoLoginMark(userId, stringBuilder.toString());
    }


    /**
     * 账户密码登陆
     *
     * @param email
     * @param password
     * @return jwt
     */
    public String postLogin(String email, String password, String ip) throws JOSEException {
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return "登录失败";
        } catch (NullPointerException e) {
            return "当前账号不存在";
        }

        UserModel userModel = userModelMapperImpl.selectSingleUserInfoByEmail(email);
        String loginMark = userModel.getLoginMark();
        List<Long> loginMarks = new LinkedList<>();
        for (String loginM:loginMark.split(";")) {
            if (Covert2Tools.isDigital(loginM) && !"".equals(loginM)) {
                loginMarks.add(Covert2Tools.covertToLong(loginM));
            }
        }

        StringBuilder retLogMarks = new StringBuilder();
        retLogMarks.append(";");
        long max = 0;
        List<LoginInfoModel> loginInfoModels = loginInfoModelMapperImpl.selectLoginInfoListByLoginMarks(userModel.getId(), loginMarks);
        if (Objects.isNull(loginInfoModels) || loginInfoModels.isEmpty()) {
            retLogMarks.append("1;");
        } else {
            for (int i = 0; i < loginInfoModels.size(); i++) {
                if (loginInfoModels.get(i).getLoginInfoId() > max) {
                    max = loginInfoModels.get(i).getLoginInfoId();
                }
            }
            long time = new Date().getTime() - 30L * 86400 * 1000;
            for (int i = 0; i < loginInfoModels.size(); i++) {
                if (loginInfoModels.get(i).getCreateTime() > time) {
                    retLogMarks.append(loginInfoModels.get(i).getLoginInfoId()).append(";");
                }
            }
            retLogMarks.append(max + 1).append(";");
        }

        loginInfoModelMapperImpl.insertSingleLoginMark(userModel.getId(), max + 1, ip);
        userModelMapperImpl.updateSingleUserInfoLoginMark(userModel.getId(), retLogMarks.toString());

        //获取csrf
        String csrfCode = csrfCodeModelMapperImpl.selectCurrentCSRFCodeByTime(new Date().getTime());

        //生成jwtHeader
        JWEHeader jweHeader = new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A128GCM);
        //生成payload信息
        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                .issuer(email)
                .subject(email)
                .expirationTime(new Date(new Date().getTime() + 60 * 1000 * 60))
                .claim("csrf", csrfCode)
                .build();

        EncryptedJWT jwt = new EncryptedJWT(jweHeader, jwtClaims);
        RSAEncrypter encrypter = new RSAEncrypter(publicKey);

        encrypter.getJCAContext().setProvider(BouncyCastleProviderSingleton.getInstance());

        jwt.encrypt(encrypter);

//        System.out.println(jwt.serialize());
        return "Bearer " + jwt.serialize();
    }

    /**
     * 检测用户合法性
     *
     * @param userId
     * @return
     */
    public int postSelectDetectLegalityWithUserId(Long userId) {
        return userModelMapperImpl.selectSingleUserInfoIsExist(userId);
    }

    /**
     * 检测登陆id合法性
     *
     * @param userId
     * @return
     */
    public int postSelectDetectLoginId(Long userId, Long loginId) {
        return userModelMapperImpl.selectSingleUserLoginIdIsExist(userId, loginId);
    }

    /**
     * 更改用户信息
     *
     * @param userModel
     * @return
     */
    public int postUpdateSingleUserInfoByUserInfo(UserModel userModel) {
        return userModelMapperImpl.updateSingleUserInfoByUserInfo(userModel);
    }

    /**
     * 添加关注
     *
     * @param userId
     * @param attentionUserId
     * @return
     */
    public int postAddAttentionUser(Long userId, Long attentionUserId) {
        return attentionModeMapperImpl.insertAttentionUserForUserId(userId, attentionUserId);
    }

    /**
     * 获取物品信息
     *
     * @param userId
     * @return
     */
    public ResModel postSelectSingleResInfoByUserId(Long userId, Long resId) {
        return resModelMapperImpl.selectSingleResInfo(userId, resId);
    }

    /**
     * 查询用户所有可用物品
     *
     * @param userId
     * @return
     */
    public List<ResModel> postSelectMultiUserResInfoByUserId(Long userId, Long page) {
        return resModelMapperImpl.selectMultiResInfoByUserId(userId, page);
    }

    /**
     * 查询用户所有可用物品数量
     * @param userId
     * @return
     */
    public Long postSelectMultiUserResInfoAmountByUserId(Long userId) {
        return resModelMapperImpl.selectMultiResInfoAmountByUserId(userId);
    }

    /**
     * 使用nickname物品
     *
     * @param userId
     * @param resId
     * @return
     */
    public int postUseSingleNicknameResInfo(Long userId, Long resId) {
        UserModel userModel = userModelMapperImpl.selectSingleUserInfo(userId);
        ResModel resModel = resModelMapperImpl.selectSingleResInfo(userId, resId);

        String nickname = resModel.getNickname();

        //删除物品
        resModelMapperImpl.deleteSingleResInfoByUserId(userId, resId);
        String oldName = userModel.getUsername();

        userModel.setUsername(nickname);
        userModel.setUsedName(userModel.getUsedName() + oldName + ";");
        return userModelMapperImpl.updateSingleUserInfoByUserInfo(userModel);
    }

    /**
     * 使用renameCard物品
     *
     * @param userId
     * @param resId
     * @return
     */
    public int postUseSingleRenameCardResInfo(Long userId, Long resId, String newName) {
        //删除物品
        UserModel userModel = userModelMapperImpl.selectSingleUserInfo(userId);
        resModelMapperImpl.deleteSingleResInfoByUserId(userId, resId);

        String oldName = userModel.getUsername();

        userModel.setUsername(newName);
        userModel.setUsedName(userModel.getUsedName() + oldName + ";");
        return userModelMapperImpl.updateSingleUserInfoByUserInfo(userModel);
    }

    /**
     * 更新用户余额
     *
     * @param userId
     * @param balance
     * @return
     */
    public int postUpdateSingleUserBalanceRpc(Long userId, Long balance) {
        return userModelMapperImpl.updateSingleUserInfoBalance(userId, balance);
    }

    /**
     * 取消关注
     *
     * @param userId
     * @param attentionUserId
     * @return
     */
    public int postCancelAttention(Long userId, Long attentionUserId) {
        return attentionModeMapperImpl.deleteCancelAttentionUser(userId, attentionUserId);
    }

    /**
     * 根据uuid查询用户
     *
     * @param uuid
     * @return
     */
    public UserModel postSelectSingleUserInfoByUuid(String uuid) {
        return userModelMapperImpl.selectSingleUserInfoByUuid(uuid);
    }

    /**
     * email查重
     * @param email
     * @return
     */
    public int postSelectSingleDetectIsExistByEmail(String email) {
        return userModelMapperImpl.selectSingleDetectIsExistByEmail(email);
    }
    /**
     * 删除物品
     * @param userId
     * @param resId
     * @return
     */
    public int postDeleteSingleUserResInfo(Long userId,Long resId) {
        return resModelMapperImpl.deleteSingleResInfoByUserId(userId, resId);
    }
}
