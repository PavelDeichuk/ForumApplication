package com.pavel.forumapplication.Service.impl;

import com.pavel.forumapplication.Dto.TopicDto;
import com.pavel.forumapplication.Entity.CategoryEntity;
import com.pavel.forumapplication.Entity.TopicEntity;
import com.pavel.forumapplication.Exception.BadRequestException;
import com.pavel.forumapplication.Exception.NotFoundException;
import com.pavel.forumapplication.Mapper.TopicMapper;
import com.pavel.forumapplication.Repository.CategoryRepository;
import com.pavel.forumapplication.Repository.TopicRepository;
import com.pavel.forumapplication.Service.TopicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    private final CategoryRepository categoryRepository;

    public TopicServiceImpl(TopicRepository topicRepository, CategoryRepository categoryRepository) {
        this.topicRepository = topicRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<TopicDto> GetAllTopic(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<TopicEntity> topicEntities = topicRepository.findAll(pageable);
        if(topicEntities.isEmpty()){
            throw new NotFoundException("topic list is empty!");
        }
        return topicEntities
                .stream()
                .map(TopicMapper.INSTANCE::TOPIC_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public TopicDto GetTopicById(Long topic_id) {
        TopicEntity topic = topicRepository
                .findById(topic_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for topic id!");
                });
        return TopicMapper.INSTANCE.TOPIC_DTO(topic);
    }

    @Override
    public TopicDto AddTopicToCategory(Long topic_id, Long category_id) {
        TopicEntity topic = topicRepository
                .findById(topic_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for topic id!");
                });
        CategoryEntity category = categoryRepository
                .findById(category_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for category id!");
                });
        List<CategoryEntity> topicEntities = topic.getCategoryEntities();
        topicEntities.add(category);
        topic.setCategoryEntities(topicEntities);
        topicRepository.save(topic);
        return TopicMapper.INSTANCE.TOPIC_DTO(topic);
    }

    @Override
    public TopicDto CreateTopic(TopicEntity topicEntity, BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error :  fieldErrors){
                throw new BadRequestException(error.getObjectName() + " " + error.getDefaultMessage());
            }
        }
        TopicEntity topic = topicRepository
                .saveAndFlush(
                        TopicEntity
                                .builder()
                                .name(topicEntity.getName())
                                .description(topicEntity.getDescription())
                                .build()
                );
        return TopicMapper.INSTANCE.TOPIC_DTO(topic);
    }

    @Override
    public TopicDto EditTopic(Long topic_id, TopicEntity topicEntity, BindingResult bindingResult) {
        TopicEntity topic = topicRepository
                .findById(topic_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for topic id!");
                });
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error :  fieldErrors){
                throw new BadRequestException(error.getObjectName() + " " + error.getDefaultMessage());
            }
        }
        topic.setName(topicEntity.getName());
        topic.setDescription(topicEntity.getDescription());
        topicRepository.save(topic);
        return TopicMapper.INSTANCE.TOPIC_DTO(topic);
    }

    @Override
    public TopicDto DeleteTopic(Long topic_id) {
        TopicEntity topic = topicRepository.findById(topic_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for topic id!");
                });
        topicRepository.deleteById(topic_id);
        return TopicMapper.INSTANCE.TOPIC_DTO(topic);
    }
}
