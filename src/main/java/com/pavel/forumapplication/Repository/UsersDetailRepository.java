package com.pavel.forumapplication.Repository;

import com.pavel.forumapplication.Entity.UsersDetailEntity;
import com.pavel.forumapplication.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDetailRepository extends JpaRepository<UsersDetailEntity,Long> {
    UsersDetailEntity findByUsersEntity(UsersEntity usersEntity);

}
