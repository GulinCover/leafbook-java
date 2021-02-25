package org.leafbook.api.testModel.explorePage;

import org.leafbook.api.respAbs.explorePage.EntryAbs;
import org.leafbook.api.respAbs.explorePage.EntryInfoResp;
import org.leafbook.api.respAbs.explorePage.TopicAbs;
import org.leafbook.api.testModel.indexPage.TestModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ExploreTextModel extends TestModel {
    public static List<EntryAbs> createRandomEntryAbsList() {
        List<EntryAbs> entryAbsList = new LinkedList<>();
        for (int i = 0; i < 6; ++i) {
            EntryAbs entryAbs = new EntryAbs();
            entryAbs.setIsLiked(i);
            entryAbs.setEntryId((long) new Random().nextInt(3));
            entryAbs.setEntryName(randomWord(4));
            entryAbs.setEntryDesc(randomString().toString());
            entryAbs.setLikeNumber(new Random().nextInt(6000)+150);
            entryAbs.setEntryAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");

            entryAbsList.add(entryAbs);
        }
        return entryAbsList;
    }

    public static List<TopicAbs> createTopicAbsListByEntryIds() {
        List<TopicAbs> topicAbsList = new LinkedList<>();
        for (int i = 0; i < (new Random().nextInt(25)) + 25; ++i) {
            TopicAbs topicAbs = new TopicAbs();
            topicAbs.setTopicId((long) i);
            topicAbs.setTopicTitle("仓库dasdasdsadsasadas" + i);
            topicAbs.setTopicUser("Alex");
            topicAbs.setUserId(100L);
            topicAbs.setEntryId((long) new Random().nextInt(3));
            topicAbs.setTopicDesc(randomString().toString());
            topicAbs.setLikeNumber(new Random().nextInt(5000)+150);

            topicAbsList.add(topicAbs);
        }

        return topicAbsList;
    }

    public static List<EntryAbs> createRandomEntryAbsListSimple() {
        List<EntryAbs> entryAbsList = new LinkedList<>();
        for (int i = 0; i < 6; ++i) {
            EntryAbs entryAbs = new EntryAbs();
            entryAbs.setEntryId((long) new Random().nextInt(3));
            entryAbs.setEntryName(randomWord(4));

            entryAbsList.add(entryAbs);
        }
        return entryAbsList;
    }

    public static List<TopicAbs> createTopicAbsListByEntryId() {
        List<TopicAbs> topicAbsList = new LinkedList<>();
        for (int i = 0; i < (new Random().nextInt(5)) + 5; ++i) {
            TopicAbs topicAbs = new TopicAbs();
            topicAbs.setTopicId((long) i);
            topicAbs.setTopicTitle("仓库" + i);
            topicAbs.setTopicUser("Alex");
            topicAbs.setUserId(100L);
            topicAbs.setEntryId((long) new Random().nextInt(3));
            topicAbs.setTopicDesc(randomString().toString());
            topicAbs.setLikeNumber(new Random().nextInt(5000)+150);

            topicAbsList.add(topicAbs);
        }

        return topicAbsList;
    }
}
