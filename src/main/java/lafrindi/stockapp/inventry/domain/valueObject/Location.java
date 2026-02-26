package lafrindi.stockapp.inventry.domain.valueObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Location {
    private final String warehouseId;
    private final String aisle;
    private final String shelf;
    private final String bin;

    public Location(String warehouseId, String aisle, String shelf, String bin) {
        if (warehouseId == null || warehouseId.isBlank()) {
            throw new IllegalArgumentException("Warehouse ID cannot be null or empty");
        }
        this.warehouseId = warehouseId;
        this.aisle = aisle;
        this.shelf = shelf;
        this.bin = bin;
    }

    public static Location of(String warehouseId, String aisle, String shelf, String bin) {
        return new Location(warehouseId, aisle, shelf, bin);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(warehouseId);
        if (aisle != null) sb.append("-").append(aisle);
        if (shelf != null) sb.append("-").append(shelf);
        if (bin != null) sb.append("-").append(bin);
        return sb.toString();
    }
}