package org.leafbook.serviceMarketplaceApi.dao;

import org.leafbook.api.modelApi.billInfo.BidingModel;
import org.leafbook.api.modelApi.billInfo.BillModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class BillModelMapper {
    /**
     * 查询用户账单
     * @param userId
     * @param page
     * @return
     */
    public List<BillModel> selectMultiBillInfoByUserId(Long userId, Long page) {
        List<BillModel> billModelList = new LinkedList<>();

        for (int i = 0; i < 8; i++) {
            BillModel billModel = new BillModel();
            billModel.setSellerUuid("ewq1-1321-eqwe-rqw2");
            billModel.setSellerId(1L);
            billModel.setPrice(312312L);
            billModel.setStartPrice(31232L);
            billModel.setBuyerUuid("eqw2-weqr-3213-rqw2");
            billModel.setBuyerId(2L);
            billModel.setResId(312L);
            billModel.setBuyTime(2312323L);
            billModel.setBillId(3123L);
            billModel.setPublicTime(21312321L);
            billModel.setType(0);

            billModelList.add(billModel);
        }

        return billModelList;
    }
    /**
     * 查询用户账单总条数
     * @param userId
     * @return
     */
    public Long selectMultiBillInfoAmountByUserId(Long userId) {
        return (long)new Random().nextInt(500);
    }
    /**
     * 获取用户竞拍成功的账单
     * @param userId
     * @param page
     * @return
     */
    public List<BillModel> selectMultiPhotographedBillInfoByUserId(Long userId, Long page) {
        List<BillModel> billModelList = new LinkedList<>();

        for (int i = 0; i < 8; i++) {
            BillModel billModel = new BillModel();
            billModel.setSellerUuid("ewq1-1321-eqwe-rqw2");
            billModel.setSellerId(1L);
            billModel.setPrice(312312L);
            billModel.setStartPrice(31232L);
            billModel.setBuyerUuid("eqw2-weqr-3213-rqw2");
            billModel.setBuyerId(2L);
            billModel.setResId(312L);
            billModel.setBuyTime(2312323L);
            billModel.setBillId(3123L);
            billModel.setPublicTime(21312321L);
            billModel.setType(0);

            billModelList.add(billModel);
        }

        return billModelList;
    }
    /**
     * 获取用户竞拍成功的条数
     * @param userId
     * @return
     */
    public Long selectMultiPhotographedBillInfoAmountByUserId(Long userId) {
        return (long)new Random().nextInt(500);
    }

    public int insert(BillModel billModel) {
        return new Random().nextInt(100);
    }
}
