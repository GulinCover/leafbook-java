package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.explorePage.*;
import org.leafbook.serviceapi.serviceApi.ExplorePageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@CrossOrigin("*")
@Api("ExplorePageController")
@RestController
public class ExplorePageControllerApi {
    @Autowired
    private ExplorePageServiceApi explorePageServiceApi;

    //获取用户信息，著述回复的数量，评论回复的数量，议论回复的数量
    @ApiOperation("/api/post/select/userReplyData")
    @PostMapping("/api/post/select/userReplyData")
    public UserReplyDataResp postSelectUserReplyDataApi(@RequestHeader("user_id")Long userId) {
        UserReplyDataResp resp = explorePageServiceApi.postSelectUserReplyNumber(userId);
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //随机获取几条词条信息
    @ApiOperation("/api/get/select/random/entryInfos")
    @GetMapping("/api/get/select/random/entryInfos")
    public EntryInfosResp getSelectEntryInfosApi() {
        EntryInfosResp resp = new EntryInfosResp();

        resp.setEntryAbsList(explorePageServiceApi.getSelectEntryInfos());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //根据词条随机查询几条著述
    @ApiOperation("/api/get/select/random/topicInfos/by/entryInfos")
    @GetMapping("/api/get/select/random/topicInfos/by/entryInfos")
    public TopicInfosResp getSelectTopicInfosByEntryIdsApi(@RequestParam("entry_id_list") List<Long> entryIds) {
        TopicInfosResp resp = new TopicInfosResp();

        resp.setTopicAbsList(explorePageServiceApi.getSelectTopicInfosByEntryIds(entryIds));
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //随机推荐一条词条
    @ApiOperation("/api/get/select/random/entryInfo")
    @GetMapping("/api/get/select/random/entryInfo")
    public EntryInfoResp getSelectSingleEntryInfoRecommendedApi() {
        EntryInfoResp resp = new EntryInfoResp();
        resp.setEntryAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");
        resp.setEntryDesc("this is a desc");
        resp.setEntryId(10L);
        resp.setEntryName("Alex");
        resp.setIsLeft("1");
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //随机推荐一条词条,及相关词条
    @ApiOperation("/api/get/select/random/entryInfo/and/related")
    @GetMapping("/api/get/select/random/entryInfo/and/related")
    public EntryInfoResp getSelectEntryInfoRecommendedApi() {
        EntryInfoResp resp = new EntryInfoResp();
        resp.setEntryAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");
        resp.setEntryDesc("this is a desc");
        resp.setEntryId(10L);
        resp.setEntryName("Alex");
        resp.setIsLeft("0");
        resp.setEntryAbsList(explorePageServiceApi.getSelectEntryInfosSimple());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //随机获取最热几条词条
    @ApiOperation("/api/get/select/random/hot/entryInfos")
    @GetMapping("/api/get/select/random/hot/entryInfos")
    public EntryInfosResp getSelectHotEntryInfosApi() {
        EntryInfosResp resp = new EntryInfosResp();

        resp.setEntryAbsList(explorePageServiceApi.getSelectEntryInfos());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

}
