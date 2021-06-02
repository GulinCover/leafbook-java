package org.leafbook.serviceMarketplaceApi.daoImpl;

import org.leafbook.api.annotation.RemainingProblem;
import org.leafbook.api.modelApi.billInfo.BillModel;
import org.leafbook.serviceMarketplaceApi.dao.BillModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class BillModelMapperImpl {
    @Autowired
    private BillModelMapper billModelMapper;
    /**
     * 查询用户账单
     * @param userId
     * @param page
     * @return
     */
    public List<BillModel> selectMultiBillInfoByUserId(Long userId, Long page) {
        Long end = 20;
        Long start = page * 20;
        return billModelMapper.selectMultiBillByUserId(userId,start,end);
    }
    /**
     * 查询用户账单总条数
     * @param userId
     * @return
     */
    public Long selectMultiBillInfoAmountByUserId(Long userId) {
        return billModelMapper.selectMultiBillAmountByUserId(userId);
    }
    /**
     * 获取用户竞拍成功的账单
     * @param userId
     * @param page
     * @return
     */
    @RemainingProblem
    public List<BillModel> selectMultiPhotographedBillInfoByUserId(Long userId, Long page) {
        return selectMultiBillInfoByUserId(userId,page);
    }
    /**
     * 获取用户竞拍成功的条数
     * @param userId
     * @return
     */
    @RemainingProblem
    public Long selectMultiPhotographedBillInfoAmountByUserId(Long userId) {
        return selectMultiBillInfoAmountByUserId(userId);
    }

    public int insert(BillModel billModel) {
        billModel.setBillId(IdGeneratorTools.nextId());
        billModel.setIsBlack(0);
        billModel.setVersion(1);
        return billModelMapper.insert(billModel);
    }
}
