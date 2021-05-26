package org.leafbook.serviceapi.serviceApi.topicPage;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.api.respAbs.topicPublicPage.branch.BranchArticleAbs;
import org.leafbook.api.respAbs.topicPublicPage.branch.DirectoryInfoResp;
import org.leafbook.api.respAbs.topicPublicPage.branch.MainArticleAbs;
import org.leafbook.serviceapi.serviceRpc.recordService.RecordServiceRpc;
import org.leafbook.serviceapi.serviceRpc.topicService.TopicServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@GlobalTransactional
@Service
public class TopicBranchPageServiceApi {
    @Autowired
    private TopicServiceRpc topicServiceRpc;
    @Autowired
    private RecordServiceRpc recordServiceRpc;
    /**
     * 获取目录
     * @param userId
     * @param topicId
     * @param page
     * @return
     */
    public DirectoryInfoResp getSelectTopicDirectory(Long userId,Long topicId,Long page) {
        DirectoryInfoResp resp = new DirectoryInfoResp();
        List<MainArticleAbs> mainArticleAbsList = new LinkedList<>();

        List<ArticleModel> mainInfoList = topicServiceRpc.getSelectMultiMainInfosRpc(topicId, page);
        if (Objects.nonNull(mainInfoList) && !mainInfoList.isEmpty()) {
            for (ArticleModel articleModel:mainInfoList) {
                MainArticleAbs mainArticleAbs = new MainArticleAbs();
                mainArticleAbs.setArticleId(articleModel.getArticleId());
                mainArticleAbs.setArticleTitle(articleModel.getArticleTitle());
                mainArticleAbs.setBranchNumber(articleModel.getBranchNumber());
                mainArticleAbs.setMainNumber(articleModel.getMainNumber());

                if (userId != 0) {
                    int ret = recordServiceRpc.postSelectUserIsBrowseArticleRpc(userId, mainArticleAbs.getArticleId());
                    mainArticleAbs.setIsBrowse(ret);
                } else {
                    mainArticleAbs.setIsBrowse(0);
                }

                List<ArticleModel> branchInfoList = topicServiceRpc.getSelectAllBranchInfoByMainNumberRpc(topicId, articleModel.getMainNumber());
                List<BranchArticleAbs> branchArticleAbsList = new LinkedList<>();
                if (Objects.nonNull(branchInfoList) && !branchInfoList.isEmpty()) {
                    for (ArticleModel branchArticle:branchInfoList) {
                        BranchArticleAbs branchArticleAbs = new BranchArticleAbs();
                        branchArticleAbs.setArticleId(branchArticle.getArticleId());
                        branchArticleAbs.setArticleTitle(branchArticle.getArticleTitle());
                        branchArticleAbs.setBranchNumber(branchArticle.getBranchNumber());
                        branchArticleAbs.setMainNumber(branchArticle.getMainNumber());

                        if (userId != 0) {
                            int ret = recordServiceRpc.postSelectUserIsBrowseArticleRpc(userId,branchArticle.getArticleId());
                            branchArticleAbs.setIsBrowse(ret);
                        } else {
                            branchArticleAbs.setIsBrowse(0);
                        }

                        branchArticleAbsList.add(branchArticleAbs);
                    }
                }

                mainArticleAbs.setBranchArticleAbsList(branchArticleAbsList);


                mainArticleAbsList.add(mainArticleAbs);
            }
        }


        resp.setPage(topicServiceRpc.getSelectMultiMainInfoAmountRpc(topicId));
        resp.setMainArticleAbsList(mainArticleAbsList);
        return resp;
    }
}
















