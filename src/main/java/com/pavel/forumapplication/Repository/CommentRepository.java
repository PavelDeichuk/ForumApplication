package com.pavel.forumapplication.Repository;


import com.pavel.forumapplication.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

}
