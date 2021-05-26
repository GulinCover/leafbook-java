package org.leafbook.serviceUserApi.daoImpl;

import org.leafbook.api.modelApi.userInfo.LoginInfoModel;
import org.leafbook.serviceUserApi.dao.LoginInfoModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class LoginInfoModelMapperImpl {
    @Autowired
    private LoginInfoModelMapper loginInfoModelMapper;

    /**
     * 查询
     * @param userId
     * @param loginMarks
     * @return
     */
    public List<LoginInfoModel> selectLoginInfoListByLoginMarks(Long userId,List<Long> loginMarks) {
        return loginInfoModelMapper.selectLoginIngoByLoginMarks(userId,loginMarks);
    }

    public int insertSingleLoginMark(Long userId, Long loginMark,String ip) {
        LoginInfoModel model = new LoginInfoModel();
        model.setIp(ip);
        model.setLoginInfoId(loginMark);
        model.setUserId(userId);
        model.setIsBlack(0);
        model.setVersion(1);
        Long time = new Date().getTime();
        model.setCreateTime(time);
        model.setUpdateTime(time);
        return loginInfoModelMapper.insert(model);
    }

    /**
     * 逻辑删除
     * @param userId
     * @param userId
     * @return
     */
    public int deleteWithLogic(Long userId,Long loginMark) {
        return loginInfoModelMapper.deleteWithLogic(userId,loginMark,new Date().getTime());
    }
}
