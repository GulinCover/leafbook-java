package org.leafbook.serviceEntryApi.daoImpl;

import org.leafbook.api.modelApi.entryInfo.EntryModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.leafbook.serviceEntryApi.dao.EntryModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class EntryModelMapperImpl {
    @Autowired
    private EntryModelMapper entryModelMapper;

    /**
     * 词条查重
     * @param entryName
     * @return
     */
    public int selectDetectIsExistByEntry(String entryName) {
        return entryModelMapper.selectDetectIsExistByEntry(entryName);
    }

    /**
     * 申请词条
     * @param userId
     * @param entryName
     * @param entryDesc
     * @param type
     * @return
     */
    public int insert(Long userId,String entryName,String entryDesc,String type) {
        EntryModel entryModel = new EntryModel();
        entryModel.setEntryType(type);
        entryModel.setEntryAvatar("");
        entryModel.setEntryDesc(entryDesc);
        entryModel.setEntryId(IdGeneratorTools.nextId());
        entryModel.setEntryName(entryName);
        entryModel.setUserId(userId);
        entryModel.setStatus(0);
        entryModel.setIsBlack(0);
        entryModel.setVersion(1);
        Long time = new Date().getTime();
        entryModel.setCreateTime(time);
        entryModel.setUpdateTime(time);
        return entryModelMapper.insert(entryModel);
    }

    /**
     * 单查询
     * @param entryId
     * @return
     */
    public EntryModel selectSingleEntryByEntryId(Long entryId) {
        return entryModelMapper.selectSingleEntryByEntryId(entryId);
    }

    /**
     * 组查询
     * @param entryIds
     * @return
     */
    public List<EntryModel> selectMultiEntryByEntryIds(List<Long> entryIds) {
        return entryModelMapper.selectMultiEntryByEntryIds(entryIds);
    }

    /**
     * 逻辑单删除
     * @param entryId
     * @return
     */
    public int deleteSingleForLogic(Long entryId) {
        return entryModelMapper.deleteSingleForLogic(entryId,new Date().getTime());
    }
    /**
     * 逻辑组删除
     * @param entryIds
     * @return
     */
    public int deleteMultiForLogic(List<Long> entryIds) {
        return entryModelMapper.deleteMultiForLogic(entryIds,new Date().getTime());
    }
}
