package org.leafbook.serviceRecordApi.dao;

import org.leafbook.api.modelApi.recordInfo.SearchHistoryModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class SearchHistoryModelMapper {
    /**
     * 搜索历史,显示最近8条
     * @param userId
     * @return code
     */
    public List<SearchHistoryModel> selectMultiSearchByUserIdAnd8(Long userId) {
        List<SearchHistoryModel> searchHistoryModelList = new LinkedList<>();
        for (int i = 1; i <= 8; i++) {
            SearchHistoryModel model = new SearchHistoryModel();
            model.setContent(TestModel.randomWord());
            model.setSearchHistoryModelId((long) i);
            model.setUserId((long) i);

            searchHistoryModelList.add(model);
        }

        return searchHistoryModelList;
    }


    public int insert(SearchHistoryModel searchHistoryModel) {
        return new Random().nextInt(100);
    }
}
