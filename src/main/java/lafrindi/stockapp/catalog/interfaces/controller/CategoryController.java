package lafrindi.stockapp.catalog.interfaces.controller;

import lafrindi.stockapp.catalog.application.dto.CategoryRequest;
import lafrindi.stockapp.catalog.application.dto.CategoryResponse;
import lafrindi.stockapp.catalog.application.service.CategoryApplicationService;
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
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryApplicationService service;

    public CategoryController(CategoryApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable UUID id, @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}