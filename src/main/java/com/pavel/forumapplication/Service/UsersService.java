package com.pavel.forumapplication.Service;

import com.pavel.forumapplication.Dto.UsersDto;
import com.pavel.forumapplication.Entity.UsersEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsersService {
    List<UsersDto> GetAllUsers(int page, int size);

    UsersDto GetUserById(Long user_id);

    UsersDto ActivateAccount(String activation);

    UsersDto CreateUser(UsersEntity usersEntity);

    UsersDto ChangePassword(String email);

    UsersDto ChangeEmail(String email);

    UsersDto ResetEmail(UsersEntity usersEntity, String email_token);

    UsersDto ResetPassword(UsersEntity usersEntity, String pass_token);

    UsersDto EditUser(Long user_id, UsersEntity usersEntity);

    UsersDto DeleteUser(Long user_id);
}
