package com.bootcamp2024.StockMicroservice.infrastructure.configuration;


import com.bootcamp2024.StockMicroservice.domain.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.ICategoryServicePort;
import com.bootcamp2024.StockMicroservice.domain.usecases.CategoryUseCases;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.CategoryAdapter;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.CategoryEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }


    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCases(categoryPersistencePort());
    }

}
