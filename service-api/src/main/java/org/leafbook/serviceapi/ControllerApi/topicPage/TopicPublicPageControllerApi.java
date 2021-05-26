package org.leafbook.serviceapi.ControllerApi.topicPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.api.respAbs.topicPublicPage.topic.*;
import org.leafbook.serviceapi.serviceApi.topicPage.TopicPublicPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("topicPage.TopicPublicPageControllerApi")
@RestController
public class TopicPublicPageControllerApi {
    @Autowired
    private TopicPublicPageServiceApi topicPublicPageServiceApi;

    /**
     * 获取topicInfo
     *
     * @param userId
     * @param form:mainNumber,branchNumber,topicId
     * @return
     */
    @ApiOperation("/api/post/select/topicArticleInfo")
    @PostMapping("/api/post/select/topicArticleInfo")
    public TopicArticleInfoResp postSelectTopicArticleInfoApi(
            @RequestHeader("userId") Long userId,
            @RequestBody Map<String, Long> form
    ) {
        TopicArticleInfoResp resp = topicPublicPageServiceApi.getSelectTopicArticleInfo(userId, form);
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取topic相关信息
     *
     * @param userId
     * @param topicId
     * @return
     */
    @ApiOperation("/api/get/select/topicInfo")
    @GetMapping("/api/get/select/topicInfo")
    public TopicInfoResp getSelectTopicInfoApi(
            @RequestHeader("userId") Long userId,
            @RequestParam("topicId") Long topicId
    ) {
        TopicInfoResp resp = topicPublicPageServiceApi.getSelectTopicInfo(userId, topicId);
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取topic简易信息
     *
     * @param userId
     * @param topicId
     * @return
     */
    @ApiOperation("/api/get/select/topicRelatedInfo")
    @GetMapping("/api/get/select/topicRelatedInfo")
    public TopicRelatedInfoResp getSelectTopicRelatedInfoApi(
            @RequestHeader("userId") Long userId,
            @RequestParam("topicId") Long topicId
    ) {
        TopicRelatedInfoResp resp = topicPublicPageServiceApi.getSelectTopicRelatedInfo(userId, topicId);
        resp.setCode(200);
        return resp;
    }

    /**
     * 检测topic里的文章是否存在
     *
     * @param userId
     * @param form:mainNumber,branchNumber,topicId
     * @return
     */
    @ApiOperation("/api/post/select/detect/topicInfo")
    @PostMapping("/api/post/select/detect/topicInfo")
    public MessageResp postSelectDetectTopicInfoApi(
            @RequestHeader("userId") Long userId,
            @RequestBody Map<String, Long> form
    ) {
        MessageResp resp = new MessageResp();
        int ret = topicPublicPageServiceApi.postSelectDetectTopicInfo(userId, form);
        resp.setMsg(String.valueOf(ret));
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取当前主线的所有分支信息
     *
     * @param userId
     * @param form:mainNumber,topicId
     * @return
     */
    @ApiOperation("/api/post/select/branchInfos/by/mainNumber")
    @PostMapping("/api/post/select/branchInfos/by/mainNumber")
    public BranchInfosResp postSelectBranchInfosByMainNumberApi(
            @RequestHeader("userId") Long userId,
            @RequestBody Map<String, Long> form
    ) {
        BranchInfosResp resp = new BranchInfosResp();
        resp.setBranchAbsList(topicPublicPageServiceApi.postSelectBranchInfosByMainNumber(userId, form));
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取著述下全部主线文章
     *
     * @param userId
     * @param form:topicId,page
     * @return
     */
    @ApiOperation("/api/select/multi/mainInfos")
    @PostMapping("/api/select/multi/mainInfos")
    public MainInfosResp postSelectMultiMainInfosApi(
            @RequestHeader("userId") Long userId,
            @RequestBody Map<String, Long> form
    ) {
        MainInfosResp resp = topicPublicPageServiceApi.postSelectMultiMainInfos(userId, form);

        resp.setCode(200);
        return resp;
    }

    /**
     * 发布分支文章
     *
     * @param userId
     * @param form:mainNumber,content,entryIds,title,desc,topic
     * @return
     */
    @ApiOperation("/api/post/insert/single/branchArticle")
    @PostMapping("/api/post/insert/single/branchArticle")
    public PublicBranchArticleResp postInsertSingleBranchArticleApi(
            @RequestHeader("userId") Long userId,
            @RequestBody Map<String, String> form
    ) {
        PublicBranchArticleResp resp = topicPublicPageServiceApi.postInsertSingleBranchArticle(userId, form);
        resp.setCode(200);
        return resp;
    }

    /**
     * 发布主线文章
     *
     * @param userId
     * @param form:content,entryIds,title,desc,topic
     * @return
     */
    @ApiOperation("/api/post/insert/single/mainArticle")
    @PostMapping("/api/post/insert/single/mainArticle")
    public PublicMainArticleResp postInsertSingleMainArticleApi(
            @RequestHeader("userId") Long userId,
            @RequestBody Map<String, String> form
    ) {
        PublicMainArticleResp resp = topicPublicPageServiceApi.postInsertSingleMainArticle(userId, form);
        resp.setCode(200);
        return resp;
    }

    /**
     * 更新文章
     * @param userId
     * @param form:articleId,content
     * @return
     */
    @ApiOperation("/api/post/update/owner/article")
    @PostMapping("/api/post/update/owner/article")
    public MessageResp postUpdateOwnerArticleApi(
            @RequestHeader("userId") Long userId,
            @RequestBody Map<String, String> form
    ) {
        MessageResp resp = new MessageResp();
        int ret = topicPublicPageServiceApi.postUpdateOwnerArticle(userId, form);
        if (ret == 1) {
            resp.setCode(200);
            resp.setMsg("更新成功");
            return resp;
        } else {
            resp.setMsg("更新失败");
            resp.setCode(ret);
            return resp;
        }
    }

}


