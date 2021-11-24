package com.qaproject.Cookbook.repository;

import com.qaproject.Cookbook.entity.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefRep extends JpaRepository<Chef,Long> {
}
