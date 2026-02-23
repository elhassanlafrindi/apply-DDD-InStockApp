package lafrindi.stockapp.catalog.infrastructure.persistence.jpa.mapper;

import lafrindi.stockapp.catalog.domain.model.Category;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;
import lafrindi.stockapp.catalog.infrastructure.persistence.jpa.entity.JpaCategoryEntity;

public class CategoryPersistenceMapper {

    public static JpaCategoryEntity toEntity(Category category) {
        JpaCategoryEntity entity = new JpaCategoryEntity();
        entity.setId(category.getCategoryId().getValue());
        entity.setName(category.getName());
        entity.setDescription(category.getDescription());
        entity.setParentCategoryId(
                category.getParentCategoryId() != null
                        ? category.getParentCategoryId().getValue()
                        : null
        );
        return entity;
    }

    public static Category toDomain(JpaCategoryEntity entity) {
        return new Category(
                new CategoryId(entity.getId()),
                entity.getName(),
                entity.getDescription(),
                entity.getParentCategoryId() != null
                        ? new CategoryId(entity.getParentCategoryId())
                        : null
        );
    }
}
