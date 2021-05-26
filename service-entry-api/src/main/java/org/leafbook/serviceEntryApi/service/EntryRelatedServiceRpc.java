package org.leafbook.serviceEntryApi.service;

import org.leafbook.api.modelApi.entryInfo.Entry2StarAndTreadAmountModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.serviceEntryApi.daoImpl.Entry2StarAndTreadAmountModelMapperImpl;
import org.leafbook.serviceEntryApi.daoImpl.EntryModelMapperImpl;
import org.leafbook.serviceEntryApi.daoImpl.EntryShowModelMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class EntryRelatedServiceRpc {
    @Autowired
    private EntryModelMapperImpl entryModelMapperImpl;
    @Autowired
    private EntryShowModelMapperImpl entryShowModelMapperImpl;
    @Autowired
    private Entry2StarAndTreadAmountModelMapperImpl entry2StarAndTreadAmountModelMapperImpl;

    /**
     * 用户申请词条，默认nonofficial类型，
     * 审核通过后方可使用
     * @param userId
     * @param entryName
     * @param entryDesc
     * @return
     */
    public int postCreateSingleEntryInfo(Long userId,String entryName,String entryDesc,String type) {
        if ("hot".equals(type)) {
            return entryModelMapperImpl.insert(userId,entryName,entryDesc,"hot");
        } else if ("official".equals(type)) {
            return entryModelMapperImpl.insert(userId,entryName,entryDesc,"official");
        } else {
            return entryModelMapperImpl.insert(userId,entryName,entryDesc,"nonofficial");
        }
    }
    /**
     * 单一查询词条信息
     * @param entryId
     * @return
     */
    public EntryShowModel getSelectSingleEntryInfo(Long entryId) {
        return entryShowModelMapperImpl.selectSingleByEntryId(entryId);
    }
    /**
     * 组查询词条信息
     * @param entryIds
     * @return
     */
    public List<EntryShowModel> getSelectMultiEntryInfo(List<Long> entryIds) {
        return entryShowModelMapperImpl.selectMultiByEntryIds(entryIds);
    }
    /**
     * 获取全部entryInfo通过页号
     * @param page
     * @return
     */
    public List<EntryShowModel> getSelectAllEntryInfo(Long page) {
        return entryShowModelMapperImpl.selectAllByPage(page);
    }

    /**
     * 获取全部entryInfo
     * @return
     */
    public List<EntryShowModel> getSelectAllEntryInfo() {
        return entryShowModelMapperImpl.selectAllEntryInfo();
    }

    /**
     * entryInfo点赞量+1
     * @param entryId
     * @return
     */
    public int postUpdateEntryInfoStarAmount(Long entryId) {
        return entry2StarAndTreadAmountModelMapperImpl.updateStarByEntryId(entryId);
    }

    /**
     * entryInfo点踩量+1
     * @param entryId
     * @return
     */
    public int postUpdateEntryInfoTreadAmount(Long entryId) {
        return entry2StarAndTreadAmountModelMapperImpl.updateTreadByEntryId(entryId);
    }
    /**
     * 获取entryInfo点赞量
     * @param entryId
     * @return
     */
    public Long getSelectEntryInfoStarAmount(Long entryId) {
        Entry2StarAndTreadAmountModel model = entry2StarAndTreadAmountModelMapperImpl.selectSingleByEntryId(entryId);
        return model.getEntryStarAmount();
    }
    /**
     * 获取entryInfo点踩量
     * @param entryId
     * @return
     */
    public Long getSelectEntryInfoTreadAmount(Long entryId) {
        Entry2StarAndTreadAmountModel model = entry2StarAndTreadAmountModelMapperImpl.selectSingleByEntryId(entryId);
        return model.getEntryTreadAmount();
    }

    /**
     * 获取所有热论词条
     * @return
     */
    public List<EntryShowModel> getSelectAllHotEntryInfo() {
        return entryShowModelMapperImpl.selectAllEntryInfoByType("hot");
    }
    /**
     * 随机获取3~8条词条信息
     * @return
     */
    public List<EntryShowModel> getSelectRandomMultiEntryInfo() {
        return entryShowModelMapperImpl.selectRandomMultiEntryInfo();
    }

    /**
     * 随机获取2~4条热门词条
     * @return
     */
    public List<EntryShowModel> getSelectRandomHotMultiEntryInfo() {
        return entryShowModelMapperImpl.selectRandomMultiHotEntryInfo();
    }
    /**
     * 获取官方词条
     * @param type: hot,official,nonofficial
     * @return
     */
    public List<EntryShowModel> getSelectAllEntryInfoWithType(String type) {
        return entryShowModelMapperImpl.selectAllEntryInfoByType(type);
    }
    /**
     * 单检测词条合法性
     * @param entryId
     * @return
     */
    public int postSelectDetectLegalityWithEntryId(Long entryId) {
        int ret = entryShowModelMapperImpl.selectSingleEntryInfoIsExist(entryId);
        return ret == 1 ? 200 : 0;
    }
    /**
     * 组检测词条合法性
     * @param entryIds
     * @return
     */
    public int postSelectDetectLegalityWithEntryIds(List<Long> entryIds) {
        int ret = entryShowModelMapperImpl.selectMultiEntryInfoIsExist(entryIds);
        return ret == entryIds.size() ? 1 : 0;
    }
    /**
     * 随机获取number条词条
     * @param type
     * @param number
     * @return
     */
    public List<EntryShowModel> getSelectRandomMultiEntryInfoByType(String type,Long number) {
        return entryShowModelMapperImpl.selectRandomMultiEntryInfoByType(type, number);
    }
    /**
     * 搜索符合条件的词条
     * @param entryName:词条名
     * @param entryType:词条类型hot,official,nonofficial
     * @return
     */
    public List<EntryShowModel> getSelectSearchMultiEntryInfoRpc(String entryName,String entryType) {
        return entryShowModelMapperImpl.selectSearchMultiEntryShowInfo(entryName,entryType);
    }
    /**
     * 词条查重
     * @param entryName
     * @return
     */
    public int postSelectDetectIsExistByEntryContent(String entryName) {
        return entryModelMapperImpl.selectDetectIsExistByEntry(entryName);
    }
    /**
     * 词条数量
     * @return
     */
    public Long getSelectAllEntryInfoPageAmount() {
        return entryShowModelMapperImpl.selectAllEntryAmount();
    }

//    /**
//     * 单删词条
//     * @param entryId
//     * @return
//     */
//    public int postDeleteSingleEntryInfo(Long entryId) {
//        return entryModelMapper.delete(entryId);
//    }
//    /**
//     * 组删词条
//     * @param entryIds
//     * @return
//     */
//    public int postDeleteMultiEntryInfo(List<Long> entryIds) {
//        return entryModelMapper.deleteMulti(entryIds);
//    }
}
