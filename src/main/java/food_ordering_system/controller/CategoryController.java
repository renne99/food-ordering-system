package food_ordering_system.controller;

import food_ordering_system.dto.CategoryDto;
import food_ordering_system.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The controller is the entry point for HTTP requests.
 * It receives requests from the client, calls the service layer,
 * and returns the response back to the client.
 */

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // GET all categories
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // [Task 4.1] GET single category by ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    // [Task 4.2] POST create a category
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // [Task 4.4] PUT update a category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
    }

    // [Task 4.5] DELETE a category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build(); // Status 204
    }
}