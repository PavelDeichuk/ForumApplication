package com.pavel.forumapplication.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavel.forumapplication.Dto.TopicDto;
import com.pavel.forumapplication.Service.TopicService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
class TopicControllerTest {

    @Mock
    private TopicService topicService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllTopic() throws Exception {
        List<TopicDto> topicDtos = new ArrayList<>();
        when(topicService.GetAllTopic(0,10)).thenReturn(topicDtos);
        mockMvc.perform(get("/api/v1/topic")
                        .content(objectMapper.writeValueAsString(topicDtos))
                .contentType("application/json"))
                .andExpect(status().isOk());
        verify(topicService).GetAllTopic(0,10);
    }

    @Test
    void getTopicById() throws Exception {
        TopicDto topicDto = new TopicDto();
        when(topicService.GetTopicById(any())).thenReturn(topicDto);
        TopicDto topicTest = topicService.GetTopicById(any());
        mockMvc.perform(get("/api/v1/topic/1")
                .content(objectMapper.writeValueAsString(topicTest))
                .contentType("application/json"))
                .andExpect(status().isOk());
        verify(topicService).GetTopicById(any());
    }

    @Test
    void createTopic() throws Exception {
        TopicDto topicDto = new TopicDto();
        when(topicService.CreateTopic(any(), any())).thenReturn(topicDto);
        TopicDto topicDtoTest = topicService.CreateTopic(any(), any());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/topic")
                .contentType("application/json")
                        .content(objectMapper.writeValueAsString(topicDtoTest)))
                .andExpect(status().isOk());
        verify(topicService).CreateTopic(any(), any());
    }

    @Test
    void editTopic() {
    }

    @Test
    void deleteTopic() {
    }
}