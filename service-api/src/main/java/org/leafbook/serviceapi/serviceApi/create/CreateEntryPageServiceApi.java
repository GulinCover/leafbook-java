package org.leafbook.serviceapi.serviceApi.create;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@GlobalTransactional
@Service
public class CreateEntryPageServiceApi {
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    /**
     * 创建普通词条
     * @param userId
     * @param form: entryContent,entryDesc,
     *
     * @return
     */
    public int postCreateEntryInfo(Long userId, Map<String,String> form) {
        String entryContent = form.get("entryContent");
        if (Objects.isNull(entryContent)) return 4000;
        String entryDesc = form.get("entryDesc");
        if (Objects.isNull(entryDesc)) return 4000;

        int ret = entryServiceRpc.postSelectDetectIsExistByEntryContentRpc(entryContent);
        if (ret == 1) return 4004;
        ret = entryServiceRpc.postCreateSingleEntryInfoRpc(userId,entryContent,entryDesc,"nonofficial");
        return ret != 0 ? 200 : 500;
    }
}
