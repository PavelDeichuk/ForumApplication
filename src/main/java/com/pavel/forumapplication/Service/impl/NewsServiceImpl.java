package com.pavel.forumapplication.Service.impl;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.pavel.forumapplication.Dto.NewsDto;
import com.pavel.forumapplication.Entity.NewsEntity;
import com.pavel.forumapplication.Entity.UsersEntity;
import com.pavel.forumapplication.Enum.NewsRole;
import com.pavel.forumapplication.Exception.NotFoundException;
import com.pavel.forumapplication.Mapper.NewsMapper;
import com.pavel.forumapplication.Repository.NewsRepository;
import com.pavel.forumapplication.Repository.UsersRepository;
import com.pavel.forumapplication.Service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    private final UsersRepository usersRepository;

    public NewsServiceImpl(NewsRepository newsRepository, UsersRepository usersRepository) {
        this.newsRepository = newsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<NewsDto> GetAllNews(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NewsEntity> newsEntities = newsRepository.findAll(pageable);
        if(newsEntities.isEmpty()){
            throw new NotFoundException("news list is empty!");
        }
        return newsEntities
                .stream()
                .map(NewsMapper.INSTANCE::NEWS_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto GetNewsById(Long news_id) {
        NewsEntity newsEntity = newsRepository
                .findById(news_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for news id!");
                });
        return NewsMapper.INSTANCE.NEWS_DTO(newsEntity);
    }

    @Override
    public NewsDto CreateNews(NewsEntity newsEntity, String username) {
        UsersEntity users = usersRepository
                .findByUsername(username)
                .orElseThrow(() -> {
                    throw new NotFoundException("Error init login!");
                });
        NewsEntity newsSave = newsRepository
                .saveAndFlush(
                        NewsEntity
                                .builder()
                                .name(newsEntity.getName())
                                .description(newsEntity.getDescription())
                                .usersEntity(users)
                                .build()
                );
        return NewsMapper.INSTANCE.NEWS_DTO(newsSave);
    }

    @Override
    public NewsDto EditNews(Long news_id, NewsEntity newsEntity) {
        NewsEntity news = newsRepository
                .findById(news_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user id");
                });
        news.setName(newsEntity.getName());
        news.setDescription(newsEntity.getDescription());
        newsRepository.save(news);
        return NewsMapper.INSTANCE.NEWS_DTO(news);
    }

    @Override
    public NewsDto DeleteNews(Long news_id) {
        NewsEntity news = newsRepository
                .findById(news_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for news id!");
                });
        newsRepository.deleteById(news_id);
        return NewsMapper.INSTANCE.NEWS_DTO(news);
    }
}
