package org.leafbook.serviceCommentApi.daoImpl;

import org.leafbook.serviceCommentApi.dao.Comment2EntryShowModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Comment2EntryShowModelMapperImpl {
    @Autowired
    private Comment2EntryShowModelMapper comment2EntryShowModelMapper;

    public List<Long> selectMultiEntryIdsByComment1Id(Long comment1Id) {
        return comment2EntryShowModelMapper.selectMultiEntryIdByComment1Id(comment1Id);
    }

    public List<Long> selectMultiEntryIdsByTalkId(Long talkId) {
        return comment2EntryShowModelMapper.selectMultiEntryIdByTalkId(talkId);
    }

    public List<Long> selectMultiEntryIdsByTalkComment1Id(Long talkComment1Id) {
        return comment2EntryShowModelMapper.selectMultiEntryIdByTalkComment1Id(talkComment1Id);
    }
}
