package org.leafbook.serviceEntryApi.service;

import org.leafbook.api.modelApi.entryInfo.Entry2StarAndTreadAmountModel;
import org.leafbook.api.modelApi.entryInfo.EntryModel;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.serviceEntryApi.dao.Entry2StarAndTreadAmountModelMapper;
import org.leafbook.serviceEntryApi.dao.EntryModelMapper;
import org.leafbook.serviceEntryApi.dao.EntryShowModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryRelatedServiceRpc {
    @Autowired
    private EntryModelMapper entryModelMapper;
    @Autowired
    private EntryShowModelMapper entryShowModelMapper;
    @Autowired
    private Entry2StarAndTreadAmountModelMapper entry2StarAndTreadAmountModelMapper;

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
            return entryModelMapper.insert(userId,entryName,entryDesc,"hot");
        } else {
            return entryModelMapper.insert(userId,entryName,entryDesc,"nonofficial");
        }
    }
    /**
     * 单一查询词条信息
     * @param entryId
     * @return
     */
    public EntryShowModel getSelectSingleEntryInfo(Long entryId) {
        return entryShowModelMapper.selectSingleByEntryId(entryId);
    }
    /**
     * 组查询词条信息
     * @param entryIds
     * @return
     */
    public List<EntryShowModel> getSelectMultiEntryInfo(List<Long> entryIds) {
        return entryShowModelMapper.selectMultiByEntryId(entryIds);
    }
    /**
     * 获取全部entryInfo通过页号
     * @param page
     * @return
     */
    public List<EntryShowModel> getSelectAllEntryInfo(Long page) {
        return entryShowModelMapper.selectAllByPage(page);
    }

    /**
     * 获取全部entryInfo
     * @return
     */
    public List<EntryShowModel> getSelectAllEntryInfo() {
        return entryShowModelMapper.selectAllEntryInfo();
    }

    /**
     * entryInfo点赞量+1
     * @param entryId
     * @return
     */
    public int postUpdateEntryInfoStarAmount(Long entryId) {
        Entry2StarAndTreadAmountModel model = entry2StarAndTreadAmountModelMapper.selectByEntryId(entryId);
        Long entryStarAmount = model.getEntryStarAmount();
        model.setEntryStarAmount(entryStarAmount + 1);
        return entry2StarAndTreadAmountModelMapper.updateByModel(model);
    }

    /**
     * entryInfo点踩量+1
     * @param entryId
     * @return
     */
    public int postUpdateEntryInfoTreadAmount(Long entryId) {
        Entry2StarAndTreadAmountModel model = entry2StarAndTreadAmountModelMapper.selectByEntryId(entryId);
        Long entryTreadAmount = model.getEntryTreadAmount();
        model.setEntryTreadAmount(entryTreadAmount + 1);
        return entry2StarAndTreadAmountModelMapper.updateByModel(model);
    }
    /**
     * 获取entryInfo点赞量
     * @param entryId
     * @return
     */
    public Long getSelectEntryInfoStarAmount(Long entryId) {
        Entry2StarAndTreadAmountModel model = entry2StarAndTreadAmountModelMapper.selectByEntryId(entryId);
        return model.getEntryStarAmount();
    }
    /**
     * 获取entryInfo点踩量
     * @param entryId
     * @return
     */
    public Long getSelectEntryInfoTreadAmount(Long entryId) {
        Entry2StarAndTreadAmountModel model = entry2StarAndTreadAmountModelMapper.selectByEntryId(entryId);
        return model.getEntryTreadAmount();
    }

    /**
     * 获取所有热论词条
     * @return
     */
    public List<EntryShowModel> getSelectAllHotEntryInfo() {
        return entryModelMapper.selectAllHotEntryInfo();
    }
    /**
     * 随机获取3~8条词条信息
     * @return
     */
    public List<EntryShowModel> getSelectRandomMultiEntryInfo() {
        return entryModelMapper.selectRandomMultiEntryInfo();
    }

    /**
     * 判断词条用户是否点过赞
     * @param userId
     * @param entryId
     * @return
     */
    public Integer postSelectIsLikedWithUserId(Long userId,Long entryId) {
        return entryModelMapper.selectIsLikedWithUserId(userId,entryId);
    }
    /**
     * 随机获取2~4条热门词条
     * @return
     */
    public List<EntryShowModel> getSelectRandomHotMultiEntryInfo() {
        return entryModelMapper.selectRandomMultiHotEntryInfo();
    }
    /**
     * 获取官方词条
     * @param type: hot,official,nonofficial
     * @return
     */
    public List<EntryShowModel> getSelectAllEntryInfoWithType(String type) {
        return entryModelMapper.selectAllEntryInfoByType(type);
    }
    /**
     * 单检测词条合法性
     * @param entryId
     * @return
     */
    public int postSelectDetectLegalityWithEntryId(Long entryId) {
        int ret = entryModelMapper.selectSingleEntryInfoIsExist(entryId);
        return ret == 1 ? 200 : 0;
    }
    /**
     * 组检测词条合法性
     * @param entryIds
     * @return
     */
    public int postSelectDetectLegalityWithEntryIds(List<Long> entryIds) {
        int ret = entryModelMapper.selectMultiEntryInfoIsExist(entryIds);
        return ret == entryIds.size() ? 200 : 0;
    }
    /**
     * 随机获取number条词条
     * @param type
     * @param number
     * @return
     */
    public List<EntryShowModel> getSelectRandomMultiEntryInfoByType(String type,Integer number) {
        return entryModelMapper.selectRandomMultiEntryInfoByType(type,number);
    }
    /**
     * 搜索符合条件的词条
     * @param entryName:词条名
     * @param entryType:词条类型hot,official,nonofficial
     * @return
     */
    public List<EntryShowModel> getSelectSearchMultiEntryInfoRpc(String entryName,String entryType) {
        return entryModelMapper.selectSearchMultiEntryShowInfo(entryName,entryType);
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
