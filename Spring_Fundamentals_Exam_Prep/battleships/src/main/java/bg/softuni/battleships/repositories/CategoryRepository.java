package bg.softuni.battleships.repositories;

import bg.softuni.battleships.models.entities.Category;
import bg.softuni.battleships.models.enums.CategoryNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(CategoryNames name);
}
