package org.leafbook.api.testModel.indexPage;

import lombok.Data;
import org.leafbook.api.respAbs.common.SearchHistoryAbs;
import org.leafbook.api.respAbs.indexPage.BrowseHistoryAbs;
import org.leafbook.api.respAbs.indexPage.EntryData;
import org.leafbook.api.respAbs.indexPage.TopicAbs;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Data
public class TestModel {
    public static List<TopicAbs> createTopicAbs() {
        List<TopicAbs> topicAbsList = new LinkedList<TopicAbs>();
        for (int i = 0;i< (new Random().nextInt(15))+5;++i) {
            TopicAbs topicAbs = new TopicAbs();
            topicAbs.setTopicId((long) i);
            topicAbs.setTopicTitle("仓库"+i);

            topicAbsList.add(topicAbs);
        }

        return topicAbsList;
    }

    public static List<SearchHistoryAbs> createSearchHistoryAbsList() {
        List<SearchHistoryAbs> searchHistoryAbss = new LinkedList<SearchHistoryAbs>();
        for (int i = 0;i< (new Random().nextInt(15))+5;++i) {
            SearchHistoryAbs searchHistoryAbs = new SearchHistoryAbs();
            searchHistoryAbs.setTopicId((long) i);
            searchHistoryAbs.setTopicTitle("仓库"+i);

            searchHistoryAbss.add(searchHistoryAbs);
        }

        return searchHistoryAbss;
    }

    public static List<BrowseHistoryAbs> createBrowseHistoryAbsList() {
        List<BrowseHistoryAbs> browseHistoryAbss = new LinkedList<BrowseHistoryAbs>();
        for (int i = 0;i< (new Random().nextInt(3))+5;++i) {
            BrowseHistoryAbs browseHistoryAbs = new BrowseHistoryAbs();
            browseHistoryAbs.setTopicId((long) i);
            browseHistoryAbs.setTopicTitle("仓库"+i);
            browseHistoryAbs.setLikeNumber(new Random().nextInt(5000)+51);
            browseHistoryAbs.setTopicDesc("仓库描述"+i);

            List<EntryData> entryDataList = new LinkedList<EntryData>();
            for (int j = 0;j< (new Random().nextInt(3))+1;++j) {
                EntryData entryData = new EntryData();
                entryData.setEntryId((long) j);
                entryData.setName("词条"+j);

                entryDataList.add(entryData);
            }

            browseHistoryAbs.setEntryDataList(entryDataList);
            browseHistoryAbss.add(browseHistoryAbs);
        }

        return browseHistoryAbss;
    }
}
