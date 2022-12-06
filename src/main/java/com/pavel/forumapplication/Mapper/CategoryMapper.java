package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.CategoryDto;
import com.pavel.forumapplication.Entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto CATEGORY_DTO(CategoryEntity categoryEntity);

    CategoryEntity CATEGORY_ENTITY(CategoryDto categoryDto);
}
