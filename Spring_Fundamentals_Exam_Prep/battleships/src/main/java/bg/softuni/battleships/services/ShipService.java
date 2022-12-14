package bg.softuni.battleships.services;

import bg.softuni.battleships.repositories.ShipRepository;
import org.springframework.stereotype.Service;

@Service
public class ShipService {
    private final ShipRepository shipRepository;

    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }
}
