package org.leafbook.serviceTopicApi.daoImpl;

import org.leafbook.api.annotation.ToBeOptimized;
import org.leafbook.api.modelApi.topicInfo.ContributorModel;
import org.leafbook.serviceTopicApi.dao.ContributorModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

@Service
public class ContributorModelMapperImpl {
    @Autowired
    private ContributorModelMapper contributorModelMapper;
    /**
     * 随机获取贡献者信息
     * @param topicId
     * @param randomNumber
     * @return
     */
    @ToBeOptimized
    public List<ContributorModel> selectRandomContributorInfoByTopicId(Long topicId,Integer randomNumber) {
        return contributorModelMapper.selectMultiRandomContributorInfoByTopicId(topicId,randomNumber);
    }

    /**
     * 获取著述的贡献者数量
     * @param topicId
     * @return
     */
    public Long selectContributorAmount(Long topicId) {
        return contributorModelMapper.selectUserIdAmount(topicId);
    }

    /**
     * 获取著述的贡献者id
     * @param topicId
     * @return
     */
    public List<Long> selectMultiContributorId(Long topicId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return contributorModelMapper.selectMultiUserIds(topicId,start,end);
    }
}
