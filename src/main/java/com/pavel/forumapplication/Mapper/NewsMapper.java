package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.NewsDto;
import com.pavel.forumapplication.Entity.NewsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDto NEWS_DTO(NewsEntity newsEntity);

    NewsEntity NEWS_ENTITY(NewsDto newsDto);
}
