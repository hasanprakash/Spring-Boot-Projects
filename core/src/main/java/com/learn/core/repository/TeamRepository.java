package com.learn.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.core.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    
}
