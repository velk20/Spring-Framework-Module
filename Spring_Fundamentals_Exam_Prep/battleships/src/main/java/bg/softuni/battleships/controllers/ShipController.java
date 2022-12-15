package bg.softuni.battleships.controllers;

import bg.softuni.battleships.models.dtos.CreateShipDTO;
import bg.softuni.battleships.services.ShipService;
import bg.softuni.battleships.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ShipController {
    private final ShipService shipService;
    private final LoggedUser loggedUser;

    public ShipController(ShipService shipService, LoggedUser loggedUser) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("createShipDTO")
    public CreateShipDTO initModel() {
        return new CreateShipDTO();
    }
    @GetMapping("/ships/add")
    public String ships() {
        if (loggedUser.getId() == null) {
            return "redirect:/index";
        }
        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String ships(@Valid CreateShipDTO createShipDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (loggedUser.getId() == null) {
            return "redirect:/index";
        }

        if (bindingResult.hasErrors() || !this.shipService.create(createShipDTO)) {
            redirectAttributes.addFlashAttribute("createShipDTO",createShipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createShipDTO", bindingResult);
            return "redirect:/ships/add";
        }

        return "redirect:/home";
    }
}
