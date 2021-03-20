package org.leafbook.serviceCommentApi.dao.comment;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

@Service
public class Comment1InfoEntryShowModelMapper {
    public List<Long> selectByComment1Id(Long comment1Id) {
        final List<Long> lst = new LinkedList<>();
        LongStream.range(10,15).forEach(it->{
            lst.add(it);
        });
        return lst;
    }
}
