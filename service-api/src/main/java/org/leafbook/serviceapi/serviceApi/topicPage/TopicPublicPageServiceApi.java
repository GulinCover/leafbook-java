package org.leafbook.serviceapi.serviceApi.topicPage;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.dto.topicService.ArticleAbs;
import org.leafbook.api.modelApi.entryInfo.EntryShowModel;
import org.leafbook.api.modelApi.topicInfo.DirectoryModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.topicPublicPage.manager.TopicManagerAbs;
import org.leafbook.api.respAbs.topicPublicPage.topic.*;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.leafbook.serviceapi.serviceRpc.entryService.EntryServiceRpc;
import org.leafbook.serviceapi.serviceRpc.recordService.RecordServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.leafbook.utils.tools.Covert2Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@GlobalTransactional
@Service
public class TopicPublicPageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private EntryServiceRpc entryServiceRpc;
    @Autowired
    private RecordServiceRpc recordServiceRpc;

    public int a = 0;

    /**
     * 获取topicInfo
     *
     * @param userId
     * @param form:mainNumber,branchNumber,topicId
     * @return
     */
    public TopicArticleInfoResp getSelectTopicArticleInfo(Long userId, Map<String, Long> form) {
        TopicArticleInfoResp resp = new TopicArticleInfoResp();
        Long mainNumber = form.get("mainNumber");
        Long branchNumber = form.get("branchNumber");
        Long topicId = form.get("topicId");
        if (Objects.isNull(mainNumber) || mainNumber < 0L) {
            mainNumber = 1L;
        }
        if (Objects.isNull(branchNumber) || branchNumber < 0L) {
            branchNumber = 1L;
        }
        if (Objects.isNull(topicId) || topicId <= 0L) {
            return resp;
        }

        //增加浏览记录
        if (userId != 0) {
            recordServiceRpc.postInsertSingleBrowseHistoryInfoRpc(userId, topicId);
        }

        ArticleModel articleModel = topicServiceRpc.getSelectSingleArticleByTopicIdAndNumberRpc(topicId, mainNumber, branchNumber);
        if (Objects.nonNull(articleModel)) {
            resp.setPublicTime(articleModel.getCreateTime());

            resp.setArticleId(articleModel.getArticleId());
            resp.setArticleDesc(articleModel.getArticleDesc());
            resp.setArticleContent(articleModel.getArticleContent());
            resp.setArticleTitle(articleModel.getArticleTitle());

            resp.setMainNumber(articleModel.getMainNumber());
            resp.setBranchNumber(articleModel.getBranchNumber());

            List<Long> articleEntryIds = topicServiceRpc.getSelectMultiEntryIdsByArticleIdRpc(articleModel.getArticleId());
            List<ArticleEntryAbs> articleEntryAbsList = new LinkedList<>();
            if (Objects.nonNull(articleEntryIds) && !articleEntryIds.isEmpty()) {
                List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectMultiEntryInfoRpc(articleEntryIds);
                if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
                    for (EntryShowModel entryShowModel : entryInfoList) {
                        ArticleEntryAbs articleEntryAbs = new ArticleEntryAbs();
                        articleEntryAbs.setEntryId(entryShowModel.getEntryId());
                        articleEntryAbs.setEntryName(entryShowModel.getEntryName());

                        articleEntryAbsList.add(articleEntryAbs);
                    }
                }
            }
            resp.setArticleEntryAbsList(articleEntryAbsList);

            UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(articleModel.getUserId());
            if (Objects.nonNull(userModel)) {
                resp.setUserId(userModel.getId());
                resp.setUserAvatar(userModel.getAvatar());
                resp.setUserLevel(userModel.getLevel());
                resp.setUsername(userModel.getUsername());
            }
        }

        resp.setIsOwner(topicServiceRpc.getSelectTopicOwnerRpc(topicId).equals(userId) ? 1 : 0);

        return resp;
    }

    /**
     * 获取topic相关信息
     *
     * @param userId
     * @param topicId
     * @return
     */
    public TopicInfoResp getSelectTopicInfo(Long userId, Long topicId) {
        TopicInfoResp resp = new TopicInfoResp();

        TopicModel topicInfo = topicServiceRpc.getSelectSingleTopicInfoRpc(topicId);

        if (Objects.nonNull(topicInfo)) {
            resp.setTopicTitle(topicInfo.getTopicTitle());

            List<Long> topicIds = topicServiceRpc.getSelectSingleTopicInfoForEntryIdsRpc(topicId);
            List<TopicEntryAbs> topicEntryAbsList = new LinkedList<>();
            if (Objects.nonNull(topicIds) && !topicIds.isEmpty()) {
                List<EntryShowModel> entryInfoList = entryServiceRpc.getSelectMultiEntryInfoRpc(topicIds);
                if (Objects.nonNull(entryInfoList) && !entryInfoList.isEmpty()) {
                    for (EntryShowModel entryShowModel : entryInfoList) {
                        TopicEntryAbs topicEntryAbs = new TopicEntryAbs();
                        topicEntryAbs.setEntryId(entryShowModel.getEntryId());
                        topicEntryAbs.setEntryName(entryShowModel.getEntryName());

                        topicEntryAbsList.add(topicEntryAbs);
                    }
                }
            }
            resp.setTopicEntryAbsList(topicEntryAbsList);

            ArticleModel articleInfo = topicServiceRpc.getSelectLastTimeArticleInfoByTopicIdRpc(topicId);
            if (Objects.nonNull(articleInfo)) {
                resp.setLastArticleId(articleInfo.getArticleId());
                resp.setLastArticleTitle(articleInfo.getArticleTitle());
                resp.setLastBranchNumber(articleInfo.getBranchNumber());
                resp.setLastMainNumber(articleInfo.getMainNumber());
                resp.setLastArticlePublicTime(articleInfo.getCreateTime());
                resp.setUpdateTime(articleInfo.getUpdateTime());
            }

            List<Long> managerIds = topicServiceRpc.getSelectTopicManagerRpc(topicId);
            List<TopicManagerAbs> managerAbsList = new LinkedList<>();
            if (Objects.nonNull(managerIds) && !managerIds.isEmpty()) {
                List<UserModel> userModelList = userServiceRpc.postSelectMultiUserInfoRpc(managerIds);
                if (Objects.nonNull(userModelList) && !userModelList.isEmpty()) {
                    for (UserModel userModel : userModelList) {
                        TopicManagerAbs managerAbs = new TopicManagerAbs();
                        managerAbs.setUserAvatar(userModel.getAvatar());
                        managerAbs.setUsername(userModel.getUsername());
                        managerAbs.setUserDesc(userModel.getUserDesc());
                        managerAbs.setUuid(userModel.getUuid());

                        managerAbsList.add(managerAbs);
                    }
                }
            }
            resp.setManagerAbsList(managerAbsList);

            resp.setArticleAmount(topicServiceRpc.getSelectArticleAmountByTopicIdRpc(topicId));

            UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(topicInfo.getUserId());
            if (Objects.nonNull(userModel)) {
                resp.setUserAvatar(userModel.getAvatar());
                resp.setUserId(userModel.getId());
                resp.setUsername(userModel.getUsername());
                resp.setUserLevel(userModel.getLevel());
            }

        }
        return resp;
    }

    /**
     * 获取topic简易信息
     *
     * @param userId
     * @param topicId
     * @return
     */
    public TopicRelatedInfoResp getSelectTopicRelatedInfo(Long userId, Long topicId) {
        TopicRelatedInfoResp resp = new TopicRelatedInfoResp();
        TopicModel topicInfo = topicServiceRpc.getSelectSingleTopicInfoRpc(topicId);
        if (Objects.nonNull(topicInfo)) {
            resp.setTopicId(topicId);
            resp.setTopicTitle(topicInfo.getTopicTitle());

            UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(topicInfo.getUserId());
            if (Objects.nonNull(userModel)) {
                resp.setUserId(userModel.getId());
                resp.setUsername(userModel.getUsername());
            }

            resp.setStarAmount(topicServiceRpc.getSelectTopicStarAmountRpc(topicId));

            resp.setBrowseAmount(recordServiceRpc.getSelectTopicBrowseInfoAmountByTopicIdRpc(topicId));
        }

        return resp;
    }

    /**
     * 检测topic里的文章是否存在
     *
     * @param userId
     * @param form:mainNumber,branchNumber,topicId
     * @return
     */
    public int postSelectDetectTopicInfo(Long userId, Map<String, Long> form) {
        Long mainNumber = form.get("mainNumber");
        Long branchNumber = form.get("branchNumber");
        Long topicId = form.get("topicId");
        if (Objects.isNull(mainNumber) || mainNumber < 0L) {
            return 0;
        }
        if (Objects.isNull(branchNumber) || branchNumber < 0L) {
            return 0;
        }
        if (Objects.isNull(topicId) || topicId <= 0L) {
            return 0;
        }

        return topicServiceRpc.getSelectIsExistTopicArticleInfoRpc(userId, mainNumber, branchNumber);
    }

    /**
     * 获取当前主线的所有分支信息
     *
     * @param userId
     * @param form:mainNumber,topicId
     * @return
     */
    public List<BranchAbs> postSelectBranchInfosByMainNumber(Long userId, Map<String, Long> form) {
        List<BranchAbs> branchAbsList = new LinkedList<>();
        Long mainNumber = form.get("mainNumber");
        Long topicId = form.get("topicId");

        if (Objects.isNull(mainNumber) || mainNumber < 0L) {
            return branchAbsList;
        }

        if (Objects.isNull(topicId) || topicId <= 0L) {
            return branchAbsList;
        }

        List<ArticleModel> articleModelList = topicServiceRpc.getSelectAllBranchInfoByMainNumberRpc(topicId, mainNumber);
        if (Objects.nonNull(articleModelList) && !articleModelList.isEmpty()) {
            for (ArticleModel articleModel : articleModelList) {
                BranchAbs branchAbs = new BranchAbs();
                branchAbs.setArticleId(articleModel.getArticleId());
                branchAbs.setArticleTitle(articleModel.getArticleTitle());
                branchAbs.setBranchNumber(articleModel.getBranchNumber());
                branchAbs.setMainNumber(articleModel.getMainNumber());

                branchAbsList.add(branchAbs);
            }
        }
        return branchAbsList;
    }

    /**
     * 获取著述下全部主线文章
     *
     * @param userId
     * @param form:topicId,page
     * @return
     */
    public MainInfosResp postSelectMultiMainInfos(Long userId, Map<String, Long> form) {
        MainInfosResp resp = new MainInfosResp();
        List<MainAbs> mainAbsList = new LinkedList<>();

        Long topicId = form.get("topicId");
        Long page = form.get("page");
        if (Objects.isNull(topicId) || topicId <= 0L) {
            return resp;
        }
        if (Objects.isNull(page) || page <= 0L) {
            page = 1L;
        }

        List<ArticleModel> articleModelList = topicServiceRpc.getSelectMultiMainInfosRpc(topicId, page);
        if (Objects.nonNull(articleModelList) && !articleModelList.isEmpty()) {
            for (ArticleModel articleModel : articleModelList) {
                MainAbs mainAbs = new MainAbs();
                mainAbs.setArticleId(articleModel.getArticleId());
                mainAbs.setArticleTitle(articleModel.getArticleTitle());
                mainAbs.setBranchNumber(articleModel.getBranchNumber());
                mainAbs.setMainNumber(articleModel.getMainNumber());

                mainAbsList.add(mainAbs);
            }
        }

        resp.setMainAbsList(mainAbsList);

        resp.setPage(topicServiceRpc.getSelectMultiMainInfoAmountRpc(topicId));
        return resp;
    }


    /**
     * 发布分支文章
     *
     * @param userId
     * @param form:mainNumber,content,entryIds,title,desc,topicId
     * @return
     */
    public PublicBranchArticleResp postInsertSingleBranchArticle(Long userId, Map<String, String> form) {
        PublicBranchArticleResp resp = new PublicBranchArticleResp();
        String mainNumberStr = form.get("mainNumber");
        String content = form.get("content");
        String entryIdsStr = form.get("entryIds");
        String title = form.get("title");
        String desc = form.get("desc");
        String topicIdStr = form.get("topicId");

        if (Objects.isNull(mainNumberStr) || !Covert2Tools.isDigital(mainNumberStr)) {
            return resp;
        }

        if (Objects.isNull(topicIdStr) || !Covert2Tools.isDigital(topicIdStr)) {
            return resp;
        }

        if (Objects.isNull(content)) {
            return resp;
        }

        if (Objects.isNull(title)) {
            return resp;
        }

        if (Objects.isNull(desc)) {
            return resp;
        }

        if (Objects.isNull(entryIdsStr)) {
            return resp;
        }

        Long topicId = Covert2Tools.covertToLong(topicIdStr);
        Long mainNumber = Covert2Tools.covertToLong(mainNumberStr);

        List<Long> entryIds = new LinkedList<>();
        for (String entryId : entryIdsStr.split(";")) {
            if (!"" .equals(entryId) && Covert2Tools.isDigital(entryId)) {
                entryIds.add(Covert2Tools.covertToLong(entryId));
            }
        }

        ArticleModel articleModel = new ArticleModel();
        articleModel.setUserId(userId);
        articleModel.setMainNumber(mainNumber);
        articleModel.setTopicId(topicId);
        articleModel.setArticleTitle(title);
        articleModel.setArticleDesc(desc);
        articleModel.setArticleContent(content);
        Map<String, Long> map = topicServiceRpc.postPublicBranchArticleByOtherReturnBranchNumberRpc(articleModel);
        Long branchNumber = map.get("branchNumber");
        Long articleId = map.get("articleId");

        if (Objects.isNull(branchNumber)) {
            return resp;
        }
        if (Objects.nonNull(articleId) && articleId > 0) {
            topicServiceRpc.postAddMultiArticleEntryIdsRpc(articleId, entryIds);
            resp.setBranchNumber(branchNumber);
            return resp;
        }
        return resp;
    }

    /**
     * 发布主线文章
     *
     * @param userId
     * @param form:content,entryIds,title,desc,topic
     * @return
     */
    public PublicMainArticleResp postInsertSingleMainArticle(Long userId, Map<String, String> form) {
        PublicMainArticleResp resp = new PublicMainArticleResp();
        String content = form.get("content");
        String entryIdsStr = form.get("entryIds");
        String title = form.get("title");
        String desc = form.get("desc");
        String topicIdStr = form.get("topicId");

        if (Objects.isNull(topicIdStr) || !Covert2Tools.isDigital(topicIdStr)) {
            return resp;
        }

        if (Objects.isNull(content)) {
            return resp;
        }

        if (Objects.isNull(title)) {
            return resp;
        }

        if (Objects.isNull(desc)) {
            return resp;
        }

        if (Objects.isNull(entryIdsStr)) {
            return resp;
        }

        Long topicId = Covert2Tools.covertToLong(topicIdStr);

        List<Long> entryIds = new LinkedList<>();
        for (String entryId : entryIdsStr.split(";")) {
            if (!"" .equals(entryId) && Covert2Tools.isDigital(entryId)) {
                entryIds.add(Covert2Tools.covertToLong(entryId));
            }
        }

        ArticleModel articleModel = new ArticleModel();
        articleModel.setUserId(userId);
        articleModel.setTopicId(topicId);
        articleModel.setArticleTitle(title);
        articleModel.setArticleDesc(desc);
        articleModel.setArticleContent(content);
        Map<String, Long> map = topicServiceRpc.postPublicMainArticleByOtherReturnMainNumberRpc(articleModel);
        Long mainNumber = map.get("mainNumber");
        Long articleId = map.get("articleId");
        if (Objects.isNull(mainNumber)) {
            return resp;
        }
        if (Objects.nonNull(articleId) && articleId > 0) {
            topicServiceRpc.postAddMultiArticleEntryIdsRpc(articleId, entryIds);
            resp.setMainNumber(mainNumber);
            return resp;
        }
        return resp;
    }

    /**
     * 更新文章
     *
     * @param userId
     * @param form:articleId,content
     * @return
     */
    public int postUpdateOwnerArticle(Long userId, Map<String, String> form) {
        String articleIdStr = form.get("articleId");
        String content = form.get("content");

        if (Objects.isNull(articleIdStr) || !Covert2Tools.isDigital(articleIdStr)) {
            return 4000;
        }
        if (Objects.isNull(content)) return 4000;

        int ret = userServiceRpc.postSelectDetectLegalityWithUserIdRpc(userId);
        if (ret != 1) return ret;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm");
        content += "<p>" + LocalDateTime.now().format(dateTimeFormatter) + "</p>";

        content = content.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&#39;");

        return topicServiceRpc.postUpdateArticleInfoRpc(userId, Covert2Tools.covertToLong(articleIdStr), content);
    }
}
















