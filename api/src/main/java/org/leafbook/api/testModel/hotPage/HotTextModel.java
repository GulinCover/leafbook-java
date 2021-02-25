package org.leafbook.api.testModel.hotPage;

import org.leafbook.api.respAbs.hotPage.EntryAbs;
import org.leafbook.api.respAbs.hotPage.TopicInfoAbs;
import org.leafbook.api.testModel.indexPage.TestModel;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class HotTextModel extends TestModel {

    public static List<TopicInfoAbs> createTopicInfosList() {
        List<TopicInfoAbs> topicInfoAbsList = new LinkedList<>();
        for (int i = 0; i < (new Random().nextInt(15)) + 5; ++i) {
            TopicInfoAbs topicInfoAbs = new TopicInfoAbs();
            topicInfoAbs.setTopicId((long) i);
            topicInfoAbs.setTopicTitle("仓库" + i);
            topicInfoAbs.setTopicDesc("desc"+i);
            topicInfoAbs.setUserId(1L);
            topicInfoAbs.setUserName("Alex");
            topicInfoAbs.setLikeNumber(i);
            topicInfoAbs.setManagerNumber(i);
            topicInfoAbs.setPublicTime(new Date());
            topicInfoAbs.setEntryAbsList(createEntryAbsByTopicId());

            topicInfoAbsList.add(topicInfoAbs);
        }

        return topicInfoAbsList;
    }

    public static List<EntryAbs> createEntryAbsByTopicId() {
        List<EntryAbs> entryAbsList = new LinkedList<EntryAbs>();
        for (int i = 0; i < 3; ++i) {
            EntryAbs entryAbs = new EntryAbs();
            entryAbs.setEntryId((long) i);
            entryAbs.setEntryName(randomWord(4));

            entryAbsList.add(entryAbs);
        }
        return entryAbsList;
    }

    public static List<EntryAbs> createAllHotEntryAbsByTopicId() {
        List<EntryAbs> entryAbsList = new LinkedList<EntryAbs>();
        for (int i = 0; i < 30; ++i) {
            EntryAbs entryAbs = new EntryAbs();
            entryAbs.setEntryId((long) i);
            entryAbs.setEntryName(randomWord(4));

            entryAbsList.add(entryAbs);
        }
        return entryAbsList;
    }
}
