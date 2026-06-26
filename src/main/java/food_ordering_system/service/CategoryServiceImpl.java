package food_ordering_system.service;

import food_ordering_system.dto.CategoryDto;
import food_ordering_system.entity.Category;
import food_ordering_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import food_ordering_system.exception.CategoryNotFoundException;

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

            dto.setDescription(category.getDescription());
            categoryDtos.add(dto);
        }

        return categoryDtos;
    }

    //Task 4.1
    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    //Task 4.2
    @Override
    public CategoryDto addCategory(CategoryDto dto) {
        // Map DTO data over into a fresh Database Entity row
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        // Save the Entity row into MySQL
        Category savedCategory = categoryRepository.save(category);

        // Map the database's newly generated row back into a DTO to return
        CategoryDto savedDto = new CategoryDto();
        savedDto.setId(savedCategory.getId());
        savedDto.setName(savedCategory.getName());
        savedDto.setDescription(savedCategory.getDescription());
        return savedDto;
    }

    //Task 4.4
    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {
        // Find matching record or throw our custom 404 error
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        // Overwrite the old values with the incoming data
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        // Convert the newly updated row back into our DTO format
        CategoryDto updatedDto = new CategoryDto();
        updatedDto.setId(updatedCategory.getId());
        updatedDto.setName(updatedCategory.getName());
        updatedDto.setDescription(updatedCategory.getDescription());
        return updatedDto;
    }

    //Task 4.5
    @Override
    public void deleteCategory(Long id) {
        // Verify the category exists before attempting to delete it
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        categoryRepository.delete(category);
    }

}