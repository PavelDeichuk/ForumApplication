package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.CommentDto;
import com.pavel.forumapplication.Entity.CommentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CommentMapperTest {

    @Mock
    private CommentMapper commentMapper;

    @Test
    void COMMENT_DTO() {
        CommentEntity commentEntity = CommentEntity.builder().id(1L).build();
        CommentDto commentDto = commentMapper.INSTANCE.COMMENT_DTO(commentEntity);
        assertEquals(commentDto.getId(), commentEntity.getId());
    }

    @Test
    void COMMENT_ENTITY() {
        CommentDto commentDto = CommentDto.builder().id(1L).build();
        CommentEntity commentEntity = commentMapper.INSTANCE.COMMENT_ENTITY(commentDto);
        assertEquals(commentEntity.getId(), commentDto.getId());
    }
}