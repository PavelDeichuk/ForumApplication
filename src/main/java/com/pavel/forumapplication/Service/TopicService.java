package com.pavel.forumapplication.Service;

import com.pavel.forumapplication.Dto.TopicDto;
import com.pavel.forumapplication.Entity.TopicEntity;

import java.util.List;

public interface TopicService {
    List<TopicDto> GetAllTopic(int page, int size);

    TopicDto GetTopicById(Long topic_id);

    TopicDto AddTopicToCategory(Long topic_id, Long category_id);

    TopicDto CreateTopic(TopicEntity topicEntity);

    TopicDto EditTopic(Long topic_id, TopicEntity topicEntity);

    TopicDto DeleteTopic(Long topic_id);
}
