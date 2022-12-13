package com.pavel.forumapplication.Repository;

import com.pavel.forumapplication.Entity.BanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BanRepository extends JpaRepository<BanEntity,Long> {
    BanEntity findByUsersEntity_Id(Long id);

}
