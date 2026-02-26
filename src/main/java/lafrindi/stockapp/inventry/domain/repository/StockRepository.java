package lafrindi.stockapp.inventry.domain.repository;

import lafrindi.stockapp.inventry.domain.model.Stock;
import lafrindi.stockapp.inventry.domain.valueObject.StockId;

import java.util.List;
import java.util.Optional;

public interface StockRepository {
    Stock save(Stock stock);
    Optional<Stock> findById(StockId id);
    List<Stock> findAll();
    void delete(Stock stock);
    Optional<Stock> findByProductId(String productId);
}