package bg.softuni.battleships.services;

import bg.softuni.battleships.models.dtos.BattleShipsDTO;
import bg.softuni.battleships.models.dtos.CreateShipDTO;
import bg.softuni.battleships.models.dtos.ShipBattleDTO;
import bg.softuni.battleships.models.entities.Category;
import bg.softuni.battleships.models.entities.Ship;
import bg.softuni.battleships.models.entities.User;
import bg.softuni.battleships.models.enums.CategoryNames;
import bg.softuni.battleships.repositories.CategoryRepository;
import bg.softuni.battleships.repositories.ShipRepository;
import bg.softuni.battleships.repositories.UserRepository;
import bg.softuni.battleships.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Ship> getAllUserShips(Long id) {
        User user = this.userRepository.findById(id).orElseThrow();

        return this.shipRepository.findAllByUser(user);
    }

    public List<Ship> getAllNonUserShips(Long id) {
        List<Ship> allShips = this.shipRepository.findAll();
        return allShips.stream().filter(s -> !s.getUser().getId().equals(id)).collect(Collectors.toList());
    }

    public List<ShipBattleDTO> getAllShips() {
        return this.shipRepository.findAllByOrderByHealthAscNameDescPowerAsc().stream()
                .map(s -> mapper.map(s, ShipBattleDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void battle(BattleShipsDTO battleShipsDTO) {
        Ship attackerShip = this.shipRepository.findById(battleShipsDTO.getAttackerId()).orElseThrow();
        Ship defenderShip = this.shipRepository.findById(battleShipsDTO.getDefenderId()).orElseThrow();

        defenderShip.setHealth(defenderShip.getHealth() - attackerShip.getPower());
        if (defenderShip.getHealth() > 0) {
            this.shipRepository.save(defenderShip);
        } else {
            this.shipRepository.delete(defenderShip);
        }

    }
}
