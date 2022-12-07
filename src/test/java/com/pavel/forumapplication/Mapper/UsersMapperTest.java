package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.UsersDto;
import com.pavel.forumapplication.Entity.UsersEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UsersMapperTest {
    @Mock
    private UsersMapper usersMapper;

    @Test
    void UsersToDto(){
        UsersEntity users = UsersEntity.builder().id(1L).build();
        UsersDto usersDto = usersMapper.INSTANCE.USERS_DTO(users);
        assertEquals(usersDto.getId(), users.getId());
    }

    @Test
    void UsersToEntity(){
        UsersDto usersDto = UsersDto.builder().id(1L).build();
        UsersEntity users = usersMapper.INSTANCE.USERS_ENTITY(usersDto);
        assertEquals(users.getId(), usersDto.getId());
    }
}
