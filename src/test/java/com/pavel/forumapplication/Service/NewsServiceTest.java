package com.pavel.forumapplication.Service;

import com.pavel.forumapplication.Entity.NewsEntity;
import com.pavel.forumapplication.Entity.UsersEntity;
import com.pavel.forumapplication.Repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest {

    @Mock
    private NewsRepository newsRepository;

    @Test
    void getAllNews() {
        List<NewsEntity> newsEntities = new ArrayList<>();
        when(newsRepository.findAll()).thenReturn(newsEntities);
        List<NewsEntity> newsEntitiesTest = newsRepository.findAll();
        assertEquals(newsEntities, newsEntitiesTest);
        verify(newsRepository).findAll();
    }

    @Test
    void getNewsById() {
        NewsEntity newsEntity = new NewsEntity();
        when(newsRepository.findById(any())).thenReturn(Optional.of(newsEntity));
        Optional<NewsEntity> newsTest = newsRepository.findById(any());
        assertEquals(newsEntity, newsTest.get());
        verify(newsRepository).findById(any());
    }

    @Test
    void createNews() {
        NewsEntity newsEntity = new NewsEntity();
        when(newsRepository.save(any())).thenReturn(newsEntity);
        NewsEntity newsTest = newsRepository.save(any());
        assertEquals(newsEntity, newsTest);
        verify(newsRepository).save(any());
    }

    @Test
    void editNews() {

    }

    @Test
    void deleteNews() {
    }
}