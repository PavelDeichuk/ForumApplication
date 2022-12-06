package com.pavel.forumapplication.Dto;

import com.pavel.forumapplication.Entity.TopicEntity;
import com.pavel.forumapplication.Entity.UsersEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.pavel.forumapplication.Entity.CommentEntity} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {
    private Long id;
    private String description;

    private UsersEntity users;

    private TopicEntity topicEntity;
}