package org.leafbook.api.testModel.repositoryPage;

import org.leafbook.api.respAbs.repository.publicCommentPage.BillAbs;
import org.leafbook.api.respAbs.repository.publicCommentPage.CommentInfoAbs;
import org.leafbook.api.respAbs.repository.publicTopicPage.EntryAbs;
import org.leafbook.api.respAbs.repository.publicTopicPage.PublicTopicAbs;
import org.leafbook.api.respAbs.repository.repositoryPage.ConsumableInfoAbs;
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

    public static List<CommentInfoAbs> createPublicCommentAbsList() {
        List<CommentInfoAbs> commentInfoAbsList = new LinkedList<>();
        for (int i = 0; i < (new Random().nextInt(15)) + 5; ++i) {
            CommentInfoAbs commentInfoAbs = new CommentInfoAbs();
            commentInfoAbs.setTopicId((long) i);
            commentInfoAbs.setTopicTitle("仓库" + i);
            commentInfoAbs.setLikeNumber(String.valueOf(new Random().nextInt(500)));
            commentInfoAbs.setCommentId((long) i);
            commentInfoAbs.setCommentContent(randomString().toString());
            commentInfoAbs.setCommentLevel("2562");
            commentInfoAbs.setCommentType("0");
            commentInfoAbs.setCommentTime(new Date().toString());

            List<org.leafbook.api.respAbs.repository.publicCommentPage.EntryAbs> entryAbsList = new LinkedList<>();
            for (int j = 0; j < (new Random().nextInt(2)) + 5; ++j) {
                org.leafbook.api.respAbs.repository.publicCommentPage.EntryAbs entryAbs = new org.leafbook.api.respAbs.repository.publicCommentPage.EntryAbs();
                entryAbs.setEntryId(21L);
                entryAbs.setEntryName(randomWord());

                entryAbsList.add(entryAbs);
            }

            commentInfoAbs.setEntryAbsList(entryAbsList);

            commentInfoAbsList.add(commentInfoAbs);
        }

        return commentInfoAbsList;
    }

    public static List<BillAbs> createBillAbsList() {
        List<BillAbs> billAbsList = new LinkedList<>();
        for (int i = 0; i < (new Random().nextInt(15)) + 5; ++i) {
            BillAbs billAbs = new BillAbs();
            billAbs.setTopicId((long) i);
            billAbs.setTopicTitle("仓库" + i);
            billAbs.setBuyTime(new Date().toString());
            billAbs.setBuyerUUID(randomWord());
            billAbs.setSellingTime(new Date().toString());
            billAbs.setSellerUUID(randomWord());
            billAbs.setBillUUID(randomWord());
            billAbs.setGetPrice(String.valueOf(new Random().nextInt(500)));
            billAbs.setPositive("+");

            billAbs.setType("topic");
            billAbs.setTopicId((long) i);
            billAbs.setTopicTitle(randomWord());

            billAbsList.add(billAbs);
        }

        return billAbsList;
    }

    public static List<ConsumableInfoAbs> createConsumableAbsList() {
        List<ConsumableInfoAbs> consumableInfoAbsList = new LinkedList<>();
        for (int i = 0; i < (new Random().nextInt(15)) + 5; ++i) {
            ConsumableInfoAbs consumableInfoAbs = new ConsumableInfoAbs();
            consumableInfoAbs.setTopicId((long) i);
            consumableInfoAbs.setConsumableId((long) i);
            consumableInfoAbs.setBuyTime(new Date().toString());
            consumableInfoAbs.setType("topic");
            consumableInfoAbs.setTopicTitle(randomWord());
            consumableInfoAbs.setConsumableUUID(randomWord());
            consumableInfoAbs.setBuyPrice(String.valueOf(new Random().nextInt(500)));

            consumableInfoAbsList.add(consumableInfoAbs);
        }

        return consumableInfoAbsList;
    }
}
