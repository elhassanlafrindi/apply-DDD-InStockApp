package lafrindi.stockapp.inventry.domain.enums;

public enum MovementStatus {
    PENDING,     // Movement recorded but not yet processed
    PROCESSING,  // Movement is being processed
    COMPLETED,   // Movement successfully completed
    CANCELLED,   // Movement was cancelled
    REJECTED     // Movement was rejected
}