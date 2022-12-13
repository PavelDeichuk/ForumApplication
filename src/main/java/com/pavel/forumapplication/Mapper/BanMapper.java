package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.BanDto;
import com.pavel.forumapplication.Entity.BanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BanMapper {
    BanMapper INSTANCE = Mappers.getMapper(BanMapper.class);

    BanDto BAN_DTO(BanEntity banEntity);

    BanEntity BAN_ENTITY(BanDto banDto);
}
