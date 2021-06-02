package org.leafbook.serviceTopicApi.daoImpl;

import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.serviceTopicApi.dao.TopicModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class TopicModelMapperImpl {
    @Autowired
    private TopicModelMapper topicModelMapper;
    /**
     * 获取topic总数量
     * @return
     */
    public Long selectAllTopicInfoPageAmount() {
        return topicModelMapper.selectAllTopicInfoPageAmount();
    }
    /**
     * 判断topicId是否存在
     * @param topicId
     * @return
     */
    public int selectDetectTopicInfoByTopicId(Long topicId) {
        return topicModelMapper.selectIsExistTopicId(topicId);
    }
    /**
     * 获取用户发布的著述
     * @param userId
     * @return
     */
    public List<TopicModel> selectMultiTopicInfoByUserId(Long userId,Long page) {
        Long end = 20;
        Long start = page * 20;
        return topicModelMapper.selectMultiTopicInfoByUserId(userId,start, end);
    }
    /**
     * 获取数量
     * @param userId
     * @return
     */
    public Long selectTopicInfoAmount(Long userId) {
        return topicModelMapper.selectMultiTopicInfoAmountByUserId(userId);
    }

    /**
     * 模糊搜索自己拥有的著述
     * @param userId
     * @param blurry
     * @param page
     * @return
     */
    public List<TopicModel> selectMultiTopicInfoByUserId(Long userId,String blurry,Long page) {
        Long end = 20;
        Long start = 20;
        return topicModelMapper.selectSearchMeMultiTopicInfo(userId,blurry,start,end);
    }

    /**
     * 获取著述拥有者id
     * @param topicId
     * @return
     */
    public Long selectSingleForOwnerId(Long topicId) {
        return topicModelMapper.selectSingleForOwnerId(topicId);
    }

    /**
     * 拥有者权限检测
     *
     * @param userId
     * @param topicId
     * @return
     */
    public int selectDecideByUserIdAndTopicId(Long userId,Long topicId) {
        return topicModelMapper.selectDecideForTopicIdByUserId(userId,topicId);
    }

    /**
     *
     * @param topicId
     * @return
     */
    public TopicModel selectById(Long topicId) {
        return topicModelMapper.selectByTopicId(topicId);
    }

    /**
     *
     * @param topicIds
     * @return
     */
    public List<TopicModel> selectByIds(List<Long> topicIds) {
        return topicModelMapper.selectMultiTopicInfoByTopicIds(topicIds);
    }
    /**
     * 获取用户最新几条著述信息
     * @param userId
     * @param number
     * @return
     */
    public List<TopicModel> selectMultiLastTopicInfo(Long userId,Integer number) {
        return topicModelMapper.selectMultiMeLastTopicInfo(userId,number);
    }


    public Long insert(TopicModel topicModel) {
        Long topicId = IdGeneratorTools.nextId();
        topicModel.setTopicId(topicId);
        topicModel.setVersion(1);
        topicModel.setIsBlack(0);
        Long time = new Date().getTime();
        topicModel.setCreateTime(time);
        topicModel.setUpdateTime(time);
        int ret = topicModelMapper.insert(topicModel);
        return ret == 1 ? topicId : 0;
    }
    /**
     * 更改用topic拥有者,用于购买后使用时使用
     * @param userId
     * @param topicId
     * @return
     */
    public int updateSingleTopicInfoByTopicIdWithOwner(Long userId,Long topicId) {
        TopicModel topicModel = topicModelMapper.selectByTopicId(topicId);
        if (!userId.equals(topicModel.getUserId())) return 0;
        return topicModelMapper.insert(topicModel);
    }

    public int updateDesc(Long topicId,String desc) {
        return topicModelMapper.updateSingleTopicDesc(topicId,desc);
    }

    public int updateCover(Long topicId,String cover) {
        return topicModelMapper.updateSingleTopicCover(topicId,cover);
    }

    public int updateForDescByTopicId(Long topicId,String topicDesc) {
        return topicModelMapper.updateSingleTopicDesc(topicId,topicDesc);
    }

    public int updateByModel(TopicModel topicModel) {
        return topicModelMapper.updateById(topicModel);
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
        Long end = 20;
        Long start = page * 20;
        List<Long> topicIds = topicModelMapper.joinSelectMultiTopic(entryIds, content, startTime, endTime, start,end);
        return selectByIds(topicIds);
    }

    public List<TopicModel> jointTopicShowEntryTableSelectSearchMultiTopicInfo(
            Integer status,
            Long entryId,
            String content,
            Long page) {
        Long end = 20;
        Long start = page * 20;
        Long startTime = 0L;
        Long endTime = new Date().getTime();
        List<Long> entryIds = new LinkedList<>();
        entryIds.add(entryId);
        return selectByIds(topicModelMapper.joinSelectMultiTopic(entryIds, content, startTime, endTime, start,end));
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
        return topicModelMapper.joinSelectMultiTopicAmount(entryIds,content,startTime,endTime);
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
        Long end = 20;
        Long start = page * 20;
        Long startTime = 0L;
        Long endTime = new Date().getTime();
        return topicModelMapper.selectSearchMultiTopicInfo(content, startTime, endTime, start,end);
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
        Long startTime = 0L;
        Long endTime = new Date().getTime();
        return topicModelMapper.selectSearchMultiTopicInfoAmount(content, startTime, endTime);
    }

    /**
     * 点赞排行模糊搜索
     * @param page
     * @param blurry
     * @param entry
     * @param startTime
     * @param endTime
     * @return
     */
    public List<TopicModel> jointTopicLikedAndTreadModelTableSelectSearchMultiTopicInfo(
            Long page,String blurry,Long entry,Long startTime,Long endTime
    ) {
        Long end = 20;
        Long start = page * 20;
        return topicModelMapper.jointTopicLikedAndTreadModelTableSelectSearchMultiTopicInfo(start,end,blurry,entry,startTime,endTime);
    }

    /**
     * 点赞排行模糊搜索数据数量
     * @param page
     * @param blurry
     * @param entry
     * @param startTime
     * @param endTime
     * @return
     */
    public Long jointTopicLikedAndTreadModelTableSelectSearchMultiTopicInfoPageAmount(
            Long page,String blurry,Long entry,Long startTime,Long endTime
    ) {
        Long end = 20;
        Long start = page * 20;
        return topicModelMapper.jointTopicLikedAndTreadModelTableSelectSearchMultiTopicInfoPageAmount(start,end,blurry,entry,startTime,endTime);
    }

    /**
     * 获取词条下所有topic数量
     * @param entryId
     * @return
     */
    public Long joinSelectTopicAmountByEntryId(Long entryId) {
        return topicModelMapper.joinSelectTopicAmountByEntryId(entryId);
    }
    /**
     * 随机获取正在拍卖的topic
     * @param entryId
     * @return
     */
    public List<Long> selectRandomTopicIdsByEntryIdForAuction(Long entryId) {
        return topicModelMapper.joinSelectRandomTopicIdsByEntryIdForAuction(entryId,0L,(long)(new Random().nextInt(6) + 4));
    }
}
