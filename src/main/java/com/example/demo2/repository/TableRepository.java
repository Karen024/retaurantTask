package com.example.demo2.repository;

import com.example.demo2.domain.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {
    @Query(value = "SELECT * FROM tables\n" +
            "WHERE (?1 NOT BETWEEN tables.booking_time_start AND tables.booking_time_end)\n" +
            "OR (tables.booking_time_start IS NULL AND tables.booking_time_end IS NULL );"
            , nativeQuery = true)
    List<TableEntity> findFreeTablesForNow(LocalDateTime nowTime);

    TableEntity findByResourceId(UUID tableId);
}
