package org.leafbook.serviceUserApi.daoImpl;

import org.leafbook.api.modelApi.userInfo.AttentionModel;
import org.leafbook.serviceUserApi.dao.AttentionModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

@Service
public class AttentionModeMapperImpl {
    @Autowired
    private AttentionModelMapper attentionModelMapper;

    public List<Long> selectMultiAttentionUserInfoByUserId(Long userId, Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return attentionModelMapper.selectMultiAttentionUserIds(userId,start, end);
    }

    public List<Long> selectMultiFollowedUserInfoByUserId(Long userId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return attentionModelMapper.selectMultiAttentionUserIds(userId,start, end);
    }

    /**
     * 判断是否关注
     * @param userId
     * @param attentionUserId
     * @return
     */
    public int selectIsExistAttention(Long userId,Long attentionUserId) {
        return attentionModelMapper.selectDetectIsExistAttention(userId, attentionUserId);
    }

    /**
     * 添加关注
     * @param userId
     * @param attentionUserId
     * @return
     */
    public int insertAttentionUserForUserId(Long userId,Long attentionUserId) {
        AttentionModel attentionModel = new AttentionModel();
        attentionModel.setAttentionModelId(IdGeneratorTools.nextId());
        attentionModel.setAttentionUserId(attentionUserId);
        attentionModel.setLastViewTimestamp(0L);
        attentionModel.setUserId(userId);
        attentionModel.setVersion(1);
        attentionModel.setIsBlack(0);
        return attentionModelMapper.insert(attentionModel);
    }

    /**
     * 取消关注
     * @param userId
     * @param attentionUserId
     * @return
     */
    public int deleteCancelAttentionUser(Long userId,Long attentionUserId) {
        return attentionModelMapper.deleteByLogic(userId,attentionUserId);
    }
}











