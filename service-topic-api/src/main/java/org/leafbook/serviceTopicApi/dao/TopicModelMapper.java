package org.leafbook.serviceTopicApi.dao;

import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

@Service
public class TopicModelMapper {
    /**
     * 获取用户发布的著述
     * @param userId
     * @return
     */
    public List<TopicModel> selectMultiTopicInfoByUserId(Long userId) {
        List<TopicModel> topicModelList = new LinkedList<>();
        for (int i = 0;i<new Random().nextInt(5)+5;++i) {
            topicModelList.add(selectById((long) i));
        }

        return topicModelList;
    }

    /**
     * 模糊搜索自己拥有的著述
     * @param userId
     * @param blurry
     * @param page
     * @return
     */
    public List<TopicModel> selectMultiTopicInfoByUserId(Long userId,String blurry,Long page) {
        List<TopicModel> topicModelList = new LinkedList<>();
        for (int i = 0;i<new Random().nextInt(5)+5;++i) {
            topicModelList.add(selectById((long) i));
        }

        return topicModelList;
    }
    /**
     * 获取点赞排行著述id,每页15条
     * @param page
     * @return
     */
    public List<Long> selectStarRank(Long page) {
        final List<Long> lst = new LinkedList<>();
        LongStream.range(5,10).forEach(it->{
            lst.add(it);
        });
        return lst;
    }
    /**
     * 获取著述的贡献者数量
     * @param topicId
     * @return
     */
    public Long selectContributorAmount(Long topicId) {
        return (long) new Random().nextInt(45);
    }
    /**
     * 获取著述的管理者数量
     * @param topicId
     * @return
     */
    public Long selectManagerAmount(Long topicId) {
        return (long) new Random().nextInt(45);
    }
    /**
     * 获取著述的贡献者id
     * @param topicId
     * @return
     */
    public List<Long> selectMultiContributorId(Long topicId) {
        final List<Long> lst = new LinkedList<>();
        LongStream.range(5,10).forEach(it->{
            lst.add(it);
        });
        return lst;
    }
    /**
     * 获取著述的管理者id
     * @param topicId
     * @return
     */
    public List<Long> selectMultiManagerId(Long topicId) {
        final List<Long> lst = new LinkedList<>();
        LongStream.range(5,10).forEach(it->{
            lst.add(it);
        });
        return lst;
    }
    /**
     * 根据词条随机查询5~8条著述
     * @param entryId
     * @return
     */
    public List<TopicModel> selectRandomMultiTopicInfoByEntryId(Long entryId) {
        List<TopicModel> topicModelList = new LinkedList<>();
        for (int i = 1;i<new Random().nextInt(4)+5;++i) {
            topicModelList.add(selectById((long) i));
        }

        return topicModelList;
    }

    public Long selectSingleForOwnerId(Long topicId) {
        return (long)new Random().nextInt(10000);
    }
    /**
     * 拥有者权限检测
     *
     * @param userId
     * @param topicId
     * @return
     */
    public int selectDecideByUserIdAndTopicId(Long userId,Long topicId) {
        return new Random().nextInt(100);
    }

    /**
     * 管理者权限检测
     *
     * @param userId
     * @param topicId
     * @return
     */
    public int selectDecideByManagerUserIdAndTopicId(Long userId,Long topicId) {
        return new Random().nextInt(100);
    }

    public TopicModel selectById(Long topicId) {
        TopicModel topicModel = new TopicModel();
        topicModel.setUserId(1111L);
        topicModel.setTopicId(topicId);
        topicModel.setTopicDesc(TestModel.randomString().toString());
        topicModel.setTopicTitle(TestModel.randomWord());
        return topicModel;
    }

    public List<TopicModel> selectByIds(List<Long> topicIds) {
        List<TopicModel> topicModelList = new LinkedList<>();
        for (Long i: topicIds) {
            topicModelList.add(selectById(i));
        }

        return topicModelList;
    }

    public Long insert(TopicModel topicModel) {
        return (long)new Random().nextInt(1000)+1000;
    }

    public int updateDesc(Long topicId,String desc) {
        return new Random().nextInt(100);
    }

    public int updateCover(Long topicId,String cover) {
        return new Random().nextInt(100);
    }

    public int updateForDescByTopicId(Long topicId,String topicDesc) {
        return new Random().nextInt(100);
    }

    public int updateForEntryIdsByTopicId(Long topicId,String entryIds) {
        return new Random().nextInt(100);
    }

    public int updateByModel(TopicModel topicModel) {
        return new Random().nextInt(100);
    }

}
