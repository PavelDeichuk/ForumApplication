package com.pavel.forumapplication.Service;

import com.pavel.forumapplication.Dto.UsersDetailDto;
import com.pavel.forumapplication.Entity.UsersDetailEntity;

import java.util.List;

public interface UsersDetailService {
    List<UsersDetailDto> GetAllUsersDetail(int page, int size);

    UsersDetailDto GetUsersDetailById(Long user_id);

    UsersDetailDto CreateUserDetail(UsersDetailEntity usersDetailEntity);

    UsersDetailDto EditUserDetail(Long user_id, UsersDetailEntity usersDetailEntity);

    UsersDetailDto DeleteUserDetail(Long user_id);
}
