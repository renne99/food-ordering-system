package food_ordering_system.entity;

import jakarta.persistence.*;

/**
 * This class represents the Category table in the database.
 * Each instance of this class maps to a row in the 'category' table.
 */
@Entity
@Table(name = "category")
public class Category {

    // Primary key - auto incremented by the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The name of the category e.g. Fast Food, Pizza
    private String name;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}