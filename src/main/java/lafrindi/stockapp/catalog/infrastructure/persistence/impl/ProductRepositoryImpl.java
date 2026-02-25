package lafrindi.stockapp.catalog.infrastructure.persistence.impl;

import lafrindi.stockapp.catalog.domain.model.Product;
import lafrindi.stockapp.catalog.domain.repository.ProductRepository;
import lafrindi.stockapp.catalog.domain.valueObject.ProductId;
import lafrindi.stockapp.catalog.domain.valueObject.Sku;
import lafrindi.stockapp.catalog.infrastructure.persistence.jpa.entity.JpaProductEntity;
import lafrindi.stockapp.catalog.infrastructure.persistence.jpa.mapper.ProductPersistenceMapper;
import lafrindi.stockapp.catalog.infrastructure.persistence.jpa.repository.JpaProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Product save(Product product) {
        JpaProductEntity entity = ProductPersistenceMapper.toEntity(product);
        return ProductPersistenceMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return jpaRepository.findById(id.getValue())
                .map(ProductPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Product> findBySku(Sku sku) {
        return jpaRepository.findBySku(sku.getValue())
                .map(ProductPersistenceMapper::toDomain);
    }

    @Override
    public void delete(Product product) {
        jpaRepository.deleteById(product.getProductId().getValue());
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll().stream().map(ProductPersistenceMapper::toDomain).collect(Collectors.toList());
    }
}