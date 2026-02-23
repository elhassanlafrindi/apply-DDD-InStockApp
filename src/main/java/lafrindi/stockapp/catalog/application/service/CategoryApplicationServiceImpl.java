package lafrindi.stockapp.catalog.application.service;

import lafrindi.stockapp.catalog.application.dto.CategoryRequest;
import lafrindi.stockapp.catalog.application.dto.CategoryResponse;
import lafrindi.stockapp.catalog.application.mapper.CategoryMapper;
import lafrindi.stockapp.catalog.domain.model.Category;
import lafrindi.stockapp.catalog.domain.service.CategoryDomainService;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;
import org.springframework.stereotype.Service;


@Service
public class CategoryApplicationServiceImpl implements CategoryApplicationService {

    private final CategoryDomainService domainService;

    public CategoryApplicationServiceImpl(CategoryDomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {

        Category category = domainService.createCategory(
                request.name,
                request.description,
                request.parentCategoryId != null
                        ? new CategoryId(request.parentCategoryId)
                        : null
        );

        return CategoryMapper.toResponse(category);
    }
}
