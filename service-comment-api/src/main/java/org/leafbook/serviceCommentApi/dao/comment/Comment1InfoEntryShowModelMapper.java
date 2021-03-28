package org.leafbook.serviceCommentApi.dao.comment;

import org.leafbook.api.modelApi.commentInfo.Comment1Model;
import org.leafbook.api.testModel.indexPage.TestModel;
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
    /**
     * 获取一级普通评论
     * @param comment1Id
     * @return
     */
    public Comment1Model selectSingleComment1Info(Long comment1Id) {
        Comment1Model comment1Model = new Comment1Model();
        comment1Model.setTopicId(312L);
        comment1Model.setCommentContent(TestModel.randomString().toString());
        comment1Model.setUserId(312L);
        comment1Model.setCommentId(comment1Id);
        return comment1Model;
    }

    public List<Long> selectByComment1Id(Long comment1Id) {
        final List<Long> lst = new LinkedList<>();
        LongStream.range(10,15).forEach(it->{
            lst.add(it);
        });
        return lst;
    }
}
