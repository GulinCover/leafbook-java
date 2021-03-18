package org.leafbook.serviceEntryApi.dao;

import org.leafbook.api.modelApi.entryInfo.EntryModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class EntryModelMapper {

    public int insert(Long userId,String entryName,String entryDesc,String type) {
        return new Random().nextInt(100);
    }

    public EntryModel select(Long entryId) {
        EntryModel entryModel = new EntryModel();
        entryModel.setEntryDesc(TestModel.randomString().toString());
        entryModel.setEntryId(entryId);
        entryModel.setEntryName(TestModel.randomWord());
        entryModel.setStatus("review_completed");
        entryModel.setType("official");
        entryModel.setUserId(500L);
        return entryModel;
    }

    public List<EntryModel> selectMulti(List<Long> entryIds) {
        List<EntryModel> entryModelList = new LinkedList<>();
        entryIds.forEach(it->{
            entryModelList.add(select(it));
        });
        return entryModelList;
    }

    public int delete(Long entryId) {
        return new Random().nextInt(100);
    }

    public int deleteMulti(List<Long> entryIds) {
        return new Random().nextInt(100);
    }
}
