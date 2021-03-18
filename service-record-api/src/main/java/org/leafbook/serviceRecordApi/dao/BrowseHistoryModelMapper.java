package org.leafbook.serviceRecordApi.dao;

import org.leafbook.api.modelApi.recordInfo.BrowseHistoryModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class BrowseHistoryModelMapper {
    public List<BrowseHistoryModel> selectMultiBrowseHistoryByUserIdAndPage(Long userId,Integer page) {
        List<BrowseHistoryModel> browseHistoryModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(5); ++i) {
            BrowseHistoryModel browseHistoryModel = new BrowseHistoryModel();
            browseHistoryModel.setHistoryModelId((long) i);
            browseHistoryModel.setTopicId((long) i);
            browseHistoryModel.setUserId(userId);

            browseHistoryModelList.add(browseHistoryModel);
        }

        return browseHistoryModelList;
    }

    public int deleteSingleBrowseHistoryByBrowseHistoryId(Long userId,Long browseHistoryId) {
        return new Random().nextInt(100);
    }

    public int insert(BrowseHistoryModel browseHistoryModel) {
        return new Random().nextInt(100);
    }

    public int deleteMultiBrowseHistoryByBrowseHistoryIds(Long userId,List<Long> browseHistoryIds) {
        return new Random().nextInt(100);
    }

}
