package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.UsersDetailDto;
import com.pavel.forumapplication.Entity.UsersDetailEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UsersDetailMapperTest {
    @Mock
    private UsersDetailMapper usersDetailMapper;

    @Test
    void UsersDetailToDto(){
        UsersDetailEntity usersDetailEntity = UsersDetailEntity.builder().id(1L).build();
        UsersDetailDto usersDetailDto = usersDetailMapper.INSTANCE.USERS_DETAIL_DTO(usersDetailEntity);
        assertEquals(usersDetailDto.getId(), usersDetailEntity.getId());
    }

    @Test
    void UsersDetailToEntity(){
        UsersDetailDto usersDetailDto = UsersDetailDto.builder().id(1L).build();
        UsersDetailEntity usersDetailEntity = usersDetailMapper.INSTANCE.USERS_DETAIL_ENTITY(usersDetailDto);
        assertEquals(usersDetailEntity.getId(), usersDetailEntity.getId());
    }
}
