package com.pavel.forumapplication.Mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.pavel.forumapplication.Dto.CategoryDto;
import com.pavel.forumapplication.Entity.CategoryEntity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryMapperTest {

    @Mock
    private CategoryMapper categoryMapper;

    @Test
    void CATEGORY_DTO() {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .id(1L)
                .build();
        CategoryDto categoryDto = categoryMapper.INSTANCE.CATEGORY_DTO(categoryEntity);
        assertEquals(categoryDto.getId(), categoryEntity.getId());
    }

    @Test
    void CATEGORY_ENTITY() {
        CategoryDto categoryDto = CategoryDto.builder().id(1L).build();
        CategoryEntity categoryEntity = categoryMapper.INSTANCE.CATEGORY_ENTITY(categoryDto);
        assertEquals(categoryEntity.getId(), categoryDto.getId());
    }
}