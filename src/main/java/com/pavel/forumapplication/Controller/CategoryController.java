package com.pavel.forumapplication.Controller;

import com.pavel.forumapplication.Dto.CategoryDto;
import com.pavel.forumapplication.Entity.CategoryEntity;
import com.pavel.forumapplication.Service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private static final String CATEGORY_NAME = "/{category_name}";

    private static final String CATEGORY_ID = "/{category_id}";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> GetAllCategories(@RequestParam(value = "page",defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "0") int size){
        return categoryService.GetAllCategories(page,size);
    }

    @RequestMapping(value = CATEGORY_NAME, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto GetCategoryByName(@PathVariable String category_name){
        return categoryService.GetCategoryByName(category_name);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto CreateCategory(@RequestBody CategoryEntity categoryEntity){
        return categoryService.CreateCategory(categoryEntity);
    }

    @RequestMapping(value = CATEGORY_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto EditCategory(@PathVariable Long category_id, @RequestBody CategoryEntity categoryEntity){
        return categoryService.EditCategory(category_id, categoryEntity);
    }

    @RequestMapping(value = CATEGORY_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto DeleteCategory(@PathVariable Long category_id){
        return categoryService.DeleteCategory(category_id);
    }
}
