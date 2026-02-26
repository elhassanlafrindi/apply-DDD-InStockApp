package lafrindi.stockapp.inventry.domain.service;

import lafrindi.stockapp.inventry.domain.model.Stock;
import lafrindi.stockapp.inventry.domain.repository.StockRepository;
import lafrindi.stockapp.inventry.domain.valueObject.Location;
import lafrindi.stockapp.inventry.domain.valueObject.Quantity;
import lafrindi.stockapp.inventry.domain.valueObject.StockId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StockDomainService {
    private final StockRepository stockRepository;

    public StockDomainService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock createStock(String productId, Quantity initialQuantity, Location defaultLocation) {
        StockId stockId = StockId.newId();
        Stock stock = new Stock(stockId, productId, initialQuantity, defaultLocation);
        return stockRepository.save(stock);
    }

    public Optional<Stock> findById(StockId id) {
        return stockRepository.findById(id);
    }

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public Stock updateStock(StockId id, Integer minimumStock, Integer maximumStock, String status, Location defaultLocation) {
        Optional<Stock> existingStock = stockRepository.findById(id);
        if (existingStock.isEmpty()) {
            throw new IllegalArgumentException("Stock not found with id: " + id);
        }

        Stock stock = existingStock.get();
        if (minimumStock != null) stock.setMinimumStock(minimumStock);
        if (maximumStock != null) stock.setMaximumStock(maximumStock);
        if (status != null) stock.setStatus(status);
        if (defaultLocation != null) stock.setDefaultLocation(defaultLocation);

        return stockRepository.save(stock);
    }

    public void deleteStock(StockId id) {
        Optional<Stock> stock = stockRepository.findById(id);
        if (stock.isEmpty()) {
            throw new IllegalArgumentException("Stock not found with id: " + id);
        }
        stockRepository.delete(stock.get());
    }

    public Stock adjustQuantity(StockId id, Quantity newQuantity) {
        Optional<Stock> existingStock = stockRepository.findById(id);
        if (existingStock.isEmpty()) {
            throw new IllegalArgumentException("Stock not found with id: " + id);
        }

        Stock stock = existingStock.get();
        stock.adjustQuantity(newQuantity);
        return stockRepository.save(stock);
    }

    public Stock reserveQuantity(StockId id, Quantity quantity) {
        Optional<Stock> existingStock = stockRepository.findById(id);
        if (existingStock.isEmpty()) {
            throw new IllegalArgumentException("Stock not found with id: " + id);
        }

        Stock stock = existingStock.get();
        stock.reserve(quantity);
        return stockRepository.save(stock);
    }

    public Stock releaseReservation(StockId id, Quantity quantity) {
        Optional<Stock> existingStock = stockRepository.findById(id);
        if (existingStock.isEmpty()) {
            throw new IllegalArgumentException("Stock not found with id: " + id);
        }

        Stock stock = existingStock.get();
        stock.releaseReservation(quantity);
        return stockRepository.save(stock);
    }

    public Optional<Stock> findByProductId(String productId) {
        return stockRepository.findByProductId(productId);
    }
}