package org.leafbook.serviceapi.serviceApi.repository;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.userInfo.ResModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.repository.repositoryPage.ConsumableInfoAbs;
import org.leafbook.api.respAbs.repository.repositoryPage.ConsumableInfosResp;
import org.leafbook.api.testModel.repositoryPage.RepositoryTestModel;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.leafbook.utils.tools.Covert2Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@GlobalTransactional
@Service
public class RepositoryPageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    /**
     * 获取用户所有可用物品
     * @param userId
     * @param form:page
     * @return
     */
    public ConsumableInfosResp postSelectConsumableInfos(Long userId, Map<String,Long> form) {
        ConsumableInfosResp resp = new ConsumableInfosResp();

        Long page = form.get("page");
        if (Objects.isNull(page) || page <= 0) page = 1L;

        List<ConsumableInfoAbs> consumableInfoAbsList = new LinkedList<>();

        List<ResModel> resModelList = userServiceRpc.postSelectMultiUserResInfoByUserIdRpc(userId,page);
        if (Objects.nonNull(resModelList) && !resModelList.isEmpty()) {
            for (ResModel resModel:resModelList) {
                ConsumableInfoAbs consumableInfoAbs = new ConsumableInfoAbs();

                consumableInfoAbs.setBuyPrice(resModel.getPrice());
                consumableInfoAbs.setBuyTime(resModel.getCreateTime());
                consumableInfoAbs.setBillId(resModel.getBillId());
                consumableInfoAbs.setResId(resModel.getResId());
                consumableInfoAbs.setType(resModel.getResType());

                if (resModel.getResType() == 0) {
                    consumableInfoAbs.setTopicId(resModel.getTopicId());
                    TopicModel topicInfo = topicServiceRpc.getSelectSingleTopicInfoRpc(resModel.getTopicId());
                    if (Objects.nonNull(topicInfo)) {
                        consumableInfoAbs.setTopicTitle(topicInfo.getTopicTitle());
                    }
                } else {
                    consumableInfoAbs.setNickname(resModel.getNickname());
                }

                consumableInfoAbsList.add(consumableInfoAbs);
            }

        }

        resp.setConsumableInfoAbsList(consumableInfoAbsList);

        resp.setPage(userServiceRpc.postSelectMultiUserResInfoAmountByUserIdRpc(userId));
        return resp;
    }

    /**
     * 使用物品
     * @param userId
     * @param form:resId,newName
     * @return
     */
    public int postUpdateUseConsumable(Long userId,Map<String,String> form) {
        String resIdWord = form.get("resId");
        if (Objects.isNull(resIdWord) || !Covert2Tools.isDigital(resIdWord)) return 0;
        Long resId = Covert2Tools.covertToLong(resIdWord);

        ResModel resModel = userServiceRpc.postSelectSingleResInfoByUserIdRpc(userId, resId);
        if (Objects.isNull(resModel)) {
            return 0;
        }

        UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
        if (Objects.isNull(userModel)) {
            return 0;
        }

        Long balance = userModel.getBalance();

        int ret = 0;
        switch (resModel.getResType()) {
            case 0:
                ret = topicServiceRpc.postUpdateSingleTopicInfoWithOwnerRpc(userId,resModel.getTopicId());
                if (ret == 0) return 0;
                if (balance < Math.round(resModel.getPrice() * 0.1)) return 0;
                balance = balance - Math.round(resModel.getPrice() * 0.1);
                break;
            case 1:
                ret = userServiceRpc.postUseSingleNicknameResInfoRpc(userId, resId);
                if (ret == 0) return 0;
                if (balance < Math.round(resModel.getPrice() * 0.1)) return 0;
                balance = balance - Math.round(resModel.getPrice() * 0.1);
                break;
            case 2:
                String newName = form.get("newName");
                if (Objects.isNull(newName)) {
                    return 0;
                }
                ret = userServiceRpc.postUseSingleRenameCardResInfoRpc(userId, resId, newName);
                if (ret == 0) return 0;
                if (balance < 5) return 0;
                balance = balance - 5;
                break;
            default:
                break;
        }

        //更新余额
        ret = userServiceRpc.postUpdateSingleUserBalanceRpc(userId,balance);

        //删除该物品
        userServiceRpc.postDeleteSingleUserResInfoRpc(userId, resId);
        return ret;
    }
}
