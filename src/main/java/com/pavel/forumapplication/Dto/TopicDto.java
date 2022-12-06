package com.pavel.forumapplication.Dto;

import com.pavel.forumapplication.Entity.CategoryEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.pavel.forumapplication.Entity.TopicEntity} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicDto implements Serializable {
    private Long id;
    private String name;
    private String description;

    private List<CategoryEntity> categoryEntities;
}