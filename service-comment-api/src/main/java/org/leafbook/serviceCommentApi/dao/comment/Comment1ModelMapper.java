package org.leafbook.serviceCommentApi.dao.comment;

import org.leafbook.api.modelApi.commentInfo.Comment1Model;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class Comment1ModelMapper {

    public List<Comment1Model> selectMultiByTopicId(Long topicId) {
        List<Comment1Model> comment1ModelList = new LinkedList<>();
        for (int i = 0; i <new Random().nextInt(5); i++) {
            Comment1Model comment1Model = new Comment1Model();
            comment1Model.setCommentId((long) i);
            comment1Model.setCommentContent(TestModel.randomString().toString());
            comment1Model.setTopicId(topicId);
            comment1Model.setUserId((long) i);

            comment1ModelList.add(comment1Model);
        }

        return comment1ModelList;
    }

    public int insertByModel(Comment1Model comment1Model) {
        return new Random().nextInt(100);
    }

}
