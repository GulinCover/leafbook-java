package org.leafbook.serviceapi.serviceApi.repository;

import org.leafbook.api.modelApi.billInfo.BillModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.userInfo.ResModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.repository.publicCommentPage.BillAbs;
import org.leafbook.api.respAbs.repository.publicCommentPage.BillInfosResp;
import org.leafbook.api.respAbs.repository.publicTopicPage.EntryAbs;
import org.leafbook.api.testModel.repositoryPage.RepositoryTestModel;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.leafbook.serviceapi.serviceRpc.marketplaceService.MarketplaceServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class WalletPageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private CommonServiceRpc commonServiceRpc;
    @Autowired
    private MarketplaceServiceRpc marketplaceServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;

    public List<EntryAbs> postSelectPublicTopicInfoAllEntryList() {
        return RepositoryTestModel.createPublicTopicAllEntryList();
    }

    /**
     * 获取用户账单
     *
     * @param userId
     * @param form:page
     * @return
     */
    public BillInfosResp postSelectBillInfos(Long userId, Map<String, Long> form) {
        Long page = form.get("page");
        if (Objects.isNull(page) || page <= 0) page = 1L;

        BillInfosResp resp = new BillInfosResp();
        List<BillAbs> billAbsList = new LinkedList<>();
        UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
        if (Objects.nonNull(userModel)) {
            resp.setBalance(userModel.getBalance());
        }

        List<BillModel> billModelList = marketplaceServiceRpc.postSelectMultiBillInfoByUserIdRpc(userId, page);

        if (Objects.nonNull(billModelList) && !billModelList.isEmpty()) {
            for (BillModel billModel : billModelList) {
                BillAbs billAbs = new BillAbs();
                billAbs.setBillId(billModel.getBillId());

                billAbs.setBuyTime(billModel.getPublicTime());

                billAbs.setSellingTime(billModel.getPublicTime());

                billAbs.setGetPrice(billModel.getPrice());
                if (billModel.getBuyerUuid().equals(userModel.getUuid())) {
                    billAbs.setPositive(0);
                } else {
                    billAbs.setPositive(1);
                }
                billAbs.setBuyerUUID(billModel.getBuyerUuid());
                billAbs.setSellerUUID(billModel.getSellerUuid());


                ResModel resModel = userServiceRpc.postSelectSingleResInfoByUserIdRpc(userId, billModel.getResId());

                if (Objects.nonNull(resModel)) {
                    billAbs.setType(resModel.getType());

                    if (billModel.getType() == 0) {
                        billAbs.setTopicId(resModel.getTopicId());
                        TopicModel topicInfo = topicServiceRpc.getSelectSingleTopicInfoRpc(resModel.getTopicId());

                        if (Objects.nonNull(topicInfo)) {
                            billAbs.setTopicTitle(topicInfo.getTopicTitle());
                        }

                    } else if (resModel.getType() == 1) {
                        billAbs.setNickname(resModel.getNickname());
                    }

                }

                billAbsList.add(billAbs);
            }
        }

        Long maxPage = marketplaceServiceRpc.postSelectMultiBillInfoByUserIdPageRpc(userId);
        resp.setPage((long)Math.ceil(maxPage / 20));
        resp.setBillAbsList(billAbsList);
        return resp;
    }
}
