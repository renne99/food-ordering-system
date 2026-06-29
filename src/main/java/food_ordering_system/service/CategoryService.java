package food_ordering_system.service;

import food_ordering_system.dto.CategoryDto;
import java.util.List;

/**
 * This interface defines the contract for the service layer.
 * Any class that implements this must provide
 * the getAllCategories() method.
 */
public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
    CategoryDto addCategory(CategoryDto dto);
    CategoryDto updateCategory(Long id, CategoryDto dto);
    void deleteCategory(Long id);
}