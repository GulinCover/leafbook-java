package org.leafbook.serviceapi.serviceApi.common;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.common.CommonEntryAbs;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.leafbook.utils.tools.Covert2Tools;
import org.leafbook.utils.tools.VerifyTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@GlobalTransactional
@Service
public class CommonServiceApi {
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    @Autowired
    private CommonServiceRpc commonServiceRpc;
    @Autowired
    private UserServiceRpc userServiceRpc;
    /**
     * 发送验证码
     * @param userId
     * @param form:email,type
     * @return
     */
    public int postSendCode(Long userId, Map<String,String> form) {
        final String email = form.get("email");
        final String type = form.get("type");
        if (!Covert2Tools.isDigital(type)) return 0;
        if (VerifyTools.EmailVerify(email)) {
            final String code = commonServiceRpc.postAcquireCodeRpc(email, Covert2Tools.covertToInteger(type));
            if (Objects.nonNull(code) && code.length() == 6) return 1;
            return commonServiceRpc.postSendCodeRpc(email, Covert2Tools.covertToInteger(type));
        }

        if (userId != 0) {
            UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
            final String code = commonServiceRpc.postAcquireCodeRpc(userModel.getEmail(), Covert2Tools.covertToInteger(type));
            if (Objects.nonNull(code) && code.length() == 6) return 1;
            return commonServiceRpc.postSendCodeRpc(userModel.getEmail(), Covert2Tools.covertToInteger(type));
        }

        return 0;
    }

    /**
     * 获取所有entryInfo
     * @param userId
     * @return
     */
    public List<CommonEntryAbs> getSelectAllEntryInfos(Long userId) {
        List<CommonEntryAbs> commonEntryAbsList = new LinkedList<>();

        List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectAllEntryInfoRpc();
        if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
            for (EntryShowModel entryShowModel:entryInfoList) {
                CommonEntryAbs commonEntryAbs = new CommonEntryAbs();
                commonEntryAbs.setEntryId(entryShowModel.getEntryId());
                commonEntryAbs.setEntryAvatar(entryShowModel.getEntryAvatar());
                commonEntryAbs.setEntryName(entryShowModel.getEntryName());
                commonEntryAbs.setEntryTitle(entryShowModel.getEntryAvatar());

                commonEntryAbsList.add(commonEntryAbs);
            }
        }

        return commonEntryAbsList;
    }
}
