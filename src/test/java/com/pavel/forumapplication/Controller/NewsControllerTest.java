package com.pavel.forumapplication.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavel.forumapplication.Dto.NewsDto;
import com.pavel.forumapplication.Dto.UsersDto;
import com.pavel.forumapplication.Service.NewsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private NewsService newsService;

    @Test
    void getAllNews() throws Exception {
        List<NewsDto> newsDtos = new ArrayList<>();
        when(newsService.GetAllNews(2,10)).thenReturn(newsDtos);
        mockMvc.perform(get("/api/v1/news")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newsDtos)))
                .andExpect(status().isOk());
    }

    @Test
    void getNewsById() {
    }

    @Test
    void createNews() {
    }

    @Test
    void editNews() {
    }

    @Test
    void deleteNews() {
    }
}