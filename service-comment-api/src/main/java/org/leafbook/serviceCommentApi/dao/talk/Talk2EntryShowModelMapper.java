package org.leafbook.serviceCommentApi.dao.talk;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

@Service
public class Talk2EntryShowModelMapper {

    public List<Long> selectByTalkIdForMultiEntryInfo(Long talkId) {
        final List<Long> lst = new LinkedList<>();
        LongStream.range(5,11).forEach(it->{
            lst.add(it);
        });
        return lst;
    }
}
