package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.ImageDto;
import com.pavel.forumapplication.Entity.ImageEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ImageMapperTest {

    @Mock
    private ImageMapper imageMapper;
    @Test
    void IMAGE_DTO() {
        ImageEntity imageEntity = ImageEntity.builder().id(1L).build();
        ImageDto imageDto = imageMapper.INSTANCE.IMAGE_DTO(imageEntity);
        assertEquals(imageDto.getId(), imageEntity.getId());
    }

    @Test
    void IMAGE_ENTITY() {
        ImageDto imageDto = ImageDto.builder().id(1L).build();
        ImageEntity imageEntity = imageMapper.INSTANCE.IMAGE_ENTITY(imageDto);
        assertEquals(imageEntity.getId(), imageDto.getId());
    }
}