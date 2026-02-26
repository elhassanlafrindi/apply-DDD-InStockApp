package lafrindi.stockapp.inventry.domain.model;

import lafrindi.stockapp.inventry.domain.valueObject.Location;
import lafrindi.stockapp.inventry.domain.valueObject.Quantity;
import lafrindi.stockapp.inventry.domain.valueObject.StockId;

import java.time.Instant;

public class Stock {
    private final StockId stockId;
    private final String productId;
    private Quantity totalQuantity;
    private Quantity reservedQuantity;
    private Quantity availableQuantity;
    private Location defaultLocation;
    private Integer minimumStock;
    private Integer maximumStock;
    private String status; // ACTIVE, INACTIVE, DISCONTINUED
    private Instant lastUpdated;

    public Stock(StockId stockId, String productId, Quantity initialQuantity, Location defaultLocation) {
        this.stockId = stockId;
        this.productId = productId;
        this.totalQuantity = initialQuantity;
        this.reservedQuantity = Quantity.of(0);
        this.availableQuantity = initialQuantity;
        this.defaultLocation = defaultLocation;
        this.minimumStock = 0;
        this.maximumStock = null;
        this.status = "ACTIVE";
        this.lastUpdated = Instant.now();
    }

    // Getters
    public StockId getStockId() { return stockId; }
    public String getProductId() { return productId; }
    public Quantity getTotalQuantity() { return totalQuantity; }
    public Quantity getReservedQuantity() { return reservedQuantity; }
    public Quantity getAvailableQuantity() { return availableQuantity; }
    public Location getDefaultLocation() { return defaultLocation; }
    public Integer getMinimumStock() { return minimumStock; }
    public Integer getMaximumStock() { return maximumStock; }
    public String getStatus() { return status; }
    public Instant getLastUpdated() { return lastUpdated; }

    // Setters for configurable properties
    public void setMinimumStock(Integer minimumStock) {
        if (minimumStock < 0) {
            throw new IllegalArgumentException("Minimum stock cannot be negative");
        }
        this.minimumStock = minimumStock;
    }

    public void setMaximumStock(Integer maximumStock) {
        if (maximumStock != null && maximumStock < 0) {
            throw new IllegalArgumentException("Maximum stock cannot be negative");
        }
        this.maximumStock = maximumStock;
    }

    public void setStatus(String status) { this.status = status; }

    public void setDefaultLocation(Location defaultLocation) { this.defaultLocation = defaultLocation; }

    // Business methods
    public void updateQuantities(Quantity newTotal, Quantity newReserved) {
        this.totalQuantity = newTotal;
        this.reservedQuantity = newReserved;
        this.availableQuantity = newTotal.subtract(newReserved);
        this.lastUpdated = Instant.now();
    }

    public boolean hasLowStock() {
        return this.availableQuantity.isLessThan(Quantity.of(minimumStock));
    }

    public boolean isOutOfStock() {
        return this.availableQuantity.isLessThan(Quantity.of(1));
    }

    public boolean hasOverstock() {
        return maximumStock != null && this.totalQuantity.isGreaterThan(Quantity.of(maximumStock));
    }

    public void reserve(Quantity quantity) {
        if (availableQuantity.isLessThan(quantity)) {
            throw new IllegalArgumentException("Insufficient available quantity to reserve");
        }
        Quantity newReserved = reservedQuantity.add(quantity);
        Quantity newAvailable = availableQuantity.subtract(quantity);
        updateQuantities(totalQuantity, newReserved);
    }

    public void releaseReservation(Quantity quantity) {
        if (reservedQuantity.isLessThan(quantity)) {
            throw new IllegalArgumentException("Cannot release more quantity than is reserved");
        }
        Quantity newReserved = reservedQuantity.subtract(quantity);
        Quantity newAvailable = availableQuantity.add(quantity);
        updateQuantities(totalQuantity, newReserved);
    }

    public void adjustQuantity(Quantity newTotalQuantity) {
        if (newTotalQuantity.isLessThan(reservedQuantity)) {
            throw new IllegalArgumentException("Total quantity cannot be less than reserved quantity");
        }
        Quantity newAvailable = newTotalQuantity.subtract(reservedQuantity);
        this.totalQuantity = newTotalQuantity;
        this.availableQuantity = newAvailable;
        this.lastUpdated = Instant.now();
    }
}
