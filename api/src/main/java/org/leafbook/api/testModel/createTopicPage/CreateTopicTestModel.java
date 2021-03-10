package org.leafbook.api.testModel.createTopicPage;

import org.leafbook.api.respAbs.createTopicPage.EntryAbs;
import org.leafbook.api.testModel.indexPage.TestModel;

import java.util.LinkedList;
import java.util.List;

public class CreateTopicTestModel extends TestModel {
    public static List<EntryAbs> createEntryAbsList() {
        List<EntryAbs> entryAbsList = new LinkedList<>();
        for (int i = 0; i < 3; ++i) {
            EntryAbs entryAbs = new EntryAbs();
            entryAbs.setEntryId((long) i);
            entryAbs.setEntryName(randomWord(4));
            entryAbs.setEntryDesc(randomString().toString());

            entryAbsList.add(entryAbs);
        }
        return entryAbsList;
    }
}
