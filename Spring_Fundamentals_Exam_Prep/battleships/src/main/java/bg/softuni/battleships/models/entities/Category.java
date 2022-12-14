package bg.softuni.battleships.models.entities;

import bg.softuni.battleships.models.enums.CategoryNames;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.ORDINAL)
    private CategoryNames name;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Category() {
    }

    public Category(CategoryNames name) {
        this.name = name;
    }

    public CategoryNames getName() {
        return name;
    }

    public Category setName(CategoryNames name) {
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
