package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.CommentDto;
import com.pavel.forumapplication.Entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDto COMMENT_DTO(CommentEntity commentEntity);

    CommentEntity COMMENT_ENTITY(CommentDto commentDto);
}
