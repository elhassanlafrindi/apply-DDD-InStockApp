package lafrindi.stockapp.catalog.infrastructure.persistence.jpa.repository;

import lafrindi.stockapp.catalog.infrastructure.persistence.jpa.entity.JpaCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaCategoryRepository extends JpaRepository<JpaCategoryEntity, UUID> {
    public Optional<JpaCategoryEntity> findByName(String name);
}
