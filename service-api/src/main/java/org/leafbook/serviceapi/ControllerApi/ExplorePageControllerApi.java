package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.explorePage.*;
import org.leafbook.serviceapi.serviceApi.ExplorePageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin("*")
@Api("ExplorePageController")
@RestController
public class ExplorePageControllerApi {
    @Autowired
    private ExplorePageServiceApi explorePageServiceApi;

    /**
     * 获取用户信息，著述回复的数量，评论回复的数量，议论回复的数量
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/userReplyData")
    @PostMapping("/api/post/select/userReplyData")
    public UserReplyDataResp postSelectUserReplyDataApi(@RequestHeader("userId")Long userId) {
        UserReplyDataResp resp = explorePageServiceApi.postSelectUserReplyNumber(userId);
        resp.setCode(200);
        return resp;
    }

    /**
     * 随机获取3~8条词条信息
     * @param userId:
     * @return
     */
    @ApiOperation("/api/post/select/random/entryInfos")
    @PostMapping("/api/post/select/random/entryInfos")
    public EntryInfosResp postSelectEntryInfosApi(
            @RequestHeader("userId")Long userId
    ) {
        EntryInfosResp resp = new EntryInfosResp();
        resp.setEntryAbsList(explorePageServiceApi.postSelectEntryInfos(userId));
        resp.setCode(200);
        return resp;
    }

    /**
     * 根据词条随机查询5~8条著述
     * @param form: entryIds?List<Long>,
     * @return
     */
    @ApiOperation("/api/post/select/random/topicInfos/by/entryInfos")
    @PostMapping("/api/post/select/random/topicInfos/by/entryInfos")
    public TopicInfosResp postSelectTopicInfosByEntryIdsApi(@RequestBody Map<String,List<Long>> form) {
        TopicInfosResp resp = new TopicInfosResp();

        resp.setTopicAbsList(explorePageServiceApi.postSelectTopicInfosByEntryIds(form));
        resp.setCode(200);
        return resp;
    }

    /**
     * 随机推荐一条词条
     *
     * @return isLeft为0，单条数据；isLeft为1，及其相关数据；
     */
    @ApiOperation("/api/get/select/random/entryInfo")
    @GetMapping("/api/get/select/random/entryInfo")
    public EntryInfoResp getSelectSingleEntryInfoRecommendedApi() {
//        EntryInfoResp resp = new EntryInfoResp();
//        resp.setEntryAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");
//        resp.setEntryDesc("this is a desc");
//        resp.setEntryId(10L);
//        resp.setEntryName("Alex");
//        resp.setIsLeft("1");
        EntryInfoResp resp = explorePageServiceApi.getSelectSingleEntryInfoRecommended();
        resp.setCode(200);
        return resp;
    }

    /**
     * 随机推荐一条词条,及相关词条
     * @return isLeft为0，单条数据；isLeft为1，及其相关数据；
     */
    @ApiOperation("/api/get/select/random/entryInfo/and/related")
    @GetMapping("/api/get/select/random/entryInfo/and/related")
    public EntryInfoResp getSelectEntryInfoRecommendedAndRelatedApi() {
//        EntryInfoResp resp = new EntryInfoResp();
//        resp.setEntryAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");
//        resp.setEntryDesc("this is a desc");
//        resp.setEntryId(10L);
//        resp.setEntryName("Alex");
//        resp.setIsLeft("0");
//        resp.setEntryAbsList(explorePageServiceApi.getSelectEntryInfosSimple());
//        resp.setCode(200);
        EntryInfoResp resp = explorePageServiceApi.getSelectEntryInfoRecommendedAndRelated();
        resp.setCode(200);
        return resp;
    }

    /**
     * 随机获取热门2~4条词条
     * @param form:userId?可传可不传
     * @return
     */
    @ApiOperation("/api/post/select/random/hot/entryInfos")
    @PostMapping("/api/post/select/random/hot/entryInfos")
    public EntryInfosResp postSelectHotEntryInfosApi(@RequestBody Map<String,Long> form) {
        EntryInfosResp resp = new EntryInfosResp();

        resp.setEntryAbsList(explorePageServiceApi.postSelectHotEntryInfos(form));
        resp.setCode(200);
        return resp;
    }

}
