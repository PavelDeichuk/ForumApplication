package com.pavel.forumapplication.Repository;

import com.pavel.forumapplication.Entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<NewsEntity,Long> {
    Optional<NewsEntity> findByName(String name);

}
