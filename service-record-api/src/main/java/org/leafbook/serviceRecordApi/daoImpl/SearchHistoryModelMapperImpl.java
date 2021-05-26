package org.leafbook.serviceRecordApi.daoImpl;

import org.leafbook.api.modelApi.recordInfo.SearchHistoryModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.leafbook.serviceRecordApi.dao.SearchHistoryModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class SearchHistoryModelMapperImpl {
    @Autowired
    private SearchHistoryModelMapper searchHistoryModelMapper;
    /**
     * 搜索历史,显示最近8条
     * @param userId
     * @return code
     */
    public List<SearchHistoryModel> selectMultiSearchByUserIdAnd8(Long userId) {
        return searchHistoryModelMapper.selectMultiLatestByUserId(userId,8L);
    }


    public int insert(SearchHistoryModel searchHistoryModel) {
        searchHistoryModel.setSearchHistoryModelId(IdGeneratorTools.nextId());
        searchHistoryModel.setIsBlack(0);
        searchHistoryModel.setVersion(1);
        return searchHistoryModelMapper.insert(searchHistoryModel);
    }
}
