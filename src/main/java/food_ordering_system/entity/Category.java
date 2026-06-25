package food_ordering_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories") // This maps the class to a database table named 'categories'
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Handles auto-incrementing IDs in PostgreSQL
    private Long id;

    private String name;
    private String description;

    // 1. Default No-Args Constructor (Required by JPA/Hibernate)
    public Category() {
    }

    // 2. Parameterized Constructor
    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // 3. Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}