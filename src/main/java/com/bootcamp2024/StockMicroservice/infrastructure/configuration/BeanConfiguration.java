package com.bootcamp2024.StockMicroservice.infrastructure.configuration;


import com.bootcamp2024.StockMicroservice.domain.ICategoriePersistencePort;
import com.bootcamp2024.StockMicroservice.domain.ICategorieServicePort;
import com.bootcamp2024.StockMicroservice.domain.usecases.CategorieUseCases;
import com.bootcamp2024.StockMicroservice.infrastructure.output.CategorieJpaAdapter;
import com.bootcamp2024.StockMicroservice.infrastructure.output.Mapper.CategorieEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.repository.ICategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategorieRepository categorieRepository;
    private final CategorieEntityMapper categorieEntityMapper;

    @Bean
    public ICategoriePersistencePort categoriePersistencePort(){
        return new CategorieJpaAdapter(categorieRepository, categorieEntityMapper);
    }


    @Bean
    public ICategorieServicePort categorieServicePort(){
        return new CategorieUseCases(categoriePersistencePort());
    }

}
