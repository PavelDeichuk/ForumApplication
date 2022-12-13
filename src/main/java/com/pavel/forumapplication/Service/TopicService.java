package com.pavel.forumapplication.Service;

import com.pavel.forumapplication.Dto.TopicDto;
import com.pavel.forumapplication.Entity.TopicEntity;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.List;

public interface TopicService {
    List<TopicDto> GetAllTopic(int page, int size);

    TopicDto GetTopicById(Long topic_id);

    TopicDto AddTopicToCategory(Long topic_id, Long category_id);

    TopicDto CreateTopic(TopicEntity topicEntity, BindingResult bindingResult) throws IOException;

    TopicDto EditTopic(Long topic_id, TopicEntity topicEntity, BindingResult bindingResult);

    TopicDto DeleteTopic(Long topic_id);
}
