package com.example.demo2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "meals")
public class MealEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "meal_name", unique = true, nullable = false)
    private String name;
    @Column(name = "meal_consistency", nullable = false)
    private String consistency;
    @Column(name = "meal_price", nullable = false)
    private Double price;

    @ManyToMany(mappedBy = "meals")
    private List<MealOrderEntity> mealOrders;
}
