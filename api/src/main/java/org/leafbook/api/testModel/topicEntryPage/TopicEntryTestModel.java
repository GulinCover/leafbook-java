package org.leafbook.api.testModel.topicEntryPage;

import org.apache.maven.model.Contributor;
import org.leafbook.api.respAbs.indexPage.TopicAbs;
import org.leafbook.api.respAbs.topicEntryPage.*;
import org.leafbook.api.testModel.indexPage.TestModel;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TopicEntryTestModel extends TestModel {
    public static EntryInfoResp createEntryInfo() {
        EntryInfoResp resp = new EntryInfoResp();
        resp.setApplicant("official");
        resp.setEntryAvatar(Picture);
        resp.setEntryDesc(randomString().toString());
        resp.setEntryId(10L);
        resp.setEntryCreatorId(321312L);
        resp.setEntryName(randomWord());
        resp.setLikedNumber(String.valueOf(new Random().nextInt(500)));
        resp.setPassTime(new Date().toString());
        resp.setRelatedTopicNumber(String.valueOf(new Random().nextInt(5000)));
        return resp;
    }

    public static List<TopicDetailAbs> createTopicDetailAbsList() {
        List<TopicDetailAbs> topicDetailAbsList = new LinkedList<>();

        for (int i = 0; i < (new Random().nextInt(15)) + 5; ++i) {
            TopicDetailAbs topicAbs = new TopicDetailAbs();
            topicAbs.setTopicId((long) i);
            topicAbs.setTopicTitle("仓库" + i);

            topicAbs.setUserId((long)i);
            topicAbs.setUsername(randomWord());

            ContentAbs contentAbs = new ContentAbs();
            contentAbs.setTopicDesc(randomString().toString());
            List<EntryAbs> entryAbsList = new LinkedList<>();
            for (int j = 0; j < (new Random().nextInt(5)) + 5; ++j) {
                EntryAbs entryAbs = new EntryAbs();
                entryAbs.setEntryId((long) i);
                entryAbs.setEntryName(randomWord());

                entryAbsList.add(entryAbs);
            }

            contentAbs.setEntryAbsList(entryAbsList);
            topicAbs.setContentAbs(contentAbs);

            CommentAbs commentAbs = new CommentAbs();
            commentAbs.setUserAvatar(Picture);
            commentAbs.setCommentContent(randomString().toString());
            commentAbs.setEntryAbsList(entryAbsList);
            commentAbs.setUserCommentTime(new Date().toString());
            commentAbs.setUserId((long)i);
            commentAbs.setUsername(randomWord());
            topicAbs.setCommentAbs(commentAbs);

            TalkAbs talkAbs = new TalkAbs();
            talkAbs.setTalkId((long)i);
            talkAbs.setTalkTitle(randomWord());
            talkAbs.setUserAvatar(Picture);
            talkAbs.setUserId((long)i);
            talkAbs.setUserTalkCommentTime(new Date().toString());
            talkAbs.setUsername(randomWord());
            talkAbs.setTalkCommentContent(randomString().toString());
            talkAbs.setEntryAbsList(entryAbsList);
            topicAbs.setTalkAbs(talkAbs);

            ContributorAbs contributorAbs = new ContributorAbs();
            List<UserInfoAbs> userInfoAbsList = new LinkedList<>();
            for (int j = 0; j < (new Random().nextInt(5)) + 5; ++j) {
                UserInfoAbs userInfoAbs = new UserInfoAbs();
                userInfoAbs.setUserId((long) i);
                userInfoAbs.setUsername(randomWord());
                userInfoAbs.setUuid(randomWord());
                userInfoAbs.setUserAvatar(Picture);

                userInfoAbsList.add(userInfoAbs);
            }
            contributorAbs.setUserInfoAbsList(userInfoAbsList);
            topicAbs.setContributorAbs(contributorAbs);

            topicAbs.setEntryAbsList(entryAbsList);

            topicDetailAbsList.add(topicAbs);
        }

        return topicDetailAbsList;
    }
}
