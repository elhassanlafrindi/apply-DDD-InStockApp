package lafrindi.stockapp.inventry.domain.repository;

import lafrindi.stockapp.inventry.domain.model.Movement;
import lafrindi.stockapp.inventry.domain.valueObject.StockId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovementRepository {
    Movement save(Movement movement);
    Optional<Movement> findById(UUID id);
    List<Movement> findAll();
    void delete(Movement movement);
    List<Movement> findByProductId(String productId);

}