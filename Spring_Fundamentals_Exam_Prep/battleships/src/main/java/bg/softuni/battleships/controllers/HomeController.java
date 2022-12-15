package bg.softuni.battleships.controllers;

import bg.softuni.battleships.models.dtos.BattleShipsDTO;
import bg.softuni.battleships.services.ShipService;
import bg.softuni.battleships.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final ShipService shipService;

    public HomeController(LoggedUser loggedUser, ShipService shipService) {
        this.loggedUser = loggedUser;
        this.shipService = shipService;
    }

    @ModelAttribute("battleShips")
    public BattleShipsDTO initBattle() {
        return new BattleShipsDTO();
    }

    @GetMapping("/")
    public String index() {
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (loggedUser.getId() == null) {
            return "redirect:/index";
        }
        model.addAttribute("userShips", this.shipService.getAllUserShips(loggedUser.getId()));
        model.addAttribute("otherShips", this.shipService.getAllNonUserShips(loggedUser.getId()));
        model.addAttribute("allShips", this.shipService.getAllShips());

        return "home";
    }

    @PostMapping("/home")
    public String homeBattle(@Valid BattleShipsDTO battleShipsDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (loggedUser.getId() == null) {
            return "redirect:/index";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("battleShips", battleShipsDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.battleShips", bindingResult);
            return "redirect:/home";
        }

        this.shipService.battle(battleShipsDTO);
        return "redirect:/home";
    }
}
