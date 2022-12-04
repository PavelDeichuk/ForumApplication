package com.pavel.forumapplication.Service;

import com.pavel.forumapplication.Dto.UsersDto;
import com.pavel.forumapplication.Entity.UsersEntity;

import java.util.List;

public interface UsersService {
    List<UsersDto> GetAllUsers();

    UsersDto GetUserById(Long user_id);

    UsersDto ActivateAccount(String activation);

    UsersDto CreateUser(UsersEntity usersEntity);

    UsersDto EditUser(Long user_id, UsersEntity usersEntity);

    UsersDto DeleteUser(Long user_id);
}
