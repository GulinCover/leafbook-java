package org.leafbook.serviceMarketplaceApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.billInfo.AuctionModel;

import java.util.List;

@Mapper
public interface AuctionModelMapper extends BaseMapper<AuctionModel> {
    List<AuctionModel> selectMultiAuctionByTopicIds(@Param("topicIds")List<Long> topicIds);

    List<AuctionModel> selectSearchMultiAuctionForNickname(
            @Param("content")String content,
            @Param("startTime")Long startTime,
            @Param("endTime")Long endTime,
            @Param("start")Long start,
            @Param("end")Long end
    );

    Long selectSearchMultiAuctionAmountForNickname(
            @Param("content")String content,
            @Param("startTime")Long startTime,
            @Param("endTime")Long endTime
    );

    Integer selectDetectIsExistSingleAuctionByAuctionId(@Param("auctionId")Long auctionId);

    AuctionModel selectSingleAuctionByAuctionId(@Param("auctionId")Long auctionId);

    List<AuctionModel> selectRandomMultiAuction(@Param("number")Integer number);

    List<AuctionModel> selectLatestMultiAuction(@Param("number")Integer number);

    List<AuctionModel> selectMultiAuctionByAuctionIds(@Param("auctionIds")List<Long> auctionIds);

    List<AuctionModel> selectAllAuctionByUserId(@Param("userId")Long userId);

    List<AuctionModel> selectMultiAuctionByUserId(
            @Param("userId")Long userId,
            @Param("start")Long start,
            @Param("end")Long end
    );
    Long selectMultiAuctionAmountByUserId(
            @Param("userId")Long userId
    );

    AuctionModel selectSingleAuctionByUserIdAndAuctionId(
            @Param("userId")Long userId,
            @Param("auctionId")Long auctionId
    );

    int updateByModel(@Param("auctionModel")AuctionModel auctionModel);
}









