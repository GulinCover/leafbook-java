package org.leafbook.serviceRecordApi.service;

import org.leafbook.api.modelApi.recordInfo.SearchHistoryModel;
import org.leafbook.serviceRecordApi.dao.SearchHistoryModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchHistoryRelatedServiceRpc {
    @Autowired
    private SearchHistoryModelMapper searchHistoryModelMapper;
    /**
     * 增加搜索记录
     * @param userId
     * @param searchContent
     * @return code
     */
    public int postInsertSingleSearchHistoryInfo(Long userId,String searchContent) {
        SearchHistoryModel searchHistoryModel = new SearchHistoryModel();
        searchHistoryModel.setUserId(userId);
        searchHistoryModel.setContent(searchContent);
        return searchHistoryModelMapper.insert(searchHistoryModel);
    }
    /**
     * 搜索历史,显示最近8条
     * @param userId
     * @return code
     */
    public List<SearchHistoryModel> postSelectMultiSearchHistoryInfo(Long userId) {
        return searchHistoryModelMapper.selectMultiSearchByUserIdAnd8(userId);
    }
}
