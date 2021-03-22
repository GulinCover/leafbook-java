package org.leafbook.serviceCommentApi.dao.comment;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

@Service
public class Comment1InfoEntryShowModelMapper {
    /**
     * 获取评论词条
     * @param comment1Id
     * @return
     */
    public List<Long> selectEntryIdsByComment1Id(Long comment1Id) {
        final List<Long> lst = new LinkedList<>();
        LongStream.range(5,8).forEach(it->{
            lst.add(it);
        });

        return lst;
    }

    public List<Long> selectByComment1Id(Long comment1Id) {
        final List<Long> lst = new LinkedList<>();
        LongStream.range(10,15).forEach(it->{
            lst.add(it);
        });
        return lst;
    }
}
