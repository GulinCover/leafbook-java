package org.leafbook.api.testModel.repositoryPage;

import org.leafbook.api.respAbs.indexPage.TopicAbs;
import org.leafbook.api.respAbs.repository.publicTopicPage.EntryAbs;
import org.leafbook.api.respAbs.repository.publicTopicPage.PublicTopicAbs;
import org.leafbook.api.testModel.indexPage.TestModel;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RepositoryTestModel extends TestModel {
    public static List<PublicTopicAbs> createPublicTopicAbsList() {
        List<PublicTopicAbs> publicTopicAbsList = new LinkedList<PublicTopicAbs>();
        for (int i = 0; i < (new Random().nextInt(15)) + 5; ++i) {
            PublicTopicAbs publicTopicAbs = new PublicTopicAbs();
            publicTopicAbs.setTopicId((long) i);
            publicTopicAbs.setTopicTitle("仓库" + i);
            publicTopicAbs.setLikeNumber(String.valueOf(new Random().nextInt(500)));
            publicTopicAbs.setTopicDesc(randomString().toString());
            publicTopicAbs.setUpdateTime(new Date().toString());

            List<EntryAbs> entryAbsList = new LinkedList<>();
            for (int j = 0; j < (new Random().nextInt(2)) + 5; ++j) {
                EntryAbs entryAbs = new EntryAbs();
                entryAbs.setEntryId(21L);
                entryAbs.setEntryName(randomWord());

                entryAbsList.add(entryAbs);
            }

            publicTopicAbs.setEntryAbsList(entryAbsList);

            publicTopicAbsList.add(publicTopicAbs);
        }

        return publicTopicAbsList;
    }

    public static List<EntryAbs> createPublicTopicAllEntryList() {
        List<EntryAbs> entryAbsList = new LinkedList<>();
        for (int j = 0; j < (new Random().nextInt(2)) + 5; ++j) {
            EntryAbs entryAbs = new EntryAbs();
            entryAbs.setEntryId(21L);
            entryAbs.setEntryName(randomWord());

            entryAbsList.add(entryAbs);
        }
        return entryAbsList;
    }
}
