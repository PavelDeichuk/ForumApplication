package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.NewsDto;
import com.pavel.forumapplication.Entity.NewsEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class NewsMapperTest {

    @Mock
    private NewsMapper newsMapper;

    @Test
    void NEWS_DTO() {
        NewsEntity newsEntity = NewsEntity.builder().id(1L).build();
        NewsDto newsDto = newsMapper.INSTANCE.NEWS_DTO(newsEntity);
        assertEquals(newsDto.getId(), newsEntity.getId());
    }

    @Test
    void NEWS_ENTITY() {
        NewsDto newsDto = NewsDto.builder().id(1L).build();
        NewsEntity newsEntity = newsMapper.INSTANCE.NEWS_ENTITY(newsDto);
        assertEquals(newsEntity.getId(), newsDto.getId());
    }
}