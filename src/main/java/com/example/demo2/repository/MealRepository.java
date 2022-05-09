package com.example.demo2.repository;

import com.example.demo2.domain.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MealRepository extends JpaRepository<MealEntity, Long> {
    MealEntity findByResourceId(UUID mealId);
}
