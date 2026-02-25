package lafrindi.stockapp.catalog.application.service;

import lafrindi.stockapp.catalog.application.dto.CategoryRequest;
import lafrindi.stockapp.catalog.application.dto.CategoryResponse;
import lafrindi.stockapp.catalog.application.mapper.CategoryMapper;
import lafrindi.stockapp.catalog.domain.model.Category;
import lafrindi.stockapp.catalog.domain.service.CategoryDomainService;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;
import lafrindi.stockapp.catalog.interfaces.validation.CategoryValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryApplicationServiceImpl implements CategoryApplicationService {

    private final CategoryDomainService domainService;

    public CategoryApplicationServiceImpl(CategoryDomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        CategoryValidator.validate(request);

        Category category = domainService.createCategory(
                request.name,
                request.description,
                request.parentCategoryId != null
                        ? new CategoryId(request.parentCategoryId)
                        : null
        );

        return CategoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse findById(UUID id) {
        return domainService.findById(new CategoryId(id))
                .map(CategoryMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));
    }

    @Override
    public List<CategoryResponse> findAll() {
        return domainService.findAll().stream()
                .map(CategoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse update(UUID id, CategoryRequest request) {
        CategoryValidator.validateUpdate(id, request);

        Category category = domainService.updateCategory(
                new CategoryId(id),
                request.name,
                request.description,
                request.parentCategoryId != null
                        ? new CategoryId(request.parentCategoryId)
                        : null
        );

        return CategoryMapper.toResponse(category);
    }

    @Override
    public void delete(UUID id) {
        domainService.deleteCategory(new CategoryId(id));
    }
}