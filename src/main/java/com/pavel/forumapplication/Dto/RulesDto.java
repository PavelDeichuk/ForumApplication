package com.pavel.forumapplication.Dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.pavel.forumapplication.Entity.RulesEntity} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RulesDto implements Serializable {
    private Long id;
    private Long id_rule;
    private String title;
    private String description;

    private LocalDateTime timeout;
}