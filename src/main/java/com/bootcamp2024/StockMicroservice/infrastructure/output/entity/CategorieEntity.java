package com.bootcamp2024.StockMicroservice.infrastructure.output.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categorie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategorieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(max = 50, message = "Name cannot be longer than 50 Characters")
    private String name;

    @Size(max = 90, message = "Name cannot be longer than 90 Characters")
    private String description;
}
