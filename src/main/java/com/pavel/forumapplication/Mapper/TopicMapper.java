package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.TopicDto;
import com.pavel.forumapplication.Entity.TopicEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TopicMapper {
    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    TopicDto TOPIC_DTO(TopicEntity topicEntity);

    TopicEntity TOPIC_ENTITY(TopicDto topicDto);
}
