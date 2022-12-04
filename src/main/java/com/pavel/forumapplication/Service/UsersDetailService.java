package com.pavel.forumapplication.Service;

import com.pavel.forumapplication.Dto.UsersDetailDto;
import com.pavel.forumapplication.Entity.UsersDetailEntity;

import java.util.List;

public interface UsersDetailService {
    List<UsersDetailDto> GetAllUsersDetail(int number, int size);

    UsersDetailDto GetUserDetailById(Long user_id);

    UsersDetailDto CreateUserDetail(Long user_id, UsersDetailEntity usersDetailEntity);

    UsersDetailDto EditUserDetail(Long user_id, UsersDetailEntity usersDetailEntity);

}
