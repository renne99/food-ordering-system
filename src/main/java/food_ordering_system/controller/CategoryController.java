package food_ordering_system.controller;

import food_ordering_system.dto.CategoryDto;
import food_ordering_system.response.Response;
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

    // 1. GET ALL
    @GetMapping
    public ResponseEntity<Response<List<CategoryDto>>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(Response.success("All categories retrieved successfully", categories));
    }

    // 2. GET BY ID [Task 4.1 Refactored]
    @GetMapping("/{id}")
    public ResponseEntity<Response<CategoryDto>> getCategoryById(@PathVariable Long id) {
        CategoryDto dto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(Response.success("Category retrieved", dto));
    }

    // 3. POST CREATE [Task 4.2 Refactored]
    @PostMapping
    public ResponseEntity<Response<CategoryDto>> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("Category created successfully", savedCategory));
    }

    // 4. PUT UPDATE [Task 4.4 Refactored]
    @PutMapping("/{id}")
    public ResponseEntity<Response<CategoryDto>> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategory = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(Response.success("Category updated successfully", updatedCategory));
    }

    // 5. DELETE [Task 4.5 Refactored]
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        // We use 200 OK here instead of 204 NoContent because 204 forces an empty body,
        // but we want to display our message payload to the user!
        return ResponseEntity.ok(Response.success("Category deleted successfully", null));
    }
}