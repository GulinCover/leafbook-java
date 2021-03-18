package org.leafbook.serviceMarketplaceApi.dao;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class Auction2EntryModelMapper {
    public List<Long> selectMultiAuctionIdByEntryId(Long entryId) {
        List<Long> auctionIds = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(15) + 20; ++i) {
            auctionIds.add((long) i);
        }
        return auctionIds;
    }
}
