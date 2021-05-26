package org.leafbook.serviceTopicApi.daoImpl;

import org.leafbook.api.annotation.NotUsedYet;
import org.leafbook.api.modelApi.topicInfo.DirectoryModifyModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@NotUsedYet
@Service
public class DirectoryModifyModelMapper {

    public int insertByModel(DirectoryModifyModel directoryModifyModel) {
        return new Random().nextInt(100);
    }
}
