package com.pavel.forumapplication.Dto;

import com.pavel.forumapplication.Entity.ImageEntity;
import com.pavel.forumapplication.Entity.UsersEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.pavel.forumapplication.Entity.NewsEntity} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto implements Serializable {
    private Long id;
    private String name;
    private String description;

    private ImageEntity imageEntities;

    private UsersEntity usersEntity;
}