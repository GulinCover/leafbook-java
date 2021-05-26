package org.leafbook.serviceEntryApi.daoImpl;

import org.leafbook.api.modelApi.entryInfo.Entry2StarAndTreadAmountModel;
import org.leafbook.serviceEntryApi.dao.Entry2StarAndTreadAmountModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class Entry2StarAndTreadAmountModelMapperImpl {
    @Autowired
    private Entry2StarAndTreadAmountModelMapper entry2StarAndTreadAmountModelMapper;
    /**
     * 单查询
     * @param entryId
     * @return
     */
    public Entry2StarAndTreadAmountModel selectSingleByEntryId(Long entryId) {
        return entry2StarAndTreadAmountModelMapper.selectSingleEntryStarAndTreadAmountByEntryId(entryId);
    }
    /**
     * 组查询
     * @param entryIds
     * @return
     */
    public List<Entry2StarAndTreadAmountModel> selectMultiByEntryIds(List<Long> entryIds) {
        return entry2StarAndTreadAmountModelMapper.selectMultiEntryStarAndTreadAmountByEntryId(entryIds);
    }

    /**
     * 更新点赞
     * @param entryId
     * @return
     */
    public int updateStarByEntryId(Long entryId) {
        return entry2StarAndTreadAmountModelMapper.updateStarIncreaseByEntryId(entryId,new Date().getTime());
    }

    /**
     * 更新点踩
     * @param entryId
     * @return
     */
    public int updateTreadByEntryId(Long entryId) {
        return entry2StarAndTreadAmountModelMapper.updateTreadIncreaseByEntryId(entryId,new Date().getTime());
    }
}





