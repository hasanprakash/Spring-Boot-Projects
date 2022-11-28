package com.learn.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.core.entity.HeroTheme;

public interface HeroThemeRepository extends JpaRepository<HeroTheme, Integer> {
    
}
