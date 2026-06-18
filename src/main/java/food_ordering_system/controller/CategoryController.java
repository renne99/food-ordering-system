package food_ordering_system.controller;

import food_ordering_system.dto.CategoryDto;
import food_ordering_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The controller is the entry point for HTTP requests.
 * It receives requests from the client, calls the service layer,
 * and returns the response back to the client.
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    // Spring automatically injects the service here
    @Autowired
    private CategoryService categoryService;

    /**
     * GET /api/category
     * Returns a list of all categories from the database.
     */
    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }
}