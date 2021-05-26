package org.leafbook.serviceUserApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.userInfo.UserModel;

import java.util.List;

@Mapper
public interface UserModelMapper extends BaseMapper<UserModel> {
    UserModel selectSingleUserInfoByEmail(@Param("email")String email);

    String selectPwdByEmail(@Param("email")String email);

    UserModel selectByUuid(@Param("uuid") String uuid);

    Integer selectSingleUserIsExist(@Param("userId")Long userId);

    Integer selectSingleEmailIsExist(@Param("email")String email);

    String selectForLoginMarkByUserId(@Param("userId")Long userId);

    List<UserModel> selectMultiUserInfoByUserIds(@Param("userIds")List<Long> userIds);

    int updateUserPassword(
            @Param("userId")Long userId,
            @Param("password")String password,
            @Param("time")Long time
    );
    int updateUserPasswordByEmail(
            @Param("email")String email,
            @Param("password")String password,
            @Param("time")Long time
    );
    int updateUserPhone(
            @Param("userId")Long userId,
            @Param("phone")String phone,
            @Param("time")Long time
    );
    int updateUserEmail(
            @Param("userId")Long userId,
            @Param("email")String email,
            @Param("time")Long time
    );
    int updateUserLoginMark(
            @Param("userId")Long userId,
            @Param("loginMark")String loginMark,
            @Param("time")Long time
    );
    int updateUserBalance(
            @Param("userId")Long userId,
            @Param("balance")Long balance,
            @Param("time")Long time
    );
}
