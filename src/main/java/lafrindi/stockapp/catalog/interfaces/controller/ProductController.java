package lafrindi.stockapp.catalog.interfaces.controller;

import lafrindi.stockapp.catalog.application.dto.ProductRequest;
import lafrindi.stockapp.catalog.application.dto.ProductResponse;
import lafrindi.stockapp.catalog.application.service.ProductApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductApplicationService service;

    public ProductController(ProductApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable UUID id, @RequestBody ProductRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}