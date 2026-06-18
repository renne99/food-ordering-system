package food_ordering_system.dto;

/**
 * DTO stands for Data Transfer Object.
 * Instead of sending the full entity to the client,
 * we use a DTO to control exactly what data is exposed.
 * This keeps our API clean and secure.
 */
public class CategoryDto {

    private Long id;
    private String name;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}