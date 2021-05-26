package org.leafbook.serviceEntryApi.daoImpl;

import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.leafbook.serviceEntryApi.dao.EntryShowModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class EntryShowModelMapperImpl {
    @Autowired
    private EntryShowModelMapper entryShowModelMapper;

    /**
     * 词条总数量
     * @return
     */
    public Long selectAllEntryAmount() {
        return entryShowModelMapper.selectAllEntryAmount();
    }
    /**
     * 单查询
     * @param entryId
     * @return
     */
    public EntryShowModel selectSingleByEntryId(Long entryId) {
        return entryShowModelMapper.selectSingleEntryByEntryId(entryId);
    }

    /**
     * 组查询
     * @param entryIds
     * @return
     */
    public List<EntryShowModel> selectMultiByEntryIds(List<Long> entryIds) {
        return entryShowModelMapper.selectMultiEntryByEntryIds(entryIds);
    }
    /**
     * 获取全部entryInfo通过页号
     * @param page
     * @return
     */
    public List<EntryShowModel> selectAllByPage(Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return entryShowModelMapper.selectMultiEntryByPage(start,end);
    }
    /**
     * 获取全部entryInfo
     * @return
     */
    public List<EntryShowModel> selectAllEntryInfo() {
        return entryShowModelMapper.selectAllEntry();
    }

    /**
     * 随机获取3~8条词条信息
     * @return
     */
    public List<EntryShowModel> selectRandomMultiEntryInfo() {
        Long number = (long) (new Random().nextInt(6)+3);
        return entryShowModelMapper.selectRandomEntry(number);
    }

    /**
     * 随机获取3~8条词条信息
     * @return
     */
    public List<EntryShowModel> selectRandomMultiEntryInfoByType(String type,Long number) {
        return entryShowModelMapper.selectRandomEntryByType(type,number);
    }

    /**
     * 随机获取2~4条热门词条
     * @return
     */
    public List<EntryShowModel> selectRandomMultiHotEntryInfo() {
        Long number = (long) (new Random().nextInt(5)+2);
        return entryShowModelMapper.selectRandomHotEntry(number);
    }

    /**
     * 获取所有词条
     * @param type: hot,official,nonofficial
     * @return
     */
    public List<EntryShowModel> selectAllEntryInfoByType(String type) {
        return entryShowModelMapper.selectAllEntryByType(type);
    }

    /**
     * 单检测词条合法性
     * @param entryId
     * @return
     */
    public int selectSingleEntryInfoIsExist(Long entryId) {
        return entryShowModelMapper.selectSingleDetectIsExistEntry(entryId);
    }

    /**
     * 组检测词条合法性
     * @param entryIds
     * @return
     */
    public int selectMultiEntryInfoIsExist(List<Long> entryIds) {
        return entryShowModelMapper.selectMultiDetectIsExistEntry(entryIds);
    }

    /**
     * 搜索符合条件的词条
     * @param entryName:词条名
     * @param entryType:词条类型hot,official,nonofficial
     * @return
     */
    public List<EntryShowModel> selectSearchMultiEntryShowInfo(String entryName,String entryType) {
        return entryShowModelMapper.selectSearchAllEntry(entryName,entryType);
    }

}














