package org.leafbook.serviceTopicApi.dao;

import org.leafbook.api.modelApi.topicInfo.ManagerModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

@Service
public class ManagerModelMapper {

    public int selectDecideByManagerUserIdAndTopicId(Long userId,Long topicId) {
        return new Random().nextInt(100);
    }
    /**
     * 获取所有著述管理者id
     * @param topicId
     * @return
     */
    public List<Long> selectMultiForManagerId(Long topicId) {
        final List<Long> lst = new LinkedList<>();
        LongStream.range(1000, new Random().nextInt(50)+1000).forEach(it->{
            lst.add(it);
        });
        return lst;
    }
    /**
     * 获取著述管理者ids
     * @param topicId
     * @return
     */
    public List<Long> selectAllManagerUserIds(Long topicId) {
        final List<Long> lst = new LinkedList<>();
        LongStream.range(5,10).forEach(lst::add);
        return lst;
    }


    public int insertByModel(ManagerModel managerModel) {
        return new Random().nextInt(100);
    }


    public int deleteSingleByManagerUserId(Long managerUserId) {
        return new Random().nextInt(100);
    }


}
