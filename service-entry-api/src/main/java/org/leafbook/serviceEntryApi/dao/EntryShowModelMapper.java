package org.leafbook.serviceEntryApi.dao;

import org.leafbook.api.modelApi.entryInfo.EntryModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EntryShowModelMapper {
    public EntryModel selectSingleByEntryId(Long entryId) {
        EntryModel entryModel = new EntryModel();
        entryModel.setEntryDesc(TestModel.randomString().toString());
        entryModel.setEntryId(entryId);
        entryModel.setEntryName(TestModel.randomWord());
        entryModel.setStatus("review_completed");
        entryModel.setType("official");
        entryModel.setUserId(500L);
        return entryModel;
    }

    public List<EntryModel> selectMultiByEntryId(List<Long> entryIds) {
        List<EntryModel> entryModelList = new LinkedList<>();
        entryIds.forEach(it->{
            entryModelList.add(selectSingleByEntryId(it));
        });
        return entryModelList;
    }

    public List<EntryShowModel> selectAllByPage(Long page) {
        List<EntryShowModel> entryShowModelList = new LinkedList<>();
        for (int i = 0; i < page; i++) {
            EntryShowModel entryShowModel = new EntryShowModel();
            entryShowModel.setEntryDesc(TestModel.randomString().toString());
            entryShowModel.setEntryId((long) i);
            entryShowModel.setEntryName(TestModel.randomWord());
            entryShowModel.setStatus("review_completed");
            entryShowModel.setType("official");
            entryShowModel.setUserId(500L);

            entryShowModelList.add(entryShowModel);
        }
        return entryShowModelList;
    }
}
