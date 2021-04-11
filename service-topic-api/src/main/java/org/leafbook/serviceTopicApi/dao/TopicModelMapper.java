package org.leafbook.serviceTopicApi.dao;

import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
     * 获取用户发布的著述
     * @param userId
     * @return
     */
    public List<TopicModel> selectMultiTopicInfoByUserId(Long userId,Long page) {
        List<TopicModel> topicModelList = new LinkedList<>();
        for (int i = 0;i<new Random().nextInt(5)+5;++i) {
            topicModelList.add(selectById((long) i));
        }

        return topicModelList;
    }
    /**
     * 获取数量
     * @param userId
     * @return
     */
    public Long selectTopicInfoAmount(Long userId) {
        return (long)new Random().nextInt(5000);
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
        return (long) new Random().nextInt(500);
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
     * 获取著述所有评论数量
     * @param topicId
     * @return
     */
    public Long selectCommentAmount(Long topicId) {
        return (long) new Random().nextInt(45);
    }

    /**
     * 获取著述的贡献者id
     * @param topicId
     * @return
     */
    public List<Long> selectMultiContributorId(Long topicId,Long page) {
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
    /**
     * 获取用户最新几条著述信息
     * @param userId
     * @param number
     * @return
     */
    public List<TopicModel> selectMultiLastTopicInfo(Long userId,Integer number) {
        List<TopicModel> topicModelList = new LinkedList<>();
        for (int i = 1;i<number;++i) {
            topicModelList.add(selectById((long) i));
        }

        return topicModelList;
    }

    public Long insert(TopicModel topicModel) {
        return (long)new Random().nextInt(1000)+1000;
    }
    /**
     * 更改用topic拥有者,用于购买后使用时使用
     * @param userId
     * @param topicId
     * @return
     */
    public int updateSingleTopicInfoByTopicIdWithOwner(Long userId,Long topicId) {
        return 1;
    }

    public int updateDesc(Long topicId,String desc) {
        return 1;
    }

    public int updateCover(Long topicId,String cover) {
        return 1;
    }

    public int updateForDescByTopicId(Long topicId,String topicDesc) {
        return 1;
    }

    public int updateForEntryIdsByTopicId(Long topicId,String entryIds) {
        return 1;
    }

    public int updateByModel(TopicModel topicModel) {
        return 1;
    }

    /**
     * 联合搜索指定著述
     * @param status://0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用
     * @param entryIds
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    public List<TopicModel> jointTopicShowEntryTableSelectSearchMultiTopicInfo(
            Integer status,
            List<Long> entryIds,
            String content,
            Long startTime,
            Long endTime,
            Long page) {
        List<TopicModel> topicModelList = new LinkedList<>();
        for (int i = 1;i<new Random().nextInt(4)+5;++i) {
            topicModelList.add(selectById((long) i));
        }

        return topicModelList;
    }

    public List<TopicModel> jointTopicShowEntryTableSelectSearchMultiTopicInfo(
            Integer status,
            Long entryId,
            String content,
            Long page) {
        List<TopicModel> topicModelList = new LinkedList<>();
        for (int i = 1;i<new Random().nextInt(4)+5;++i) {
            topicModelList.add(selectById((long) i));
        }

        return topicModelList;
    }
    /**
     * 获取搜索条数
     * @param status
     * @param entryIds
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    public Long jointTopicShowEntryTableSelectSearchMultiTopicInfoAmount(
            Integer status,
            List<Long> entryIds,
            String content,
            Long startTime,
            Long endTime,
            Long page
    ) {
        return (long)new Random().nextInt(5000);
    }

    /**
     * 联合搜索指定著述
     * @param status://0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用
     * @param content
     * @param page
     * @return
     */
    public List<TopicModel> jointTopicShowEntryTableSelectSearchMultiTopicInfo(
            Integer status,
            String content,
            Long page) {
        List<TopicModel> topicModelList = new LinkedList<>();
        for (int i = 1;i<new Random().nextInt(4)+5;++i) {
            topicModelList.add(selectById((long) i));
        }

        return topicModelList;
    }

    /**
     * 获取搜索条数
     * @param status
     * @param content
     * @return
     */
    public Long jointTopicShowEntryTableSelectSearchMultiTopicInfoAmount(
            Integer status,
            String content
    ) {
        return (long)new Random().nextInt(5000);
    }



}
