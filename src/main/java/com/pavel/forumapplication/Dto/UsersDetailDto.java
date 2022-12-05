package com.pavel.forumapplication.Dto;

import com.pavel.forumapplication.Entity.UsersEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.pavel.forumapplication.Entity.UsersDetailEntity} entity
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDetailDto implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String description;
    private LocalDateTime last_online;

    private UsersEntity usersEntity;
}