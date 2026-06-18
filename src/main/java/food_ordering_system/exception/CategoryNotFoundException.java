package food_ordering_system.exception;

/**
 * This is a custom exception class.
 * Instead of throwing a generic error, we throw this specific
 * exception when a category is not found in the database.
 * Custom exceptions make error handling cleaner and more readable.
 */
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) {
        super(message);
    }
}