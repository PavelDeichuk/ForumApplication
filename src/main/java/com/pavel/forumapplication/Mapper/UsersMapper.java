package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.UsersDto;
import com.pavel.forumapplication.Entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersMapper {
    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    UsersDto USERS_DTO(UsersEntity usersEntity);

    UsersEntity USERS_ENTITY(UsersDto usersDto);
}
