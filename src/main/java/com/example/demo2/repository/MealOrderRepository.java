package com.example.demo2.repository;

import com.example.demo2.domain.MealOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MealOrderRepository extends JpaRepository<MealOrderEntity,Long> {
    MealOrderEntity findByUserResourceId(UUID userId);

    Integer removeByUserResourceId(UUID userId);
}
