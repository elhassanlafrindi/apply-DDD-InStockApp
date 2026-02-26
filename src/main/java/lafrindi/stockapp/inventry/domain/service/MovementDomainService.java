package lafrindi.stockapp.inventry.domain.service;

import lafrindi.stockapp.inventry.domain.enums.MovementType;
import lafrindi.stockapp.inventry.domain.model.Movement;
import lafrindi.stockapp.inventry.domain.repository.MovementRepository;
import lafrindi.stockapp.inventry.domain.valueObject.Location;
import lafrindi.stockapp.inventry.domain.valueObject.Quantity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class MovementDomainService {
    private final MovementRepository movementRepository;

    public MovementDomainService(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public Movement createMovement(String productId, MovementType type, Quantity quantity, Location location, String reason, String reference) {
        Movement movement = new Movement(productId, type, quantity, location, reason, reference);
        return movementRepository.save(movement);
    }

    public Optional<Movement> findById(UUID id) {
        return movementRepository.findById(id);
    }

    public List<Movement> findAll() {
        return movementRepository.findAll();
    }

    public Movement updateMovement(UUID id, String performedBy, String notes) {
        Optional<Movement> existingMovement = movementRepository.findById(id);
        if (existingMovement.isEmpty()) {
            throw new IllegalArgumentException("Movement not found with id: " + id);
        }

        Movement movement = existingMovement.get();
        if (performedBy != null) movement.setPerformedBy(performedBy);
        if (notes != null) movement.setNotes(notes);

        return movementRepository.save(movement);
    }

    public void deleteMovement(UUID id) {
        Optional<Movement> movement = movementRepository.findById(id);
        if (movement.isEmpty()) {
            throw new IllegalArgumentException("Movement not found with id: " + id);
        }
        movementRepository.delete(movement.get());
    }

    public Movement completeMovement(UUID id, String performedBy) {
        Optional<Movement> existingMovement = movementRepository.findById(id);
        if (existingMovement.isEmpty()) {
            throw new IllegalArgumentException("Movement not found with id: " + id);
        }

        Movement movement = existingMovement.get();
        movement.complete(performedBy);
        return movementRepository.save(movement);
    }

    public Movement cancelMovement(UUID id) {
        Optional<Movement> existingMovement = movementRepository.findById(id);
        if (existingMovement.isEmpty()) {
            throw new IllegalArgumentException("Movement not found with id: " + id);
        }

        Movement movement = existingMovement.get();
        movement.cancel();
        return movementRepository.save(movement);
    }

    public List<Movement> findByProductId(String productId) {
        return movementRepository.findByProductId(productId);
    }
}