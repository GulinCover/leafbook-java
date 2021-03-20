package org.leafbook.serviceCommentApi.dao.talk.comment;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

@Service
public class TalkComment1Info2EntryShowModelMapper {
    public List<Long> selectByTalkComment1IdForMultiEntryInfo(Long talkComment1Id) {
        final List<Long> lst = new LinkedList<>();
        LongStream.range(5,11).forEach(it->{
            lst.add(it);
        });
        return lst;
    }
}
