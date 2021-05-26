package org.leafbook.serviceMarketplaceApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.billInfo.BillModel;

import java.util.List;

@Mapper
public interface BillModelMapper extends BaseMapper<BillModel> {

    List<BillModel> selectMultiBillByUserId(
            @Param("userId")Long userId,
            @Param("start")Long start,
            @Param("end")Long end
    );

    Long selectMultiBillAmountByUserId(@Param("userId")Long userId);
}
