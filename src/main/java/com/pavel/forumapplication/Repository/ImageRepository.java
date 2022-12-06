package com.pavel.forumapplication.Repository;

import com.pavel.forumapplication.Entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity,Long> {
    Optional<ImageEntity> findByName(String name);

}
