package org.leafbook.serviceapi.serviceApi.repository;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.billInfo.BillModel;
import org.leafbook.api.modelApi.userInfo.ResModel;
import org.leafbook.api.respAbs.repository.successPage.SuccessAbs;
import org.leafbook.api.respAbs.repository.successPage.SuccessInfoResp;
import org.leafbook.serviceapi.serviceRpc.marketplaceService.MarketplaceServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@GlobalTransactional
@Service
public class SuccessPageServiceApi {
    @Autowired
    private MarketplaceServiceRpc marketplaceServiceRpc;
    @Autowired
    private UserServiceRpc userServiceRpc;
    /**
     * 获取用户竞拍成功的账单
     * @param userId
     * @param form:page
     * @return
     */
    public SuccessInfoResp postSelectMultiPhotographedSuccessInfo(Long userId, Map<String, Long> form) {
        SuccessInfoResp resp = new SuccessInfoResp();
        List<SuccessAbs> successAbsList = new LinkedList<>();

        Long page = form.get("page");
        if (Objects.isNull(page) || page <= 0) page = 1L;

        List<BillModel> billModelList = marketplaceServiceRpc.postSelectMultiPhotographedBillInfoByUserIdRpc(userId,page);
        if (Objects.nonNull(billModelList) && !billModelList.isEmpty()) {
            for (BillModel billModel:billModelList) {
                SuccessAbs successAbs = new SuccessAbs();
                successAbs.setBillId(billModel.getBillId());
                successAbs.setType(billModel.getBillType());

                successAbs.setStartPrice(billModel.getStartPrice());
                successAbs.setPhotographedPrice(billModel.getPrice());
                successAbs.setPhotographedTime(billModel.getBuyTime());

                ResModel resModel = userServiceRpc.postSelectSingleResInfoByUserIdRpc(userId, billModel.getResId());
                if (Objects.isNull(resModel)) return resp;

                switch (billModel.getBillType()) {
                    case 0:
                        successAbs.setTopicId(resModel.getTopicId());
                        break;
                    case 1:
                        successAbs.setNickname(resModel.getNickname());
                        break;
                    default:
                        break;
                }

                successAbsList.add(successAbs);
            }
        }

        resp.setSuccessAbsList(successAbsList);

        Long maxPage = marketplaceServiceRpc.postSelectMultiPhotographedBillInfoByUserIdPageRpc(userId);
        resp.setPage(maxPage);
        return resp;
    }
}
