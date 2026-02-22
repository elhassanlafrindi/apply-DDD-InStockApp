package lafrindi.stockapp.catalog.domain.repository;

import lafrindi.stockapp.catalog.domain.model.Category;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;

import java.util.Optional;
import java.util.List;

public interface CategoryRepository {

    Category save(Category category);

    Optional<Category> findById(CategoryId categoryId);

    List<Category> findAll();

    void delete(Category category);
}