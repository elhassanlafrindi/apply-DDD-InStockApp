package lafrindi.stockapp.catalog.application.mapper;

import lafrindi.stockapp.catalog.application.dto.CategoryRequest;
import lafrindi.stockapp.catalog.application.dto.CategoryResponse;
import lafrindi.stockapp.catalog.domain.model.Category;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;

public class CategoryMapper {

    public static Category toDomain(CategoryRequest request) {
        return new Category(
                CategoryId.newId(),
                request.name,
                request.description,
                request.parentCategoryId != null ? new CategoryId(request.parentCategoryId) : null
        );
    }

    public static CategoryResponse toResponse(Category category) {
        CategoryResponse res = new CategoryResponse();
        res.categoryId = category.getCategoryId().getValue();
        res.name = category.getName();
        res.description = category.getDescription();
        res.parentCategoryId = category.getParentCategoryId() != null
                ? category.getParentCategoryId().getValue()
                : null;
        return res;
    }
}