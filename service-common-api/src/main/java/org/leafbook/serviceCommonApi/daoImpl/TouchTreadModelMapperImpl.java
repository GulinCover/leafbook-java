package org.leafbook.serviceCommonApi.daoImpl;

import org.leafbook.api.modelApi.common.TreadModel;
import org.leafbook.serviceCommonApi.dao.TouchTreadModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TouchTreadModelMapperImpl {
    @Autowired
    private TouchTreadModelMapper touchTreadModelMapper;

    public int selectTouchTread(Long userId, Long objectId, String type) {
        return touchTreadModelMapper.selectDetectIsExist(userId, objectId, type);
    }

    public int insertTouchTread(Long userId, Long objectId, String type) {
        TreadModel treadModel = new TreadModel();
        treadModel.setStarId(IdGeneratorTools.nextId());
        treadModel.setTreadType(type);
        treadModel.setUserId(userId);
        if ("topic".equals(type)) {
            treadModel.setTopicId(objectId);

        } else if ("comment".equals(type)) {
            treadModel.setCommentId(objectId);

        } else if ("talk".equals(type)) {
            treadModel.setTalkId(objectId);

        } else if ("talkComment".equals(type)) {
            treadModel.setTalkCommentId(objectId);

        } else if ("entry".equals(type)) {
            treadModel.setEntryId(objectId);

        } else if ("user".equals(type)) {
            treadModel.setLikedUserId(objectId);

        } else {
            return 0;
        }
        return touchTreadModelMapper.insert(treadModel);
    }

}
