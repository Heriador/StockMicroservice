package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "brand")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Length(max = 50, message = "Name cannot be longer than 50 Characters")
    private String name;

    @Column(nullable = false)
    @Length(max = 120, message = "Description cannot be longer than 120 Characters")
    private String description;

}
