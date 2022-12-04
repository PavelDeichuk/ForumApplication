package com.pavel.forumapplication.Repository;

import com.pavel.forumapplication.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity,Long> {
    Optional<UsersEntity> findByUsername(String username);

    Optional<UsersEntity> findByEmail(String email);

    Optional<UsersEntity> findByActivation(String activation);

    UsersEntity findByLoginIsEmail(boolean loginIsEmail);

    Optional<UsersEntity> findByPass_token(String pass_token);

    Optional<UsersEntity> findByEmail_token(String email_token);
}
