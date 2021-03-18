package org.leafbook.serviceEntryApi.service;

import org.leafbook.api.modelApi.entryInfo.EntryModel;
import org.leafbook.serviceEntryApi.dao.EntryModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryRelatedServiceRpc {
    @Autowired
    private EntryModelMapper entryModelMapper;

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
    public EntryModel postSelectSingleEntryInfo(Long entryId) {
        return entryModelMapper.select(entryId);
    }
    /**
     * 组查询词条信息
     * @param entryIds
     * @return
     */
    public List<EntryModel> postSelectMultiEntryInfo(List<Long> entryIds) {
        return entryModelMapper.selectMulti(entryIds);
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
