package com.pavel.forumapplication.Service.impl;

import com.pavel.forumapplication.Dto.CategoryDto;
import com.pavel.forumapplication.Entity.CategoryEntity;
import com.pavel.forumapplication.Exception.NotFoundException;
import com.pavel.forumapplication.Mapper.CategoryMapper;
import com.pavel.forumapplication.Mapper.UsersMapper;
import com.pavel.forumapplication.Repository.CategoryRepository;
import com.pavel.forumapplication.Service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> GetAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable);
        if(categoryEntities.isEmpty()){
            throw new NotFoundException("Category list is empty!");
        }
        return categoryEntities
                .stream()
                .map(CategoryMapper.INSTANCE::CATEGORY_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto GetCategoryByName(String name) {
        CategoryEntity categoryEntity = categoryRepository
                .findByName(name)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for username");
                });
        return CategoryMapper.INSTANCE.CATEGORY_DTO(categoryEntity);
    }

    @Override
    @Transactional
    public CategoryDto CreateCategory(CategoryEntity categoryEntity) {
        categoryRepository
                .findByName(categoryEntity.getName())
                .ifPresent(name -> {
                    throw new NotFoundException("Category name is exist!");
                });
        CategoryEntity category = categoryRepository.saveAndFlush(categoryEntity);
        return CategoryMapper.INSTANCE.CATEGORY_DTO(category);
    }

    @Override
    @Transactional
    public CategoryDto EditCategory(Long category_id, CategoryEntity categoryEntity) {
        CategoryEntity category = categoryRepository
                .findById(category_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for category id!");
                });
        category.setName(categoryEntity.getName());
        category.setTopicEntities(categoryEntity.getTopicEntities());
        categoryRepository.save(category);
        return CategoryMapper.INSTANCE.CATEGORY_DTO(category);
    }

    @Override
    @Transactional
    public CategoryDto DeleteCategory(Long category_id) {
        CategoryEntity categoryEntity = categoryRepository
                .findById(category_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for category id!");
                });
        categoryRepository.deleteById(category_id);
        return CategoryMapper.INSTANCE.CATEGORY_DTO(categoryEntity);
    }
}
