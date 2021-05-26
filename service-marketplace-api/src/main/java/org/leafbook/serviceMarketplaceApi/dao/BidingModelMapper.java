package org.leafbook.serviceMarketplaceApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.billInfo.BidingModel;

import java.util.List;

@Mapper
public interface BidingModelMapper extends BaseMapper<BidingModel> {
    List<BidingModel> selectMultiBidingFailedByUserId(
            @Param("userId")Long userId,
            @Param("start")Long start,
            @Param("end")Long end
    );

    Long selectMultiBidingFailedAmountByUserId(@Param("userId")Long userId);

    List<BidingModel> selectMultiBidingByUserId(
            @Param("userId")Long userId,
            @Param("start")Long start,
            @Param("end")Long end
    );

    Long selectMultiBidingAmountByUserId(@Param("userId")Long userId);

    List<BidingModel> selectMultiBidingSuccessByUserId(
            @Param("userId")Long userId,
            @Param("start")Long start,
            @Param("end")Long end
    );

    Long selectMultiBidingSuccessAmountByUserId(@Param("userId")Long userId);
}
