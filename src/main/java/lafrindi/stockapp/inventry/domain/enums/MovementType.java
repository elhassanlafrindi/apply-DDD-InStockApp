package lafrindi.stockapp.inventry.domain.enums;

public enum MovementType {
    INCOMING,      // Entry: purchase, production, transfer in
    OUTGOING,      // Exit: sale, consumption, transfer out
    TRANSFER,      // Internal transfer
    ADJUSTMENT,    // Manual adjustment
    CORRECTION     // Correction of errors
}