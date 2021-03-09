package org.leafbook.serviceapi.serviceApi;

import org.leafbook.api.respAbs.explorePage.EntryAbs;
import org.leafbook.api.respAbs.explorePage.EntryInfoResp;
import org.leafbook.api.respAbs.explorePage.TopicAbs;
import org.leafbook.api.respAbs.explorePage.UserReplyDataResp;
import org.leafbook.api.testModel.explorePage.ExploreTextModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ExplorePageServiceApi {

    public UserReplyDataResp postSelectUserReplyNumber(Long userId) {
        UserReplyDataResp userReplyDataResp = new UserReplyDataResp();
        userReplyDataResp.setUserAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");
        userReplyDataResp.setUUID("asfw-2324-asfs");
        userReplyDataResp.setUsername("Alex");

        userReplyDataResp.setCommentReplyNumber(new Random().nextInt(100));
        userReplyDataResp.setTopicReplyNumber(new Random().nextInt(100));
        userReplyDataResp.setTalkReplyNumber(new Random().nextInt(100));
        return userReplyDataResp;
    }

    public List<EntryAbs> getSelectEntryInfos() {
        return ExploreTextModel.createRandomEntryAbsList();
    }

    public List<TopicAbs> getSelectTopicInfosByEntryIds(List<Long> entryIds) {
        return ExploreTextModel.createTopicAbsListByEntryIds();
    }

    public List<EntryAbs> getSelectEntryInfosSimple() {
        return ExploreTextModel.createRandomEntryAbsListSimple();
    }

}
