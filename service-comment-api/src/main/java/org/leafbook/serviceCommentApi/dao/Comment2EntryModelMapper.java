package org.leafbook.serviceCommentApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.leafbook.api.modelApi.commentInfo.Comment2EntryModel;

@Mapper
public interface Comment2EntryModelMapper extends BaseMapper<Comment2EntryModel> {
}
