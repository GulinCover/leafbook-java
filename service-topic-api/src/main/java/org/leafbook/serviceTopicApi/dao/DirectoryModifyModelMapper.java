package org.leafbook.serviceTopicApi.dao;

import org.leafbook.api.modelApi.topicInfo.DirectoryModifyModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DirectoryModifyModelMapper {

    public int insertByModel(DirectoryModifyModel directoryModifyModel) {
        return new Random().nextInt(100);
    }
}
