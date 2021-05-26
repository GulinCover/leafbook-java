package org.leafbook.serviceCommonApi.daoImpl;

import org.leafbook.api.modelApi.common.StarModel;
import org.leafbook.serviceCommonApi.dao.TouchStarModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TouchStarModelMapperImpl {
    @Autowired
    private TouchStarModelMapper touchStarModelMapper;

    /**
     * 查询是否点过赞
     *
     * @param userId
     * @param objectId
     * @param type
     * @return
     */
    public int selectTouchStar(Long userId, Long objectId, String type) {
        if (Objects.isNull(touchStarModelMapper.selectDetectIsExist(userId, objectId, type)))
            return 0;
        else
            return 1;
    }


    /**
     * 插入点赞信息
     *
     * @param userId
     * @param objectId
     * @param type
     * @return
     */
    public int insertTouchStar(Long userId, Long objectId, String type) {
        StarModel starModel = new StarModel();
        starModel.setStarId(IdGeneratorTools.nextId());
        starModel.setStarType(type);
        starModel.setUserId(userId);
        starModel.setVersion(1);
        starModel.setIsBlack(0);
        Long time = new Date().getTime();
        starModel.setCreateTime(time);
        starModel.setUpdateTime(time);

        if ("topic".equals(type)) {
            starModel.setTopicId(objectId);

        } else if ("comment".equals(type)) {
            starModel.setCommentId(objectId);

        } else if ("talk".equals(type)) {
            starModel.setTalkId(objectId);

        } else if ("talkComment".equals(type)) {
            starModel.setTalkCommentId(objectId);

        } else if ("entry".equals(type)) {
            starModel.setEntryId(objectId);

        } else if ("user".equals(type)) {
            starModel.setLikedUserId(objectId);

        } else {
            return 0;
        }
        return touchStarModelMapper.insert(starModel);
    }
}












