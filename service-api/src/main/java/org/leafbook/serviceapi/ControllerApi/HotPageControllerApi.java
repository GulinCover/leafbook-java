package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import org.leafbook.api.respAbs.hotPage.AllTopicInfosResp;
import org.leafbook.serviceapi.serviceApi.HotPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("HotPageControllerApi")
@RestController
public class HotPageControllerApi {

    @Autowired
    private HotPageServiceApi hotPageServiceApi;


    //获取所有热论,每页15条数据
    @GetMapping("/api/get/select/all/hotTopicInfos")
    public AllTopicInfosResp getSelectAllHotTopicInfosApi(@RequestParam("page")Integer page) {
        AllTopicInfosResp resp = new AllTopicInfosResp();

        resp.setTopicInfoAbsList(hotPageServiceApi.getSelectAllHotTopicInfos(page));
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }
}
