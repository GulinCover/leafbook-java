package org.leafbook.serviceCommentApi.dao.comment;

import org.leafbook.api.modelApi.commentInfo.Comment2Model;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class Comment2ModelMapper {
    public List<Comment2Model> selectMultiByComment1Id(Long topicId) {
        List<Comment2Model> comment2ModelList = new LinkedList<>();
        for (int i = 0; i <new Random().nextInt(5); i++) {
            Comment2Model comment2Model = new Comment2Model();
            comment2Model.setCommentId((long) i);
            comment2Model.setCommentContent(TestModel.randomString().toString());
            comment2Model.setTopicId(topicId);
            comment2Model.setUserId((long) i);

            comment2ModelList.add(comment2Model);
        }

        return comment2ModelList;
    }

    public int insertByModel(Comment2Model comment1Model) {
        return new Random().nextInt(100);
    }
}
