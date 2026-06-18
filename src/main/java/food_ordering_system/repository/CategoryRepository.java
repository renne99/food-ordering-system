package food_ordering_system.repository;

import food_ordering_system.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The repository layer is responsible for talking directly to the database.
 * By extending JpaRepository, we get built-in methods like
 * findAll(), findById(), save(), and deleteById() for free.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}