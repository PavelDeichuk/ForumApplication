package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.UsersDetailDto;
import com.pavel.forumapplication.Entity.UsersDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersDetailMapper {

    UsersDetailMapper INSTANCE = Mappers.getMapper(UsersDetailMapper.class);

    UsersDetailDto USERS_DETAIL_DTO(UsersDetailEntity usersDetailEntity);

    UsersDetailEntity USERS_DETAIL_ENTITY(UsersDetailDto usersDetailDto);
}
