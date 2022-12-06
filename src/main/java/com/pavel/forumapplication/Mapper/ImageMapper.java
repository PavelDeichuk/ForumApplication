package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.ImageDto;
import com.pavel.forumapplication.Entity.ImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto IMAGE_DTO(ImageEntity imageEntity);

    ImageEntity IMAGE_ENTITY(ImageDto imageDto);
}
