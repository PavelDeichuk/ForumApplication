package com.pavel.forumapplication.Dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.pavel.forumapplication.Entity.UsersEntity} entity
 */
@Getter
@Setter
public class UsersDto implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String role;
    private boolean loginIsEmail;
}