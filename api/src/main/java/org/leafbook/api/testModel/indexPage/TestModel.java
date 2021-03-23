package org.leafbook.api.testModel.indexPage;

import lombok.Data;
import org.leafbook.api.respAbs.common.SearchHistoryAbs;
import org.leafbook.api.respAbs.indexPage.BrowseHistoryAbs;
import org.leafbook.api.respAbs.indexPage.EntryData;
import org.leafbook.api.respAbs.indexPage.TopicAbs;
import org.leafbook.api.respAbs.topicPage.EntryAbs;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Data
public class TestModel {
    public static String Picture = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg";

    public static List<TopicAbs> createTopicAbsList() {
        List<TopicAbs> topicAbsList = new LinkedList<TopicAbs>();
        for (int i = 0; i < (new Random().nextInt(15)) + 5; ++i) {
            TopicAbs topicAbs = new TopicAbs();
            topicAbs.setTopicId((long) i);
            topicAbs.setTopicTitle("仓库" + i);

            topicAbsList.add(topicAbs);
        }

        return topicAbsList;
    }

    public static List<SearchHistoryAbs> createSearchHistoryAbsList() {
        List<SearchHistoryAbs> searchHistoryAbss = new LinkedList<SearchHistoryAbs>();
        for (int i = 0; i < (new Random().nextInt(15)) + 5; ++i) {
            SearchHistoryAbs searchHistoryAbs = new SearchHistoryAbs();
            searchHistoryAbs.setSearchContent(randomWord());

            searchHistoryAbss.add(searchHistoryAbs);
        }

        return searchHistoryAbss;
    }

    public static List<BrowseHistoryAbs> createBrowseHistoryAbsList() {
        List<BrowseHistoryAbs> browseHistoryAbss = new LinkedList<BrowseHistoryAbs>();
        for (int i = 0; i < (new Random().nextInt(3)) + 5; ++i) {
            BrowseHistoryAbs browseHistoryAbs = new BrowseHistoryAbs();
            browseHistoryAbs.setTopicId((long) i);
            browseHistoryAbs.setTopicTitle("仓库" + i);
            browseHistoryAbs.setLikeNumber((long)new Random().nextInt(5000) + 51);
            browseHistoryAbs.setTopicDesc("仓库描述" + i);

            List<EntryData> entryDataList = new LinkedList<EntryData>();
            for (int j = 0; j < (new Random().nextInt(3)) + 1; ++j) {
                EntryData entryData = new EntryData();
                entryData.setEntryId((long) j);
                entryData.setName("词条" + j);

                entryDataList.add(entryData);
            }

            browseHistoryAbs.setEntryDataList(entryDataList);
            browseHistoryAbss.add(browseHistoryAbs);
        }

        return browseHistoryAbss;
    }

    public static List<EntryAbs> createRecommendedEntryAbsList() {
        List<EntryAbs> entryAbsList = new LinkedList<EntryAbs>();
        for (int i = 0; i < 3; ++i) {
            EntryAbs entryAbs = new EntryAbs();
            entryAbs.setIsLiked(i);
            entryAbs.setEntryId((long) i);
            entryAbs.setEntryName(randomWord(4));
            entryAbs.setEntryDesc(randomString().toString());
            entryAbs.setLikeNumber((long)new Random().nextInt(6000)+150);
            entryAbs.setEntryAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");

            entryAbsList.add(entryAbs);
        }
        return entryAbsList;
    }

    public static List<EntryAbs> createAllEntryAbsList() {
        List<EntryAbs> entryAbsList = new LinkedList<EntryAbs>();
        for (int i = 0; i < 6; ++i) {
            EntryAbs entryAbs = new EntryAbs();
            entryAbs.setIsLiked(i);
            entryAbs.setEntryId((long) i);
            entryAbs.setEntryName(randomWord(4));
            entryAbs.setEntryDesc(randomString().toString());
            entryAbs.setLikeNumber((long)new Random().nextInt(6000)+150);
            entryAbs.setEntryAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg");

            entryAbsList.add(entryAbs);
        }
        return entryAbsList;
    }

    public static StringBuilder randomString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < new Random().nextInt(8)+5; ++i) {
            str.append(randomWord(4));
            str.append(" ");
        }

        return str;
    }

    public static String randomWord(int max) {
        int length = 5 + (int) (Math.random() * max);
        String word = "";
        for (int i = 0; i < length; i++) {
            word += (char) randomChar();
        }
        return word;
    }

    public static String randomWord() {
        int length = 5 +  (int) (Math.random() * 9);
        String word = "";
        for (int i = 0; i < length; i++) {
            word += (char) randomChar();
        }
        return word;
    }

    public static byte randomChar() {
        int flag = (int) (Math.random() * 2);// 0小写字母1大写字母
        byte resultBt;
        if (flag == 0) {
            byte bt = (byte) (Math.random() * 26);// 0 <= bt < 26
            resultBt = (byte) (65 + bt);
        } else {
            byte bt = (byte) (Math.random() * 26);// 0 <= bt < 26
            resultBt = (byte) (97 + bt);
        }
        return resultBt;
    }
}
