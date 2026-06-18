package food_ordering_system.service;

import food_ordering_system.dto.CategoryDto;
import food_ordering_system.entity.Category;
import food_ordering_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the service layer - it contains the business logic.
 * It fetches data from the repository, converts it to DTOs,
 * and returns the result to the controller.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    // Spring automatically injects the repository here
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {

        // Fetch all Category entities from the database
        List<Category> categories = categoryRepository.findAll();

        // Create an empty list to hold the DTOs
        List<CategoryDto> categoryDtos = new ArrayList<>();

        // Convert each Category entity into a CategoryDto
        for (Category category : categories) {
            CategoryDto dto = new CategoryDto();
            dto.setId(category.getId());
            dto.setName(category.getName());
            categoryDtos.add(dto);
        }

        return categoryDtos;
    }
}