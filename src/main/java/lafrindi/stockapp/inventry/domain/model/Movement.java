package lafrindi.stockapp.inventry.domain.model;

import lafrindi.stockapp.inventry.domain.enums.MovementStatus;
import lafrindi.stockapp.inventry.domain.enums.MovementType;
import lafrindi.stockapp.inventry.domain.valueObject.Location;
import lafrindi.stockapp.inventry.domain.valueObject.Quantity;

import java.time.Instant;
import java.util.UUID;

public class Movement {
    private final UUID movementId;
    private final String productId;
    private final MovementType type;
    private Quantity quantity;
    private Location location;
    private MovementStatus status;
    private final String reason;
    private final String reference;  // N° commande, N° bon de livraison, etc.
    private final Instant createdAt;
    private Instant completedAt;
    private String performedBy;
    private String notes;

    public Movement(String productId, MovementType type, Quantity quantity, Location location, String reason, String reference) {
        this.movementId = UUID.randomUUID();
        this.productId = productId;
        this.type = type;
        this.quantity = quantity;
        this.location = location;
        this.reason = reason;
        this.reference = reference;
        this.status = MovementStatus.PENDING;
        this.createdAt = Instant.now();
    }

    // Getters
    public UUID getMovementId() { return movementId; }
    public String getProductId() { return productId; }
    public MovementType getType() { return type; }
    public Quantity getQuantity() { return quantity; }
    public Location getLocation() { return location; }
    public MovementStatus getStatus() { return status; }
    public String getReason() { return reason; }
    public String getReference() { return reference; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getCompletedAt() { return completedAt; }
    public String getPerformedBy() { return performedBy; }
    public String getNotes() { return notes; }

    // Setters for mutable properties
    public void setQuantity(Quantity quantity) { this.quantity = quantity; }
    public void setLocation(Location location) { this.location = location; }
    public void setStatus(MovementStatus status) { this.status = status; }
    public void setCompletedAt(Instant completedAt) { this.completedAt = completedAt; }
    public void setPerformedBy(String performedBy) { this.performedBy = performedBy; }
    public void setNotes(String notes) { this.notes = notes; }

    // Business methods
    public void complete(String performedBy) {
        this.status = MovementStatus.COMPLETED;
        this.completedAt = Instant.now();
        this.performedBy = performedBy;
    }

    public void cancel() {
        this.status = MovementStatus.CANCELLED;
    }

    public boolean isIncoming() {
        return this.type == MovementType.INCOMING;
    }

    public boolean isOutgoing() {
        return this.type == MovementType.OUTGOING;
    }
}
