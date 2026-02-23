package lafrindi.stockapp.catalog.infrastructure.persistence.impl;


import lafrindi.stockapp.catalog.domain.model.Category;
import lafrindi.stockapp.catalog.domain.repository.CategoryRepository;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;
import lafrindi.stockapp.catalog.infrastructure.persistence.jpa.entity.JpaCategoryEntity;
import lafrindi.stockapp.catalog.infrastructure.persistence.jpa.mapper.CategoryPersistenceMapper;
import lafrindi.stockapp.catalog.infrastructure.persistence.jpa.repository.JpaCategoryRepository;

import java.util.List;
import java.util.Optional;
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaCategoryRepository jpaRepository;

    public CategoryRepositoryImpl(JpaCategoryRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Category save(Category category) {
        JpaCategoryEntity entity = CategoryPersistenceMapper.toEntity(category);
        return CategoryPersistenceMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Category> findById(CategoryId id) {
        return jpaRepository.findById(id.getValue())
                .map(CategoryPersistenceMapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(CategoryPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Category category) {
        jpaRepository.deleteById(category.getCategoryId().getValue());
    }
}