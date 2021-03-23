package org.leafbook.serviceEntryApi.dao;

import org.leafbook.api.modelApi.entryInfo.EntryModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class EntryShowModelMapper {
    public EntryShowModel selectSingleByEntryId(Long entryId) {
        EntryShowModel entryShowModel = new EntryShowModel();
        entryShowModel.setEntryDesc(TestModel.randomString().toString());
        entryShowModel.setEntryId(entryId);
        entryShowModel.setEntryName(TestModel.randomWord());
        entryShowModel.setStatus("review_completed");
        entryShowModel.setType("official");
        entryShowModel.setUserId(500L);
        return entryShowModel;
    }

    public List<EntryShowModel> selectMultiByEntryId(List<Long> entryIds) {
        List<EntryShowModel> entryShowModelList = new LinkedList<>();
        entryIds.forEach(it->{
            entryShowModelList.add(selectSingleByEntryId(it));
        });
        return entryShowModelList;
    }
    /**
     * 获取全部entryInfo通过页号
     * @param page
     * @return
     */
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
    /**
     * 获取全部entryInfo
     * @return
     */
    public List<EntryShowModel> selectAllEntryInfo() {
        List<EntryShowModel> entryShowModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(30)+10; i++) {
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
