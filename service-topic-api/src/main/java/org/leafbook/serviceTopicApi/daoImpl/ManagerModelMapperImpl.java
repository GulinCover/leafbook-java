package org.leafbook.serviceTopicApi.daoImpl;

import org.leafbook.api.modelApi.topicInfo.ManagerModel;
import org.leafbook.serviceTopicApi.dao.ManagerModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

@Service
public class ManagerModelMapperImpl {
    @Autowired
    private ManagerModelMapper managerModelMapper;
    /**
     * 获取所有著述管理者id
     * @param topicId
     * @return
     */
    public List<Long> selectMultiForManagerId(Long topicId) {
        return managerModelMapper.selectMultiManagerIdsByTopicId(topicId);
    }
    /**
     * 获取著述管理者ids
     * @param topicId
     * @return
     */
    public List<Long> selectAllManagerUserIds(Long topicId) {
        return managerModelMapper.selectMultiManagerIdsByTopicId(topicId);
    }

    /**
     * 获取著述的管理者数量
     * @param topicId
     * @return
     */
    public Long selectManagerAmount(Long topicId) {
        return managerModelMapper.selectMultiManagerIdAmount(topicId);
    }

    /**
     * 管理者权限检测
     *
     * @param userId
     * @param topicId
     * @return
     */
    public int selectDecideByManagerUserIdAndTopicId(Long userId,Long topicId) {
        return managerModelMapper.selectDecideManagerIdWithTopicId(userId,topicId);
    }


    public int insertByModel(ManagerModel managerModel) {
        return managerModelMapper.insert(managerModel);
    }


    public int deleteSingleByManagerUserId(Long managerUserId, Long topicId) {
        return managerModelMapper.deleteManagerId(managerUserId,topicId);
    }

}
