package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository;

import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.CategoryEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
class CategoryRepositoryTest {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Test
    @DisplayName("Created category should not be null")
    void CategoryRepository_ReturnSaveCategory(){

        CategoryEntity category = CategoryEntity.builder()
                .name("computadoras")
                .description("para entrar a internet")
                .build();

        CategoryEntity savedCategory = categoryRepository.save(category);

        Assertions.assertThat(savedCategory).isNotNull();
        Assertions.assertThat(savedCategory.getId()).isGreaterThan(0);

    }

    @Test
    void CategoryRepository_Get_ReturnOneCategory(){

        CategoryEntity category = CategoryEntity.builder()
                .name("computadoras")
                .description("para entrar a internet")
                .build();

        categoryRepository.save(category);

        CategoryEntity categoryRes = categoryRepository.findByName("computadoras").get();

        Assertions.assertThat(categoryRes).isNotNull();
        assertEquals(category, categoryRes);

    }
  
}