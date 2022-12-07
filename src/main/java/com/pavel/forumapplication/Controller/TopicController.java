package com.pavel.forumapplication.Controller;

import com.pavel.forumapplication.Dto.TopicDto;
import com.pavel.forumapplication.Entity.TopicEntity;
import com.pavel.forumapplication.Service.TopicService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/topic")
public class TopicController {
    private static final String TOPIC_ID = "/{topic_id}";

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TopicDto> GetAllTopic(@RequestParam(value = "page", defaultValue = "page") int page, @RequestParam(value = "size", defaultValue = "10") int size){
        return topicService.GetAllTopic(page,size);
    }

    @RequestMapping(value = TOPIC_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TopicDto GetTopicById(@PathVariable Long topic_id){
        return topicService.GetTopicById(topic_id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public TopicDto CreateTopic(@RequestBody TopicEntity topicEntity) throws IOException {
        return topicService.CreateTopic(topicEntity);
    }

    @RequestMapping(value = TOPIC_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public TopicDto EditTopic(@PathVariable Long topic_id,
                              @RequestBody TopicEntity topicEntity){
        return topicService.EditTopic(topic_id,topicEntity);
    }

    @RequestMapping(value = TOPIC_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TopicDto DeleteTopic(@PathVariable Long topic_id){
        return topicService.DeleteTopic(topic_id);
    }
}
