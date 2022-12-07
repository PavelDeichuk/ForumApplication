package com.pavel.forumapplication.Service;

import com.pavel.forumapplication.Dto.NewsDto;
import com.pavel.forumapplication.Entity.NewsEntity;

import java.util.List;

public interface NewsService {
    List<NewsDto> GetAllNews(int page, int size);

    NewsDto GetNewsById(Long news_id);

    NewsDto CreateNews(NewsEntity newsEntity, String username);

    NewsDto EditNews(Long news_id, NewsEntity newsEntity);

    NewsDto DeleteNews(Long news_id);
}
