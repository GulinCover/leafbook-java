package org.leafbook.serviceMarketplaceApi.dao;

import org.leafbook.api.modelApi.billInfo.BillModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BillModelMapper {
    public int insert(BillModel billModel) {
        return new Random().nextInt(100);
    }
}
