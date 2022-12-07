package com.pavel.forumapplication.Repository;

import com.pavel.forumapplication.Entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity,Long> {

}
