package bg.softuni.errors.web;

import bg.softuni.errors.model.CategoryDTO;
import bg.softuni.errors.model.ObjectNotFoundException;
import bg.softuni.errors.model.ProductDTO;
import bg.softuni.errors.model.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/category")
@Controller
public class CategoryController {

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id,
                                 Model model) {
        CategoryDTO categoryById = getCategoryById(id);
        if (categoryById == null) {
            throw new ObjectNotFoundException(id);
        }
        model.addAttribute("name", categoryById.getCategoryName());

        return "category";
    }

    private CategoryDTO getCategoryById(Long id) {
        return null;
    }
}
