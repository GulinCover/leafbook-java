package org.leafbook.serviceMarketplaceApi.dao;

import org.leafbook.api.modelApi.billInfo.BidingModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BidingModelMapper {

    public int insertSingleModel(BidingModel bidingModel) {
        return new Random().nextInt(100);
    }
}
