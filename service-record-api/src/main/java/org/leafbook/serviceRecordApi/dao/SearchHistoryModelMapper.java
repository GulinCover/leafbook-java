package org.leafbook.serviceRecordApi.dao;

import org.leafbook.api.modelApi.recordInfo.SearchHistoryModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SearchHistoryModelMapper {
    public int insert(SearchHistoryModel searchHistoryModel) {
        return new Random().nextInt(100);
    }
}
