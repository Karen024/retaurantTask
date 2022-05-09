package com.example.demo2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tables")
public class TableEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "number_of_seats", nullable = false)
    private Integer numberOfSeats;
    @Column(name = "is_smoking_table", nullable = false)
    private Boolean isSmokingTable;
    @Column(name = "booking_time_start")
    private LocalDateTime bookingTimeStart;
    @Column(name = "booking_time_end")
    private LocalDateTime bookingTimeEnd;

    @OneToMany(mappedBy = "table")
    private List<AppUserEntity> users;
}
