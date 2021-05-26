package org.leafbook.serviceCommentApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.commentInfo.Comment2EntryShowModel;

import java.util.List;

@Mapper
public interface Comment2EntryShowModelMapper extends BaseMapper<Comment2EntryShowModel> {
    List<Long> selectMultiEntryIdByComment1Id(@Param("comment1Id")Long comment1Id);
    List<Long> selectMultiEntryIdByTalkId(@Param("talkId")Long talkId);
    List<Long> selectMultiEntryIdByTalkComment1Id(@Param("talkComment1Id")Long talkComment1Id);

}
