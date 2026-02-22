package lafrindi.stockapp.catalog.infrastructure.persistence.jpa.repository;

import lafrindi.stockapp.catalog.infrastructure.persistence.jpa.entity.JpaProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<JpaProductEntity, UUID> {
    Optional<JpaProductEntity> findBySku(String sku);
}
