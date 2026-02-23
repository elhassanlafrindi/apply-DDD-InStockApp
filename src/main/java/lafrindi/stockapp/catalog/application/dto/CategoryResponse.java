package lafrindi.stockapp.catalog.application.dto;

import java.util.UUID;

public class CategoryResponse {
    public UUID categoryId;
    public String name;
    public String description;
    public UUID parentCategoryId;
}