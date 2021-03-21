package org.leafbook.serviceEntryApi.dao;

import org.leafbook.api.modelApi.entryInfo.Entry2StarAndTreadAmountModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Entry2StarAndTreadAmountModelMapper {
    public Entry2StarAndTreadAmountModel selectByEntryId(Long entryId) {
        Entry2StarAndTreadAmountModel model = new Entry2StarAndTreadAmountModel();
        model.setEntryId(entryId);
        model.setEntryStarAmount(145L);
        model.setEntryTreadAmount(866L);
        return model;
    }

    public int updateByModel(Entry2StarAndTreadAmountModel model) {
        return new Random().nextInt(100);
    }
}
