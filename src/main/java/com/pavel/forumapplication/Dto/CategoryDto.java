package com.pavel.forumapplication.Dto;

import com.pavel.forumapplication.Entity.TopicEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.pavel.forumapplication.Entity.CategoryEntity} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {
    private Long id;
    private String name;

    private List<TopicEntity> topicEntities;
}