package com.pavel.forumapplication.Dto;

import com.pavel.forumapplication.Entity.RulesEntity;
import com.pavel.forumapplication.Entity.UsersEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.pavel.forumapplication.Entity.BanEntity} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BanDto implements Serializable {
    private Long id;

    private RulesEntity rulesEntity;

    private UsersEntity usersEntity;

    private UsersEntity author_ban;
}