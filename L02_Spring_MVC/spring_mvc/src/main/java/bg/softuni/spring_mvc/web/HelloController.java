package bg.softuni.spring_mvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
//@RequestMapping("/test") //url="localhost:8080/test.........." каквото има нататък ще бъде изпълнено само
public class HelloController {

    //Params variables
    //http://localhost:8080/testParams?name=Angel ще изведе => username = Angel
    @GetMapping("/testParams")
    public ModelAndView testParams(ModelAndView model, @RequestParam("name") String username) {
        model.addObject("name", username);
        model.setViewName("helloWorld");
        return model;
    }

    //Path variables
    //http://localhost:8080/model/Angel ще изведе => username = Angel
    @GetMapping("/model/{name}")
    public String model(Model model, @PathVariable("name")String name) {
        model.addAttribute("name", name);
        return "helloWorld.html";
    }

    @GetMapping("/model")
    public String model(Model model) {
        model.addAttribute("name", "Pesho");
        return "helloWorld.html";
    }

    @GetMapping( "/modelMap")
    public String modelMap(ModelMap model) {
        model.addAttribute("name", "Pesho");
        return "helloWorld";
    }

    @GetMapping( "/modelAndView")
    public ModelAndView modelAndView(ModelAndView model) {
        model.addObject("name", "Pesho");
        model.setViewName("helloWorld.html");
        return model;
    }
}
