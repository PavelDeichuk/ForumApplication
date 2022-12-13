package com.pavel.forumapplication.Service;

import com.pavel.forumapplication.Dto.UsersDetailDto;
import com.pavel.forumapplication.Entity.UsersDetailEntity;
import com.pavel.forumapplication.Entity.UsersEntity;
import com.pavel.forumapplication.Repository.UsersDetailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersDetailServiceTest {

    @Mock
    private UsersDetailRepository usersDetailRepository;

    @Test
    void getAllUsersDetail() {
        List<UsersDetailEntity> usersDetailEntities = new ArrayList<>();
        when(usersDetailRepository.findAll()).thenReturn(usersDetailEntities);
        List<UsersDetailEntity> usersDetailRepositoryAll = usersDetailRepository.findAll();
        assertEquals(usersDetailEntities, usersDetailRepositoryAll);
        verify(usersDetailRepository).findAll();
    }

    @Test
    void getUsersDetailById() {
        UsersDetailEntity usersDetail = new UsersDetailEntity();
        when(usersDetailRepository.findById(any())).thenReturn(Optional.of(usersDetail));
        Optional<UsersDetailEntity> usersDetailEntityTest = usersDetailRepository.findById(any());
        assertEquals(usersDetail, usersDetailEntityTest.get());
        verify(usersDetailRepository).findById(any());
    }

    @Test
    void createUserDetail() {
        UsersDetailEntity usersDetail = new UsersDetailEntity();
        when(usersDetailRepository.save(any())).thenReturn(usersDetail);
        UsersDetailEntity usersDetailEntityTest = usersDetailRepository.save(any());
        assertEquals(usersDetail, usersDetailEntityTest);
        verify(usersDetailRepository).save(any());
    }

    @Test
    void editUserDetail() {

    }

    @Test
    void deleteUserDetail() {
    }
}