package lafrindi.stockapp.catalog.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class JpaCategoryEntity {

    @Id
    private UUID id;

    private String name;
    private String description;
    private UUID parentCategoryId;
}
