package lafrindi.stockapp.catalog.infrastructure.config;

import lafrindi.stockapp.catalog.domain.repository.ProductRepository;
import lafrindi.stockapp.catalog.infrastructure.persistence.impl.ProductRepositoryImpl;
import lafrindi.stockapp.catalog.infrastructure.persistence.jpa.repository.JpaProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogInfrastructureConfig {

    @Bean
    public ProductRepository productRepository(JpaProductRepository jpaRepo) {
        return new ProductRepositoryImpl(jpaRepo);
    }
}