package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.TopicDto;
import com.pavel.forumapplication.Entity.TopicEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TopicMapperTest {

    @Mock
    private TopicMapper topicMapper;

    @Test
    void TOPIC_DTO() {
        TopicEntity topicEntity = TopicEntity.builder().id(1L).build();
        TopicDto topicDto = topicMapper.INSTANCE.TOPIC_DTO(topicEntity);
        assertEquals(topicDto.getId(), topicEntity.getId());
    }

    @Test
    void TOPIC_ENTITY() {
        TopicDto topicDto = TopicDto.builder().id(1L).build();
        TopicEntity topicEntity = topicMapper.INSTANCE.TOPIC_ENTITY(topicDto);
        assertEquals(topicEntity.getId(), topicDto.getId());
    }
}