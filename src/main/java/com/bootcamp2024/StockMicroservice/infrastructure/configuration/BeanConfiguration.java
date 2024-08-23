package com.bootcamp2024.StockMicroservice.infrastructure.configuration;


import com.bootcamp2024.StockMicroservice.domain.api.IBrandServicePort;
import com.bootcamp2024.StockMicroservice.domain.spi.IBrandPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.spi.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.api.ICategoryServicePort;
import com.bootcamp2024.StockMicroservice.domain.usecases.BrandUseCases;
import com.bootcamp2024.StockMicroservice.domain.usecases.CategoryUseCases;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.BrandEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters.BrandAdapter;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters.CategoryAdapter;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.CategoryEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.IBrandRepository;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.ICategoryRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;


    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandAdapter(brandRepository,brandEntityMapper);
    }


    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCases(categoryPersistencePort());
    }

    @Bean
    public IBrandServicePort brandServicePort(){return new BrandUseCases(brandPersistencePort());
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Stock Microservice")
                        .version("1.0")
                        .description("Stock Microservice API"));
    }

}
