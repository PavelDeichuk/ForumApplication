package com.pavel.forumapplication.Service.impl;

import com.pavel.forumapplication.Entity.UsersEntity;
import com.pavel.forumapplication.Repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {

    @Mock
    private UsersRepository usersRepository;


    @Test
   public void getAllUsers() {
        List<UsersEntity> usersEntities = new ArrayList<>();
        when(usersRepository.findAll()).thenReturn(usersEntities);
        List<UsersEntity> usersEntities1 = usersRepository.findAll();
        assertEquals(usersEntities, usersEntities1);
        verify(usersRepository).findAll();
    }

    @Test
    void getUserById() {
        UsersEntity users = UsersEntity.builder().id(1L).build();
        when(usersRepository.findById(users.getId())).thenReturn(Optional.of(users));
        Optional<UsersEntity> userstwo = usersRepository.findById(1L);
        assertEquals(users.getId(), userstwo.get().getId());
        verify(usersRepository).findById(any());
    }

    @Test
    void activateAccount() {
        UsersEntity users = new UsersEntity();
        when(usersRepository.findByActivation(any())).thenReturn(Optional.of(users));
        Optional<UsersEntity> userstwo = usersRepository.findByActivation(any());
        assertEquals(users.getActivation(), userstwo.get().getActivation());
        verify(usersRepository).findByActivation(any());
    }

    @Test
    void createUser() {
        UsersEntity users = new UsersEntity();
        when(usersRepository.save(any())).thenReturn(users);
        UsersEntity users1 = usersRepository.save(any());
        assertEquals(users, users1);
        verify(usersRepository).save(any());
    }

    @Test
    void changePassword() {

    }

    @Test
    void changeEmail() {
    }

    @Test
    void resetEmail() {
    }

    @Test
    void resetPassword() {
    }

    @Test
    void editUser() {

    }

    @Test
    void deleteUser() {
    }
}