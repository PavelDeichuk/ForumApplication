package com.pavel.forumapplication.Dto;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.pavel.forumapplication.Entity.ImageEntity} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto implements Serializable {
    private Long id;
    private String name;
    private byte[] bytes;
    private Long size;
}