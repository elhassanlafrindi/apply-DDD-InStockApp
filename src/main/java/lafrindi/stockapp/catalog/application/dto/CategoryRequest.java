package lafrindi.stockapp.catalog.application.dto;

import java.util.UUID;

public class CategoryRequest {
    public String name;
    public String description;
    public UUID parentCategoryId;
}
