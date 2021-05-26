package org.leafbook.serviceRecordApi.service;

import org.leafbook.api.modelApi.recordInfo.SearchHistoryModel;
import org.leafbook.serviceRecordApi.daoImpl.SearchHistoryModelMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class SearchHistoryRelatedServiceRpc {
    @Autowired
    private SearchHistoryModelMapperImpl searchHistoryModelMapperImpl;
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
        return searchHistoryModelMapperImpl.insert(searchHistoryModel);
    }
    /**
     * 搜索历史,显示最近8条
     * @param userId
     * @return code
     */
    public List<SearchHistoryModel> postSelectMultiSearchHistoryInfo(Long userId) {
        return searchHistoryModelMapperImpl.selectMultiSearchByUserIdAnd8(userId);
    }
}
