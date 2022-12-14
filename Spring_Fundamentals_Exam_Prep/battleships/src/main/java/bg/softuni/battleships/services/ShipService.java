package bg.softuni.battleships.services;

import bg.softuni.battleships.models.dtos.CreateShipDTO;
import bg.softuni.battleships.models.entities.Category;
import bg.softuni.battleships.models.entities.Ship;
import bg.softuni.battleships.models.enums.CategoryNames;
import bg.softuni.battleships.repositories.CategoryRepository;
import bg.softuni.battleships.repositories.ShipRepository;
import bg.softuni.battleships.repositories.UserRepository;
import bg.softuni.battleships.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipService {
    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final LoggedUser loggedUser;
    public ShipService(ShipRepository shipRepository, CategoryRepository categoryRepository, UserRepository userRepository, ModelMapper mapper, LoggedUser loggedUser) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.loggedUser = loggedUser;
    }

    public boolean create(CreateShipDTO createShipDTO) {
        if (this.shipRepository.findByName(createShipDTO.getName()).isPresent()) {
            return false;
        }
        Ship ship = mapper.map(createShipDTO, Ship.class);
        Optional<Category> byName = categoryRepository.findByName(CategoryNames.values()[createShipDTO.getCategory()]);
        if (byName.isEmpty()) {
            return false;
        }
        ship.setCategory(byName.get());
        ship.setUser(this.userRepository.getReferenceById(loggedUser.getId()));
        this.shipRepository.save(ship);

        return true;
    }
}
