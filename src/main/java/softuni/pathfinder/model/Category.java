package softuni.pathfinder.model;

import jakarta.persistence.*;
import softuni.pathfinder.model.enums.RouteCategoryEnum;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RouteCategoryEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category(){

    }

    public long getId() {
        return id;
    }

    public Category setId(long id) {
        this.id = id;
        return this;
    }

    public RouteCategoryEnum getName() {
        return name;
    }

    public Category setName(RouteCategoryEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
