package com.pavel.forumapplication.Service;

import com.pavel.forumapplication.Dto.CategoryDto;
import com.pavel.forumapplication.Entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> GetAllCategories(int page, int size);
    CategoryDto GetCategoryByName(String name);

    CategoryDto CreateCategory(CategoryEntity categoryEntity);

    CategoryDto EditCategory(Long category_id, CategoryEntity categoryEntity);

    CategoryDto DeleteCategory(Long category_id);
}
