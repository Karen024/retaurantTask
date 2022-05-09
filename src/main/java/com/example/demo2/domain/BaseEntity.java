package com.example.demo2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Column(name = "resource_id", unique = true, columnDefinition = "uuid default uuid_generate_v4()")
    private final UUID resourceId = UUID.randomUUID();
}
