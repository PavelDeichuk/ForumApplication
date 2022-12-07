package com.pavel.forumapplication.Dto;

import com.pavel.forumapplication.Entity.ImageEntity;
import com.pavel.forumapplication.Entity.UsersEntity;
import com.pavel.forumapplication.Enum.StatusEnum;
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

    private LocalDateTime birthday;

    private StatusEnum statusEnum;

    private UsersEntity usersEntity;

    private ImageEntity imageEntity;
}
