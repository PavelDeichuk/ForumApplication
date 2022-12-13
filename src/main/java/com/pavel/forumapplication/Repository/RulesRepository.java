package com.pavel.forumapplication.Repository;

import com.pavel.forumapplication.Entity.RulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RulesRepository extends JpaRepository<RulesEntity,Long> {

}
