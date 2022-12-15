package bg.softuni.battleships.repositories;

import bg.softuni.battleships.models.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    Optional<Ship> findByName(String name);

    List<Ship> findAllByUser(User user);

}
