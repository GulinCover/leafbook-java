package org.leafbook.serviceTopicApi.daoImpl;

import org.leafbook.api.annotation.NotUsedYet;
import org.leafbook.api.annotation.RemainingProblem;
import org.leafbook.api.modelApi.topicInfo.DirectoryModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@NotUsedYet
@Service
public class DirectoryModelMapper {
    public List<DirectoryModel> selectByTopicId(Long topicId) {
        List<DirectoryModel> directoryModelList = new LinkedList<>();

        for (int i = 1; i < new Random().nextInt(50)+1; ++i) {
            DirectoryModel directoryModel = new DirectoryModel();
            directoryModel.setArticleId(65465L);
            directoryModel.setTopicId(topicId);
            directoryModel.setPageNumber((long) i);

            directoryModelList.add(directoryModel);
        }

        return directoryModelList;
    }

    public int updateByModel(DirectoryModel directoryModel) {
        return new Random().nextInt(100);
    }

    public int insertByModel(DirectoryModel directoryModel) {
        return new Random().nextInt(100);
    }


}
