package com.example.demo2.repository;

import com.example.demo2.domain.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUserEntity, Long> {
    AppUserEntity findByResourceId(UUID resourceId);

    AppUserEntity findByEmail(String email);

    AppUserEntity findByPhoneNumber(String phoneNumber);

    AppUserEntity findByUsername(String username);
}
