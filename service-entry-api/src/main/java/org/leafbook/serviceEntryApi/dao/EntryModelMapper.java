package org.leafbook.serviceEntryApi.dao;

import org.leafbook.api.modelApi.entryInfo.EntryModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class EntryModelMapper {
    /**
     * 搜索符合条件的词条
     * @param entryName:词条名
     * @param entryType:词条类型hot,official,nonofficial
     * @return
     */
    public List<EntryShowModel> selectSearchMultiEntryShowInfo(String entryName,String entryType) {
        return this.selectRandomMultiEntryInfo();
    }

    /**
     * 判断词条用户是否点过赞
     * @param userId
     * @param entryId
     * @return
     */
    public Integer selectIsLikedWithUserId(Long userId,Long entryId) {
        return new Random().nextInt(2);
    }
    /**
     * 随机获取3~8条词条信息
     * @return
     */
    public List<EntryShowModel> selectRandomMultiEntryInfo() {
        List<EntryShowModel> entryShowModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(15)+1; i++) {
            EntryShowModel entryShowModel = new EntryShowModel();
            entryShowModel.setEntryDesc(TestModel.randomString().toString());
            entryShowModel.setEntryId((long) i);
            entryShowModel.setEntryName(TestModel.randomWord());
            entryShowModel.setStatus("review_completed");
            entryShowModel.setType("hot");
            entryShowModel.setUserId(500L);

            entryShowModelList.add(entryShowModel);
        }

        return entryShowModelList;
    }
    /**
     * 随机获取2~4条热门词条
     * @return
     */
    public List<EntryShowModel> selectRandomMultiHotEntryInfo() {
        return selectRandomMultiEntryInfo();
    }
    /**
     * 随机获取number条词条
     * @param type
     * @param number
     * @return
     */
    public List<EntryShowModel> selectRandomMultiEntryInfoByType(String type,Integer number) {
        List<EntryShowModel> entryShowModelList = new LinkedList<>();
        for (int i = 0; i < number; i++) {
            EntryShowModel entryShowModel = new EntryShowModel();
            entryShowModel.setEntryDesc(TestModel.randomString().toString());
            entryShowModel.setEntryId((long) i);
            entryShowModel.setEntryName(TestModel.randomWord());
            entryShowModel.setStatus("review_completed");
            entryShowModel.setType("hot");
            entryShowModel.setUserId(500L);

            entryShowModelList.add(entryShowModel);
        }

        return entryShowModelList;
    }
    /**
     * 获取官方词条
     * @param type: hot,official,nonofficial
     * @return
     */
    public List<EntryShowModel> selectAllEntryInfoByType(String type) {
        List<EntryShowModel> entryShowModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(15)+1; i++) {
            EntryShowModel entryShowModel = new EntryShowModel();
            entryShowModel.setEntryDesc(TestModel.randomString().toString());
            entryShowModel.setEntryId((long) i);
            entryShowModel.setEntryName(TestModel.randomWord());
            entryShowModel.setStatus("review_completed");
            entryShowModel.setType(type);
            entryShowModel.setUserId(500L);

            entryShowModelList.add(entryShowModel);
        }

        return entryShowModelList;
    }
    /**
     * 单检测词条合法性
     * @param entryId
     * @return
     */
    public int selectSingleEntryInfoIsExist(Long entryId) {
        return 1;
    }
    /**
     * 组检测词条合法性
     * @param entryIds
     * @return
     */
    public int selectMultiEntryInfoIsExist(List<Long> entryIds) {
        return entryIds.size();
    }

    /**
     * 获取所有热论词条
     * @return
     */
    public List<EntryShowModel> selectAllHotEntryInfo() {
        List<EntryShowModel> entryShowModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(15)+1; i++) {
            EntryShowModel entryShowModel = new EntryShowModel();
            entryShowModel.setEntryDesc(TestModel.randomString().toString());
            entryShowModel.setEntryId((long) i);
            entryShowModel.setEntryName(TestModel.randomWord());
            entryShowModel.setStatus("review_completed");
            entryShowModel.setType("hot");
            entryShowModel.setUserId(500L);

            entryShowModelList.add(entryShowModel);
        }

        return entryShowModelList;
    }

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
