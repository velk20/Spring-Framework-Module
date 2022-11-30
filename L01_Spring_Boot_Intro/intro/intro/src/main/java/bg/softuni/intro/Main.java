package bg.softuni.intro;

import bg.softuni.intro.cats.model.dto.CreateOwnerDTO;
import bg.softuni.intro.cats.service.OwnerService;
import bg.softuni.intro.models.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Main implements CommandLineRunner {
    private final OwnerService ownerService;

    @Autowired
    public Main( OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public void run(String... args) throws Exception {
        ownerService.createOwner(new CreateOwnerDTO()
                .setOwnerName("Pesho")
                .setCatNames(List.of("Crastavica", "Domat")));
    }
}
