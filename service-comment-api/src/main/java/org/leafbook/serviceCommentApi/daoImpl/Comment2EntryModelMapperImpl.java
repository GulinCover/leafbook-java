package org.leafbook.serviceCommentApi.daoImpl;

import org.leafbook.api.modelApi.commentInfo.Comment2EntryModel;
import org.leafbook.serviceCommentApi.dao.Comment2EntryModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Comment2EntryModelMapperImpl {
    @Autowired
    private Comment2EntryModelMapper comment2EntryModelMapper;

    public int insertByModel(Comment2EntryModel model) {
        Long time = new Date().getTime();
        model.setComment2EntryModelId(IdGeneratorTools.nextId());
        model.setVersion(1);
        model.setIsBlack(0);
        model.setCreateTime(time);
        model.setUpdateTime(time);
        return comment2EntryModelMapper.insert(model);
    }
}
