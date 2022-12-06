package com.pavel.forumapplication.Repository;

import com.pavel.forumapplication.Entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<TopicEntity,Long> {

}
