package com.bootcamp2024.StockMicroservice.infrastructure.configuration;


import com.bootcamp2024.StockMicroservice.domain.api.IBrandServicePort;
import com.bootcamp2024.StockMicroservice.domain.api.IItemServicePort;
import com.bootcamp2024.StockMicroservice.domain.spi.IBrandPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.spi.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.api.ICategoryServicePort;
import com.bootcamp2024.StockMicroservice.domain.spi.IItemPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.usecases.BrandUseCases;
import com.bootcamp2024.StockMicroservice.domain.usecases.CategoryUseCases;
import com.bootcamp2024.StockMicroservice.domain.usecases.ItemUseCases;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.IBrandEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.IItemEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters.BrandAdapter;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.PaginationMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters.CategoryAdapter;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.CategoryEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters.ItemAdapter;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.IBrandRepository;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.ICategoryRepository;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.IItemRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    private final IItemRepository itemRepository;
    private final IItemEntityMapper iItemEntityMapper;

    private final PaginationMapper paginationMapper;



    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryAdapter(categoryRepository, categoryEntityMapper, paginationMapper);
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandAdapter(brandRepository,brandEntityMapper, paginationMapper);
    }

    @Bean
    public IItemPersistencePort itemPersistencePort(){
        return new ItemAdapter(itemRepository,iItemEntityMapper, paginationMapper);
    }


    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCases(categoryPersistencePort());
    }

    @Bean
    public IBrandServicePort brandServicePort(){return new BrandUseCases(brandPersistencePort());
    }

    @Bean
    public IItemServicePort itemServicePort(){
        return new ItemUseCases(itemPersistencePort());
    }

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);

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
